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

    @Query("SELECT p.paperNum FROM Conference  p where p.conferenceId=?1")
    Long findPaperNumByConferenceId(String conferenceId);

    @Query("SELECT p.status FROM Conference  p where p.conferenceId=?1")
    String findConferenceStatusByConferenceId(String conferenceId);

    @Query("SELECT p.conferenceId FROM Conference  p where p.conferenceName=?1")
    String findConferenceIdByConferenceName(String conferenceName);

    @Query("SELECT p.conferenceId FROM Conference  p where p.shortName=?1")
    String findConferenceIdByShortName(String shortName);

    @Query("UPDATE Conference p SET p.paperNum=?2 where p.conferenceId=?1")
    String upDatePaperNumByConferenceId(String conferenceId, Long paperNum);
}
