package review.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import review.entity.Review;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author star
 */
public interface
ReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByPaperId(String paperId);


    List<Review> findByConferenceId(String conferenceId);

    List<Review> findByUserName(String userName);

    @Modifying
    void deleteByPaperId(String paperId);

    Optional<Review> findById(Long id);

    @Query("SELECT p FROM Review p WHERE p.paperId=?1 AND p.userName=?2")
    Optional<Review> findReviewByPaperIdAndUserName(String paperId, String userName);

    @Transactional
    @Modifying
    @Query("UPDATE Review p SET p.rebuttal=?3 WHERE p.paperId=?1 AND p.userName=?2")
    Optional<Review> updateReviewByPaperIdAndUserName(String paperId, String userName,String rebuttal);
}
