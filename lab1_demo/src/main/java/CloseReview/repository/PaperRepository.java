package CloseReview.repository;


import CloseReview.paper.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    Paper findByTitle(String title);
}
