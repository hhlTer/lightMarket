package lightmarket.mvc.controller.service.sequrity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SecurityService {

    public ModelAndView getSecurityModel(String uri, Authentication authenticationz){
//        System.out.println("auth from SequrityService " + authentication);
//        System.out.println("principal: " + authentication.getPrincipal());
//        User user = getUser(authentication);

        User user = getUser();

        ModelAndView modelAndView = new ModelAndView(uri);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", getRolesString(user));
        return modelAndView;
    }


    private User getUser() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null){
            System.out.println("null");
            return null;
        }

        return (User)authentication.getPrincipal();
    }

    private String getRolesString(User user){
        if (user == null){
            return "null";
        }
        Collection<GrantedAuthority> collection = user.getAuthorities();

        System.out.println("collection " + collection);

        String result = collection.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        return result.length() == 0 ? "guest" : result;
    }
}
