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
    @Modifying
    Optional<PcMember> findByConferenceId(String conferenceId);

    @Modifying
    void deleteById(Long Id);

    @Modifying
    Optional<PcMember> findById(Long userId);



}