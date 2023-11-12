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

    @GetMapping("/pending/{userId}")
    public List<Paper> getPendingPapers(@PathVariable Long userId) {
        // 获取用户待评审的论文，可以根据用户ID和状态来筛选
        return paperRepository.findByReviewerIdAndStatus(userId, "Submitted");
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

    @PostMapping("/sendInvitation/{paperId}")
    public String sendInvitation(@PathVariable Long paperId, @RequestParam("reviewerId") Long reviewerId) {
        // 发送审稿邀请消息
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new PaperNotFoundException("Paper not found with id: " + paperId));
        return reviewerId + "邀请你评审轮文\n" + paper.getTitle();
    }
}
