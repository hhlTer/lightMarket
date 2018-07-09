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
public class LoginController extends MainController{

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

}
