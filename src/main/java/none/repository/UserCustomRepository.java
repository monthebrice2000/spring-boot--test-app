package none.repository;

import java.util.Optional;
import none.domain.UserCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA none.repository for the UserCustom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCustomRepository extends JpaRepository<UserCustom, Long>, JpaSpecificationExecutor<UserCustom> {
    UserCustom findByUsername(String username);

    Optional<UserCustom> findOneByUsername(String username);

    Optional<UserCustom> findOneByEmail(String email);
}
