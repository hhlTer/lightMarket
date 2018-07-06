package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.controller.service.sequrity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private SecurityService securityService;

    ModelAndView securityModelGenerate(String uri){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("auth from MainController: " + authentication);

        return securityService.getSecurityModel(uri, authentication);
    }
}
