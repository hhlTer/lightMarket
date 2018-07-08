package lightmarket.mvc.service.jpa;


import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
