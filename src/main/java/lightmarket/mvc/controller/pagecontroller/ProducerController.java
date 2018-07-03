package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.service.jpa.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/producer")
    public ModelAndView produce(){
        ModelAndView modelAndView = new ModelAndView("producer");

//        List<Producer> producerList = producerService.getAll();
//        modelAndView.addObject("producers", producerList);

        return modelAndView;
    }
}
