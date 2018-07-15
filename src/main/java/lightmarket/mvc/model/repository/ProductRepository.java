package lightmarket.mvc.model.repository;

import lightmarket.mvc.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE producer_id = :id")
    List<Product> findAllByProducerId(@Param("id") long id);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE producer_id = :producerId LIMIT :countList OFFSET :offset")
    List<Product> findPageProductList(
            @Param("producerId") long producerId,
            @Param("countList") int countList,
            @Param("offset") int offset);

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM product WHERE producer_id = :id")
    long getCountOfProductsByProducerId(
            @Param("id") long producerId);

    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT :count OFFSET :offset")
    List<Product> findAllByOffset(
            @Param("count") int countVisiblePage,
            @Param("offset") int offset);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM product WHERE id=:id")
    void deleteProductById(@Param("id") long productId);
}
