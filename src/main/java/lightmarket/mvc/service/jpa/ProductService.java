package lightmarket.mvc.service.jpa;

import lightmarket.mvc.model.domain.Product;
import lightmarket.mvc.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * CRUD
     */

    public Product createProduct(Product product){
        Product result = productRepository.save(product);
        return result;
    }

    public Product getProductById(long productId){
        if (productRepository.existsById(productId)){
            return productRepository.getOne(productId);
        } else{
            return null;
        }
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public boolean deleteProductById(Product product){
        if (productRepository.existsById(product.getId())){
            productRepository.delete(product);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Additional
     */
}
