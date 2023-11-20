package user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import user.dto.UserDto;
import user.security.MyJdbcUserDetailsManager;

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
        return "registration_page";
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
        return "login_page";
    }

    @GetMapping("/registration_error")
    public String RegistrationFailureAdvice(WebRequest request, Model model){
        if(!(RegistrationFailureException == null)){
            model.addAttribute("exception_info", RegistrationFailureException.getMessage());
        }
        return "registration_error_page";
    }

}
