package lightmarket.mvc.controller.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends MainController{

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = securityModelGenerate("index");
        return modelAndView;
    }
}
