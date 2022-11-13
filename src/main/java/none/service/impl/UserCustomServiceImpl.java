package none.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import none.constants.Role;
import none.domain.UserCustom;
import none.exceptions.EmailAlreadyExistException;
import none.exceptions.EmailNotFoundException;
import none.exceptions.UsernameAlreadyExistException;
import none.repository.UserCustomRepository;
import none.service.UserCustomService;
import none.service.dto.UserCustomDTO;
import none.service.dto.UserPrincipal;
import none.service.mapper.UserCustomMapper;
import none.service.mapper.UserCustomMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserCustom}.
 */
@Service
@Transactional
public class UserCustomServiceImpl implements UserCustomService, UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserCustomServiceImpl.class);

    private final UserCustomRepository userCustomRepository;

    private final UserCustomMapperImpl userCustomMapper;

    @Autowired
    private LoginAttemptServiceImpl loginAttemptService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserCustomServiceImpl(UserCustomRepository userCustomRepository, UserCustomMapperImpl userCustomMapper) {
        this.userCustomRepository = userCustomRepository;
        this.userCustomMapper = userCustomMapper;
    }

    @Override
    public UserCustomDTO save(UserCustomDTO userCustomDTO)
        throws none.exceptions.UsernameNotFoundException, UsernameAlreadyExistException, EmailAlreadyExistException {
        log.info("Request to save UserCustom : {}", userCustomDTO);
        userCustomRepository
            .findOneByEmail(userCustomDTO.getEmail())
            .ifPresent(existingUser -> {
                throw new EmailAlreadyExistException("User email already exist");
            });
        UserCustom userCustom = userCustomMapper.toEntity(userCustomDTO);
        userCustom.setPassword(this.passwordEncoder.encode(userCustom.getPassword()));
        userCustom.setJoinDate(new Date());
        userCustom.setIsActived(true);
        userCustom.setIsNotLocked(true);
        Role role = Role.valueOf(userCustom.getRole());
        userCustom.setAuthorities(role.getAuthorities());
        userCustom = userCustomRepository.save(userCustom);
        return userCustomMapper.toDto(userCustom);
    }

    @Override
    public UserCustomDTO update(UserCustomDTO userCustomDTO) {
        log.debug("Request to update UserCustom : {}", userCustomDTO);
        UserCustom userCustom = userCustomMapper.toEntity(userCustomDTO);
        userCustom = userCustomRepository.save(userCustom);
        return userCustomMapper.toDto(userCustom);
    }

    @Override
    public Optional<UserCustomDTO> partialUpdate(UserCustomDTO userCustomDTO) {
        log.debug("Request to partially update UserCustom : {}", userCustomDTO);

        return userCustomRepository
            .findById(userCustomDTO.getId())
            .map(existingUserCustom -> {
                userCustomMapper.partialUpdate(existingUserCustom, userCustomDTO);

                return existingUserCustom;
            })
            .map(userCustomRepository::save)
            .map(userCustomMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserCustomDTO> findAll() {
        log.debug("Request to get all UserCustoms");
        return userCustomRepository.findAll().stream().map(userCustomMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserCustomDTO> findOne(Long id) {
        log.debug("Request to get UserCustom : {}", id);
        return userCustomRepository.findById(id).map(userCustomMapper::toDto);
    }

    @Override
    public Optional<UserCustomDTO> findOneByUsername(String username) throws none.exceptions.UsernameNotFoundException {
        log.debug("Request to get UserCustom : {}", username);
        Optional<UserCustomDTO> userCustomDTO = userCustomRepository.findOneByUsername(username).map(userCustomMapper::toDto);
        if (!userCustomDTO.isPresent()) throw new none.exceptions.UsernameNotFoundException("User email was not found");

        return userCustomDTO;
    }

    @Override
    public Optional<UserCustomDTO> findOneByEmail(String email) throws EmailNotFoundException {
        log.debug("Request to get UserCustom : {}", email);
        Optional<UserCustomDTO> userCustomDTO = userCustomRepository.findOneByEmail(email).map(userCustomMapper::toDto);
        if (!userCustomDTO.isPresent()) throw new EmailNotFoundException("User email was not found");

        return userCustomDTO;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCustom : {}", id);
        userCustomRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserCustom userCustom = this.userCustomRepository.findByUsername(username);
        if (userCustom == null) {
            log.error("Username not found");
            throw new UsernameNotFoundException("Username not found");
        } else {
            if (userCustom.getIsNotLocked()) {
                try {
                    if (loginAttemptService.hasExceededMaxAttempts(username)) {
                        userCustom.setIsNotLocked(false);
                    } else {
                        userCustom.setIsNotLocked(true);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                loginAttemptService.evictUserFromLoadingAttemptCache(username);
            }

            UserPrincipal userPrincipal = new UserPrincipal(userCustom);
            userCustom.setLastLoginDateDisplay(userCustom.getLastLoginDate());
            userCustom.setLastLoginDate(new Date());
            this.userCustomRepository.save(userCustom);
            log.info("Return user found");
            return userPrincipal;
        }
    }
}
