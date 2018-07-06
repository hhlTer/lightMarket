package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.service.jpa.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProducerController extends MainController{

    @Autowired
    ProducerService producerService;

    @GetMapping("/producer")
    public ModelAndView produce(){
        ModelAndView modelAndView = securityModelGenerate("producer");

//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        System.out.println("authentication " + authentication);


        List<Producer> producerList = producerService.getAll();
        modelAndView.addObject("producers", producerList);

        return modelAndView;
    }
}
