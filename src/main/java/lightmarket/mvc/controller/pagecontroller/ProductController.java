package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.domain.Product;
import lightmarket.mvc.service.jpa.ProducerService;
import lightmarket.mvc.service.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController extends MainController{

    private final static int COUNT_VISIBLE_PAGE = 10;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @GetMapping("/product")
    public ModelAndView product(
            @RequestParam(required = false, defaultValue = "-1") long producerId,
            @RequestParam(required = false, defaultValue = "0") String pageNumber
    ){
        ModelAndView modelAndView = securityModelGenerate("products");

        int pageNumberInt = Integer.parseInt(pageNumber);
        List<Product> productList = getProducts(producerId, pageNumberInt);
        Map<Long, Integer> indices = getIndices(productList, pageNumberInt);

        modelAndView.addObject("productList", productList);
        modelAndView.addObject("producerId", producerId);
        modelAndView.addObject("pageNumber", pageNumberInt);
        modelAndView.addObject("pagesCount", getCountOfPages(productService.getCountOfProducts(producerId)));
        modelAndView.addObject("indices", indices);
        return modelAndView;
    }


    @PostMapping("/product/edit")
    public ModelAndView editProduct(){
        return new ModelAndView("/product/edit");
    }

    private int getCountOfPages(long countOfNotes){
        return countOfNotes/COUNT_VISIBLE_PAGE +
               countOfNotes%COUNT_VISIBLE_PAGE > 0 ? 1 : 0;
    }

    private List<Product> getProducts(long producerId, int pageNumber){
//        List<Product> result = new ArrayList<>();
//        Producer producer = producerService.getProducerById(producerId);

        if (producerId >= 0 && producerService.exist(producerId)){
            return productService.getProductListPageByProducerId(producerId, pageNumber, COUNT_VISIBLE_PAGE);
        } else {
            return productService.getAllOffset(pageNumber,COUNT_VISIBLE_PAGE);
        }
    }

    private Map<Long,Integer> getIndices(List<Product> productList, int pageNumber) {
        HashMap<Long, Integer> result = new HashMap<>();

        int count = 0;

        for (Product p:
             productList) {
            result.put(p.getId(), COUNT_VISIBLE_PAGE*pageNumber + ++count);
        }
        return result;
    }

}
