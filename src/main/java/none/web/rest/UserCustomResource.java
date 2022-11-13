package none.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import none.exceptions.EmailAlreadyExistException;
import none.exceptions.ExceptionHandling;
import none.exceptions.UsernameAlreadyExistException;
import none.exceptions.UsernameNotFoundException;
import none.repository.UserCustomRepository;
//import none.service.UserCustomQueryService;
import none.service.UserCustomService;
import none.service.criteria.UserCustomCriteria;
import none.service.dto.*;
//import none.web.rest.errors.BadRequestAlertException;
import none.service.mapper.UserCustomMapperImpl;
import none.utilities.JWTTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link none.domain.UserCustom}.
 */
@RestController
@RequestMapping(path = { "/api" })
public class UserCustomResource {

    private final Logger log = LoggerFactory.getLogger(UserCustomResource.class);

    private final UserCustomService userCustomService;

    private final UserCustomRepository userCustomRepository;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCustomMapperImpl userCustomMapper;

    //private final UserCustomQueryService userCustomQueryService;

    public UserCustomResource(
        UserCustomService userCustomService,
        UserCustomRepository userCustomRepository
        //UserCustomQueryService userCustomQueryService
    ) {
        this.userCustomService = userCustomService;
        this.userCustomRepository = userCustomRepository;
        //this.userCustomQueryService = userCustomQueryService;
    }

    /*    *//**
     * {@code GET  /user-customs} : get all the userCustoms.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCustoms in body.
     *//*
    @GetMapping("/user-customs")
    public ResponseEntity<List<UserCustomDTO>> getAllUserCustoms(UserCustomCriteria criteria) {
        log.debug("REST request to get UserCustoms by criteria: {}", criteria);
        *//*List<UserCustom> entityList = userCustomQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);*//*
        throw new NoResultException("Not usersCustoms Found");
    }

    *//**
     * {@code GET  /user-customs/count} : count all the userCustoms.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     *//*
    @GetMapping("/user-customs/count")
    public ResponseEntity<Long> countUserCustoms(UserCustomCriteria criteria) {
        log.debug("REST request to count UserCustoms by criteria: {}", criteria);
        //return ResponseEntity.ok().body(userCustomQueryService.countByCriteria(criteria));
        return null;
    }

    *//**
     * {@code GET  /user-customs/:id} : get the "id" userCustom.
     *
     * @param id the id of the userCustom to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCustom, or with status {@code 404 (Not Found)}.
     *//*
    @GetMapping("/user-customs/{id}")
    public ResponseEntity<UserCustomDTO> getUserCustom(@PathVariable Long id) {
        log.debug("REST request to get UserCustom : {}", id);
        Optional<UserCustomDTO> userCustom = userCustomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCustom);
    }*/

    /**
     * {@code POST  /register} : register userCustom.
     *
     * @param userCustomRegister the userCustom.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the userCustomDTO, or with status {@code 404 (Not Found)}.
     */
    @Operation(summary = "Register User", description = "Register User")
    @PostMapping("/register")
    public ResponseEntity<UserCustomDTO> register(@RequestBody UserCustomRegister userCustomRegister)
        throws EmailAlreadyExistException, UsernameAlreadyExistException, UsernameNotFoundException {
        log.debug("REST request to create UserCustom : {}", userCustomRegister);
        UserCustomDTO userCustom = userCustomService.save(new UserCustomDTO(userCustomRegister));
        return new ResponseEntity<UserCustomDTO>(userCustom, HttpStatus.CREATED);
    }

    /**
     * {@code POST  /login} : login userCustom.
     *
     * @param userCustomLogin the userCustom.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCustomDTO, or with status {@code 404 (Not Found)}.
     */
    @Operation(summary = "login user", description = "Login User")
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserCustomLogin userCustomLogin) throws UsernameNotFoundException {
        log.debug("REST request to create UserCustom : {}", userCustomLogin);
        Authentication authentication =
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCustomLogin.getUsername(), userCustomLogin.getPassword())
                );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        JwtToken jwtToken = new JwtToken(jwtTokenProvider.generateJwtToken(userPrincipal));
        httpHeaders.add("JWT_TOKEN", jwtToken.getToken());
        return new ResponseEntity<JwtToken>(jwtToken, httpHeaders, HttpStatus.OK);
    }

    /**
     * {@code Get  /current-user} : current-user userCustom.
     *
     * @param principal the userCustom.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCustomDTO, or with status {@code 404 (Not Found)}.
     */
    @Operation(summary = "Get Current User With Token", description = "Get Current User With Token")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/current-user")
    public ResponseEntity<?> currentUser(Principal principal) throws UsernameNotFoundException {
        log.info("REST request to get current user : {}", principal);

        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
    }
}
