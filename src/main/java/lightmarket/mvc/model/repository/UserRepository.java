package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = :email")
    User findByEmail(@Param("email") String email);
}
