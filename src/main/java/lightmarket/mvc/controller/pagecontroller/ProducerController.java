package lightmarket.mvc.controller.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProducerController {

    @GetMapping("/producer")
    public ModelAndView produce(){
        ModelAndView modelAndView = new ModelAndView("producer");
        return modelAndView;
    }
}
