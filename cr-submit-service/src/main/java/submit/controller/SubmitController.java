package submit.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import submit.entity.Paper;
import submit.entity.PaperDto;
import submit.exception.PaperNotFoundException;
import submit.repository.PaperRepository;
import submit.service.SubmitService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class SubmitController {
    private final PaperRepository repository;

    public SubmitController(PaperRepository repository) {
        this.repository = repository;
    }

    @Autowired
    SubmitService service;

    // 查找全部Paper
    @GetMapping("/paper/search/all")
    List<Paper> all() {
        return repository.findAll();
    }

    @GetMapping("/paper/search/conferenceid/{conference_id}")
    List<Paper> searchByConferenceId(@PathVariable String conference_id) {
        List<Paper> res =  repository.findByConferenceId(conference_id);
        if (res.isEmpty()) {
            throw new PaperNotFoundException(conference_id);
        }
        return res;
    }

    @GetMapping("/paper/search/paperid/{paper_id}")
    Optional<Paper> searchByPaperId(@PathVariable String paper_id) {
        Optional<Paper> res =  repository.findByPaperId(paper_id);
        if (res.isEmpty()) {
            throw new PaperNotFoundException(paper_id);
        }
        return res;
    }

    //新建论文
    @PostMapping("/paper/new")
    String newPcmember(@RequestBody PaperDto dto) {
        return service.submitPaper(dto);
    }


    //上传论文
    @PostMapping("/paper/new/{paper_id}")
    @ResponseBody
    public String uploadPaperByPaperId(@RequestPart MultipartFile file, @PathVariable String paper_id) throws IOException {
        return service.uploadPaperByPaperId(file,paper_id);
    }

    /**
     * @param paper_id     想要下载的paper的paper id
     * @param response
     * @功能描述 下载文件:
     */
    //下载论文
    @GetMapping("/paper/download/{paper_id}")
    public String  download(@PathVariable String paper_id, HttpServletResponse response) {
        return service.downloadPaperByPaperId(paper_id,response);
    }


}
