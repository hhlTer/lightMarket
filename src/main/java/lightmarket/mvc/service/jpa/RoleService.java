package lightmarket.mvc.service.jpa;

import lightmarket.mvc.model.domain.Role;
import lightmarket.mvc.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getByRoleName(String roleName){
        return roleRepository.getRoleByName(roleName);
    }
}
