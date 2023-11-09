package CloseReview.develop;

import CloseReview.repository.UserRepository;
import CloseReview.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DevelopController {
    private final UserRepository repository;

    DevelopController(UserRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    // 查找全体用户
    @GetMapping("/dev/all")
    List<User> all() {
        return repository.findAll();
    }
}
