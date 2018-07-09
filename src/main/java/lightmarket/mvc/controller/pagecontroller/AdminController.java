package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.controller.service.sequrity.UserValidateService;
import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.service.jpa.ProducerService;
import lightmarket.mvc.service.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidateService userValidateService;

    @GetMapping("/admin/register")
    public String showRegisterPage(){
        return "/admin/register";
    }

    @PostMapping("/admin/register")
    public ModelAndView register(
            @ModelAttribute User user
    ){
        UserValidateService.ValidateForm result = userValidateService.validate(user);

        if (result == UserValidateService.ValidateForm.ok){
            userService.createUser(user);
            return new ModelAndView("redirect:/login");
        } else {
            ModelAndView modelAndView = new ModelAndView("/admin/register");
            modelAndView.addObject("error", result.getDescription());
            return modelAndView;
        }
    }

    /**
     * Update producer
     */
    @GetMapping("/admin/producer/edit")
    public ModelAndView showProducerEditPage(
            @RequestParam String producerId
    ){
        Long longId = Long.parseLong(producerId);
        Producer producer = producerService.getProducerById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/producer/edit");
        modelAndView.addObject("producer", producer);

        return modelAndView;
    }

    @PostMapping("/admin/producer/edit")
    public String update(
            @RequestParam String producerId,
            @ModelAttribute Producer producer
    ){
        Long id = Long.parseLong(producerId);
        producer.setId(id);

        producerService.update(producer);
        return "redirect:/producer";
    }

    /**
     * Delete producer
     */
    @GetMapping("/admin/producer/delete")
    public ModelAndView showProducerDeletePage(
            @RequestParam String producerId
    ){
        Long longId = Long.parseLong(producerId);
        Producer producer = producerService.getProducerById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/producer/delete");
        modelAndView.addObject("producer", producer);

        return modelAndView;
    }

    @PostMapping("/admin/producer/delete")
    public String delete(
            @RequestParam String producerId
    ){
        Long id = Long.parseLong(producerId);

        producerService.deleteProducerById(id);
        return "redirect:/producer";
    }

}
