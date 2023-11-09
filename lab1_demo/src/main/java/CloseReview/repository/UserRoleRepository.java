package CloseReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import CloseReview.user.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRoleRepository
 *
 * @author star
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserName(String userName);

    @Modifying
    void deleteByUserName(String userName);
}
