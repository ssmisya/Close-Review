package CloseReview.submit;

import CloseReview.paper.Paper;
import CloseReview.repository.PaperRepository;
import CloseReview.submit.Exception.PaperNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/papers")
public class PaperSubmitController {

    private final PaperRepository paperRepository;

    private static final String UPLOAD_DIR = "uploads";

    public PaperSubmitController(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }


    @PostMapping("/upload")
    public Paper submitPaper(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("email") String email, @RequestParam("organization") String organization) throws IOException {
        // 保存上传的PDF文件到服务器
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
        Files.write(path, bytes);

        // 保存论文信息到数据库
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setAuthor(author);
        paper.setPdfPath(path.toString()); // 存储PDF文件的路径
        paper.setEmail(email);
        paper.setOrganization(organization);
        paper.setStatus("Submitted");
        paper.setNotificationResult("None");
        return paperRepository.save(paper);
    }

    @GetMapping("/getAll")
    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    @GetMapping("/{id}")
    public Paper getPaperById(@PathVariable Long id) {
        return paperRepository.findById(id)
                .orElseThrow(() -> new PaperNotFoundException("Paper not found with id: " + id));
    }

    @GetMapping("/status")
    public String getPaperStatusByTitle(@RequestParam String title) {
        Paper paper = paperRepository.findByTitle(title);
        if (paper != null) {
            return paper.getStatus();
        } else {
            throw new PaperNotFoundException("Paper not found with title: " + title);
        }
    }

    @GetMapping("/status")
    public String getPaperNotificationResultByTitle(@RequestParam String title) {
        Paper paper = paperRepository.findByTitle(title);
        if (paper != null) {
            return paper.getNotificationResult();
        } else {
            throw new PaperNotFoundException("Paper not found with title: " + title);
        }
    }
}
