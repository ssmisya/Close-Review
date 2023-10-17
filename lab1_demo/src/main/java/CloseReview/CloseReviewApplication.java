package CloseReview;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("CloseReview")
public class CloseReviewApplication {

    public static void main(String... args) {
        SpringApplication.run(CloseReviewApplication.class, args);
    }
}