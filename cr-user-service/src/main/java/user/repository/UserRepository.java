package user.repository;

import user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author star
 */
public interface
UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserName(String userName);

    @Modifying
    void deleteByUserName(String userName);

    @Modifying
    Optional<User> findById(Long userId);
}
