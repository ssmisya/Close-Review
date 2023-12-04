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
PcMemberRepository extends JpaRepository<PcMember, Long> {


    List<PcMember> findByConferenceId(String conferenceId);

    Optional<PcMember> findByUserName(String userName);

    List<PcMember> findByTopic(String topic);

    @Modifying
    void deleteById(Long id);

    Optional<PcMember> findById(Long id);



}