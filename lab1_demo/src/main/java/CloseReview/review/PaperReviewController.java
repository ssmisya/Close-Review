package CloseReview.review;

import CloseReview.paper.Paper;
import CloseReview.repository.PaperRepository;
import CloseReview.review.Exception.PaperNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class PaperReviewController {

    private final PaperRepository paperRepository;

    @Autowired
    public PaperReviewController(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @GetMapping("/pending")
    public List<Paper> getPendingPapers() {
        // 获取待评审的论文，可以根据状态来筛选
        return paperRepository.findByStatus("Submitted");
    }

    @PostMapping("/submitReview/{id}")
    public Paper submitReview(@PathVariable Long id, @RequestParam("reviewResult") String reviewResult) {
        // 提交论文评审结果
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new PaperNotFoundException("Paper not found with id: " + id));

        paper.setNotificationResult(reviewResult);
        // 更新状态为"Reviewed" 或其他合适的状态

        return paperRepository.save(paper);
    }

    @GetMapping("/{id}")
    public Paper getReviewResult(@PathVariable Long id) {
        // 获取论文评审结果
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new PaperNotFoundException("Paper not found with id: " + id));

        return paper;
    }
}
