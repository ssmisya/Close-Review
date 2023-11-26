package submit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import submit.entity.Paper;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author star
 */
public interface
PaperRepository extends JpaRepository<Paper, Long> {


    Optional<Paper> findByPaperId(String conferenceId);

    List<Paper> findByConferenceId(String conferenceId);


    @Modifying
    void deleteByPaperId(String conferenceId);


    Optional<Paper> findById(Long userId);


}
