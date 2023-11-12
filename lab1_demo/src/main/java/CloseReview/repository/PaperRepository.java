package CloseReview.repository;


import CloseReview.paper.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByStatus(String status);
    Paper findByTitle(String title);
}
