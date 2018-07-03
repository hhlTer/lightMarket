package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.domain.Product;
import lightmarket.mvc.service.jpa.ProducerService;
import lightmarket.mvc.service.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @GetMapping("/product")
    public ModelAndView product(
            @RequestParam(required = false, defaultValue = "-1") long producerId
    ){
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("productList", getProducts(producerId));
        return modelAndView;
    }

    private List<Product> getProducts(long producerId){
        List<Product> result = new ArrayList<>();
        Producer producer = producerService.getProducerById(producerId);

        if (producerId >= 0 && producerService.exist(producerId)){
            return productService.getProductListByProducer(producer);
        } else {
            return productService.getAll();
        }
    }
}
