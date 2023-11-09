package person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/add")
    public String add() {
        return "addStudent";
    }

    @GetMapping("/update")
    public String update() {
        return "showStudent";
    }

    @GetMapping("/show")
    public String showALL() {
        return "showAll";
    }

    @GetMapping("/search")
    public String select() {
        return "searchStudent";
    }



}