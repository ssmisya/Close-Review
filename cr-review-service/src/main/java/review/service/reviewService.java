package review.service;


import org.springframework.stereotype.Service;
import review.entity.Review;
import review.entity.ReviewDto;
import review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class reviewService {

    final ReviewRepository repository;


    public reviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public String doReview(ReviewDto dto,String paperId, String userName){
        Optional<Review> reviewOptional = repository.findReviewByPaperIdAndUserName(paperId,userName);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            if(review.getRebuttalSign()==1||review.getRebuttalSign()>=3) return "review is not available";
            review.setRank(dto.getRank());
            review.setComment(dto.getComment());
            review.setConfidence(dto.getConfidence());
            review.setRebuttalSign(review.getRebuttalSign()+1);
            repository.save(review);
            return "review submit correctly";
        }
        return "no such review";
    }

    public String doRebuttal(String rebuttal,String paperId, String userName){
        Optional<Review> reviewOptional = repository.findReviewByPaperIdAndUserName(paperId,userName);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            if (review.getRebuttalSign()!=1) return "rebuttal is not available";
            review.setRebuttal(rebuttal);
            review.setRebuttalSign(review.getRebuttalSign()+1);
            repository.save(review);
            return "rebuttal submit correctly";
        }
        return "no such review";
    }

    public String acceptResult(String paperId){
        List<Review> reviewList = repository.findByPaperId(paperId);
        if (reviewList.isEmpty()) return  "no such paper";
        for(Review i:reviewList){
            if (i.getRebuttalSign()<3) return "result is not open";
            if (i.getRank()<0) return "Reject";
        }
        return "Accept";
    }
}
