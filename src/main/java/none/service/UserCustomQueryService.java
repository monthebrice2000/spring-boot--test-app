/*
package none.service;

import java.util.List;

import none.domain.UserCustom;
import none.repository.UserCustomRepository;
import none.service.criteria.UserCustomCriteria;
import none.service.dto.UserCustomDTO;
import none.service.mapper.UserCustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

*/
/**
 * Service for executing complex queries for {@link UserCustom} entities in the database.
 * The main input is a {@link UserCustomCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserCustom} or a {@link Page} of {@link UserCustom} which fulfills the criteria.
 *//*

@Service
@Transactional(readOnly = true)
public class UserCustomQueryService extends QueryService<UserCustom> {

    private final Logger log = LoggerFactory.getLogger(UserCustomQueryService.class);

    private final UserCustomRepository userCustomRepository;

    private final UserCustomMapper userCustomMapper;

    public UserCustomQueryService(UserCustomRepository userCustomRepository, UserCustomMapper userCustomMapper) {
        this.userCustomRepository = userCustomRepository;
        this.userCustomMapper = userCustomMapper;
    }

    */
/**
 * Return a {@link List} of {@link UserCustom} which matches the criteria from the database.
 * @param criteria The object which holds all the filters, which the entities should match.
 * @return the matching entities.
 *//*

    @Transactional(readOnly = true)
    public List<UserCustomDTO> findByCriteria(UserCustomCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UserCustom> specification = createSpecification(criteria);
        return userCustomMapper.toDto(userCustomRepository.findAll(specification));
    }

    */
/**
 * Return a {@link Page} of {@link UserCustom} which matches the criteria from the database.
 * @param criteria The object which holds all the filters, which the entities should match.
 * @param page The page, which should be returned.
 * @return the matching entities.
 *//*

    @Transactional(readOnly = true)
    public Page<UserCustomDTO> findByCriteria(UserCustomCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UserCustom> specification = createSpecification(criteria);
        return userCustomRepository.findAll(specification, page).map(userCustomMapper::toDto);
    }

    */
/**
 * Return the number of matching entities in the database.
 * @param criteria The object which holds all the filters, which the entities should match.
 * @return the number of matching entities.
 *//*

    @Transactional(readOnly = true)
    public long countByCriteria(UserCustomCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UserCustom> specification = createSpecification(criteria);
        return userCustomRepository.count(specification);
    }

    */
/**
 * Function to convert {@link UserCustomCriteria} to a {@link Specification}
 * @param criteria The object which holds all the filters, which the entities should match.
 * @return the matching {@link Specification} of the entity.
 *//*

    protected Specification<UserCustom> createSpecification(UserCustomCriteria criteria) {
        Specification<UserCustom> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UserCustomDTO.id));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), UserCustom_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), UserCustom_.lastName));
            }
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), UserCustom_.username));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), UserCustom_.email));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), UserCustom_.password));
            }
            if (criteria.getRoles() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoles(), UserCustom_.roles));
            }
            if (criteria.getAuthorities() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorities(), UserCustom_.authorities));
            }
            if (criteria.getIsNotLocked() != null) {
                specification = specification.and(buildSpecification(criteria.getIsNotLocked(), UserCustom_.isNotLocked));
            }
            if (criteria.getIsActived() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActived(), UserCustom_.isActived));
            }
            if (criteria.getJoinDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJoinDate(), UserCustom_.joinDate));
            }
            if (criteria.getLastLoginDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastLoginDate(), UserCustom_.lastLoginDate));
            }
            if (criteria.getLastLoginDateDisplay() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getLastLoginDateDisplay(), UserCustom_.lastLoginDateDisplay));
            }
        }
        return specification;
    }
}
*/
