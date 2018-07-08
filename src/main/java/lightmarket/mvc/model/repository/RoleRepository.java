package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM role WHERE role = :role_name")
    Role getRoleByName(@Param("role_name") String roleName);
}
