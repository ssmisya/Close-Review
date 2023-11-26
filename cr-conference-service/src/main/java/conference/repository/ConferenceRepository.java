package conference.repository;

import conference.entity.Conference;
import conference.entity.PcMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author star
 */
public interface
ConferenceRepository extends JpaRepository<Conference, Long> {

    @Modifying
    List<Conference> findByConferenceName(String conferenceName);

    @Modifying
    Optional<Conference> findByConferenceId(String conferenceId);

    @Modifying
    void deleteByConferenceId(String conferenceId);

    @Modifying
    Optional<Conference> findById(Long userId);

    @Query("SELECT p FROM PcMember  p where p.conferenceId=?1")
    Optional<PcMember> findPcMemberByConferenceId(String conferenceId);

}
