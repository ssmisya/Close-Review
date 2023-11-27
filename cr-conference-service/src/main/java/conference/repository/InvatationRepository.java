package conference.repository;

import conference.entity.Conference;
import conference.entity.Invatation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvatationRepository extends JpaRepository<Invatation,Long> {

    List<Invatation> findByConferenceId(String conferenceId);

    List<Invatation> findByUserName(String userName);


    @Transactional
    @Modifying
    @Query("DELETE FROM Invatation i WHERE i.userName = ?1 AND i.conferenceId = ?2")
    void deleteByConferenceIdAndUserName(String userName,String conferenceId);

    @Query("SELECT i FROM Invatation i WHERE i.conferenceId = ?2 AND i.userName = ?1")
    Optional<Invatation> findByConferenceIdAndUserName(String userName,String conferenceId);

    @Modifying
    void deleteById(String id);

    Optional<Invatation> findById(Long Id);
}
