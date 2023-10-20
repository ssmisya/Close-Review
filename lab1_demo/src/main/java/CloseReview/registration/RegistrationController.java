package CloseReview.registration;

import CloseReview.user.User;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    @Qualifier("H2Datasource")
    private DataSource dataSource;

    private Exception RegistrationFailureException;


    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {

        try {
            userDto.setEnabled(true);
            MyJdbcUserDetailsManager manager = new MyJdbcUserDetailsManager(dataSource);
            manager.createUser(userDto);
        } catch (Exception e) {
            RegistrationFailureException = new Exception(e);
            e.printStackTrace();
            return "registration_error";
        }
        return "login";
    }

    @GetMapping("/registration_error")
    public void RegistrationFailureAdvice(WebRequest request, Model model){
        if(!(RegistrationFailureException == null)){
            model.addAttribute("exception_info", RegistrationFailureException.getMessage());
        }
    }

}
