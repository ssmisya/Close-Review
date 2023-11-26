package review.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import review.entity.Review;
import review.entity.ReviewAssignDto;
import review.entity.ReviewDto;
import review.repository.ReviewRepository;
import review.service.reviewService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {
    private final ReviewRepository repository;

    @Autowired
    private reviewService service;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }



    // 查找全部review
    @GetMapping("/review/search/all")
    List<Review> all() {
        return repository.findAll();
    }

    //按会议搜索review
    @GetMapping("/review/search/conferenceid/{conference_id}")
    List<Review> searchByConferenceId(@PathVariable String conference_id) {
        List<Review> res =  repository.findByConferenceId(conference_id);
        return res;
    }

    //按paper id 搜索
    @GetMapping("/review/search/paperid/{paper_id}")
    List<Review> searchByPaperId(@PathVariable String paper_id) {
        List<Review> res =  repository.findByPaperId(paper_id);
        return res;
    }

    //查询自己的审稿分配
    @GetMapping("/review/search/username/{user_name}")
    List<Review> searchByUserName(@PathVariable String user_name) {
        List<Review> res =  repository.findByUserName(user_name);
        return res;
    }


    //审稿
    @PostMapping("/review/doreview/")
    String doReview(@RequestParam String paper_id, @RequestParam String user_name, @RequestBody ReviewDto dto){
        return service.doReview(dto,paper_id,user_name);
    }

    //rebuttal
    @PostMapping("/review/dorebuttal/")
    String doRebuttal(@RequestParam String paper_id, @RequestParam String user_name, @RequestBody String rebuttal){
        return service.doRebuttal(rebuttal,paper_id,user_name);
    }

    @GetMapping("/review/reviewresult/{paper_id}")
    String getAcceptResult(@PathVariable String paper_id){
        return service.acceptResult(paper_id);
    }


    //分配稿件
    @PostMapping("/review/assign/")
    Review reviewAssign(@RequestBody ReviewAssignDto dto){
        return repository.save(new Review(dto));
    }



}
