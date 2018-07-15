package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = :email")
    User findByEmail(@Param("email") String email);

    @Query(nativeQuery = true,
            value = "SELECT * FROM user LIMIT :countUser OFFSET :off ")
    List<User> getOffset(
            @Param("countUser") int count_users_on_page,
            @Param("off") int offset);
}
