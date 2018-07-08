package lightmarket.mvc.service.jpa;


import lightmarket.mvc.model.domain.Role;
import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

//    private final String OWN_USER_ROLE = "USER";
//    private final Role USER_ROLE = roleService.getByRoleName(OWN_USER_ROLE);

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void createUser(User user) {
        Role role = roleService.getByRoleName("USER");

        user.getRoles().add(role);
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
    }
}
