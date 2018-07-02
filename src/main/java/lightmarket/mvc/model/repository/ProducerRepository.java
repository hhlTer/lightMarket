package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
}
