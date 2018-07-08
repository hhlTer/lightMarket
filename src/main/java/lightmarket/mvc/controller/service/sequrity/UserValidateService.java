package lightmarket.mvc.controller.service.sequrity;

import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.service.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static lightmarket.mvc.controller.service.sequrity.UserValidateService.ValidateForm.*;

@Service
public class UserValidateService {

    public enum ValidateForm{
        ok{
            public String getDescription() {
                return "ok";
            }
        },
        shortPassword{
            public String getDescription() {
                return "password to short";
            }
        },
        wrongEmailFormat{
            public String getDescription() {
                return "wrong email format";
            }
        },
        alreadyEmailExist{
            public String getDescription() {
                return "email already exist";
            }
        };

        public abstract String getDescription();
    }

    @Autowired
    UserService userService;


    public ValidateForm validate(User user){
        if (user.getPassword().length() < 5){
            return shortPassword;
        }

        if (!isValidEmailAddress(user.getEmail())){
            return wrongEmailFormat;
        }

        if (userService.getByEmail(user.getEmail()) != null){
            return alreadyEmailExist;
        }

        return ok;
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
