package none.service;

import java.util.List;
import java.util.Optional;
import none.exceptions.EmailAlreadyExistException;
import none.exceptions.UsernameAlreadyExistException;
import none.exceptions.UsernameNotFoundException;
import none.service.dto.UserCustomDTO;

/**
 * Service Interface for managing {@link none.service.dto.UserCustomDTO}.
 */
public interface UserCustomService {
    /**
     * Save a userCustom.
     *
     * @param userCustomDTO the entity to save.
     * @return the persisted entity.
     */
    UserCustomDTO save(UserCustomDTO userCustomDTO)
        throws UsernameAlreadyExistException, EmailAlreadyExistException, UsernameNotFoundException;

    /**
     * Updates a userCustomDTO.
     *
     * @param userCustomDTO the entity to update.
     * @return the persisted entity.
     */
    UserCustomDTO update(UserCustomDTO userCustomDTO);

    /**
     * Partially updates a userCustom.
     *
     * @param userCustomDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserCustomDTO> partialUpdate(UserCustomDTO userCustomDTO);

    /**
     * Get all the userCustoms.
     *
     * @return the list of entities.
     */
    List<UserCustomDTO> findAll();

    /**
     * Get the "id" userCustom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserCustomDTO> findOne(Long id);

    /**
     * Get the "email" userCustom.
     *
     * @param email the id of the entity.
     * @return the entity.
     */
    Optional<UserCustomDTO> findOneByEmail(String email);

    /**
     * Get the "username" userCustom.
     *
     * @param username the username of the entity.
     * @return the entity.
     */
    Optional<UserCustomDTO> findOneByUsername(String username) throws UsernameNotFoundException;

    /**
     * Delete the "id" userCustom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
