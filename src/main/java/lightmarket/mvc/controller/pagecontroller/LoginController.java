package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.controller.service.sequrity.UserValidateService;
import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.service.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidateService userValidateService;

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(
            @ModelAttribute User user
    ){
        UserValidateService.ValidateForm result = userValidateService.validate(user);

        if (result == UserValidateService.ValidateForm.ok){
            userService.createUser(user);
            return new ModelAndView("redirect:/login");
        } else {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("error", result.getDescription());
            return modelAndView;
        }
    }
}
