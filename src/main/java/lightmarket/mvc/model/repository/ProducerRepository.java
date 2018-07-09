package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{

    @Query(nativeQuery = true, value = "UPDATE producer SET producer_name = :pName WHERE id = :id")
    Producer update(
            @Param("pName") String pName,
            @Param("id") long id
    );
}
