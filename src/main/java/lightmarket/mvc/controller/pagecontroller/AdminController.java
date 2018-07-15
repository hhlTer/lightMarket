package lightmarket.mvc.controller.pagecontroller;

import lightmarket.mvc.controller.service.sequrity.UserValidateService;
import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.domain.Product;
import lightmarket.mvc.model.domain.User;
import lightmarket.mvc.service.jpa.ProducerService;
import lightmarket.mvc.service.jpa.ProductService;
import lightmarket.mvc.service.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidateService userValidateService;

    @GetMapping("/admin/register")
    public String showRegisterPage(){
        return "/admin/register";
    }

    @PostMapping("/admin/register")
    public ModelAndView register(
            @ModelAttribute User user
    ){
        UserValidateService.ValidateForm result = userValidateService.validate(user);

        if (result == UserValidateService.ValidateForm.ok){
            userService.createUser(user);
            return new ModelAndView("redirect:/login");
        } else {
            ModelAndView modelAndView = new ModelAndView("/admin/register");
            modelAndView.addObject("error", result.getDescription());
            return modelAndView;
        }
    }

    /**
     * Update producer
     */
    @GetMapping("/admin/producer/edit")
    public ModelAndView showProducerEditPage(
            @RequestParam String producerId
    ){
        Long longId = Long.parseLong(producerId);
        Producer producer = producerService.getProducerById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/producer/edit");
        modelAndView.addObject("producer", producer);

        return modelAndView;
    }

    @PostMapping("/admin/producer/edit")
    public String update(
            @RequestParam String producerId,
            @ModelAttribute Producer producer
    ){
        Long id = Long.parseLong(producerId);
        producer.setId(id);

        producerService.update(producer);
        return "redirect:/producer";
    }

    /**
     * Delete producer
     */
    @GetMapping("/admin/producer/delete")
    public ModelAndView showProducerDeletePage(
            @RequestParam String producerId
    ){
        Long longId = Long.parseLong(producerId);
        Producer producer = producerService.getProducerById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/producer/delete");
        modelAndView.addObject("producer", producer);

        return modelAndView;
    }

    @PostMapping("/admin/producer/delete")
    public String delete(
            @RequestParam String producerId
    ){
        Long id = Long.parseLong(producerId);

        producerService.deleteProducerById(id);
        return "redirect:/producer";
    }

    /**
     * Create producer
     */

    @GetMapping("/admin/producer/create")
    public ModelAndView showProducerCreatePage(){
        ModelAndView modelAndView = new ModelAndView("/admin/producer/create");
        return modelAndView;
    }

    @PostMapping("/admin/producer/create")
    public String create(
            @ModelAttribute Producer producer
    ){
        producerService.createProducer(producer);
        return "redirect:/producer";
    }

    /**
     ***************** CRUD product
     */

    /**
     * Update product
     */
    @GetMapping("/admin/product/edit")
    public ModelAndView showProductEditPage(
            @RequestParam String productId
    ){
        Long longId = Long.parseLong(productId);
        Product product = productService.getProductById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("producerList", producerService.getAll());

        return modelAndView;
    }

    @PostMapping("/admin/product/edit")
    public String update(
            @RequestParam String productId,
            @RequestParam String producerId,
            @ModelAttribute Product product
    ){
        long producerLongId = Long.parseLong(producerId.split(" ")[0]);

        Long id = Long.parseLong(productId);
        product.setId(id);
        product.setProducer(producerService.getProducerById(producerLongId));

        productService.createProduct(product);
        return "redirect:/product";
    }

    /**
     * Delete product
     */
    @GetMapping("/admin/product/delete")
    public ModelAndView showProductDeletePage(
            @RequestParam String productId
    ){
        Long longId = Long.parseLong(productId);
        Product product = productService.getProductById(longId);

        ModelAndView modelAndView = new ModelAndView("/admin/product/delete");
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @PostMapping("/admin/product/delete")
    public String deleteProduct(
            @RequestParam long productId
    ){

        productService.deleteProductById(productId);
        return "redirect:/product";
    }

    /**
     * Create product
     */

    @GetMapping("/admin/product/create")
    public ModelAndView showProductCreatePage(){
        ModelAndView modelAndView = new ModelAndView("/admin/product/create");

        List<Producer> producers = producerService.getAll();
        modelAndView.addObject("producerList", producers);

        return modelAndView;
    }

    @PostMapping("/admin/product/create")
    public String create(
            @RequestParam String producerId,
            @ModelAttribute Product product
    ){

        producerId = producerId.split(" ")[0];
        long id = Long.parseLong(producerId);
        Producer producer = producerService.getProducerById(id);

        product.setProducer(producer);

        productService.createProduct(product);
        return "redirect:/product";
    }

    /**
     * **************************  User  ******************************
     */

    private final int COUNT_USERS_ON_PAGE = 10;
    @GetMapping("/admin/user")
    public ModelAndView user(@RequestParam(required = false, defaultValue = "0") int pageNumber){
        ModelAndView modelAndView = new ModelAndView("/admin/user/user");

        List<User> userList = userService.getOffset(pageNumber, COUNT_USERS_ON_PAGE);
        modelAndView.addObject("pageNumber", pageNumber);
        modelAndView.addObject("userList", userList);

        Map<Long, Integer> indices = initIndices(userList, pageNumber);
        modelAndView.addObject("indices", indices);

        modelAndView.addObject("pagesCount", getCountOfPage(userList.size()));

        return modelAndView;
    }

    /**
     * Delete
     */

    @GetMapping("admin/user/delete")
    public ModelAndView showDeleteUserDialog(
            @RequestParam long userId
    ){
        ModelAndView modelAndView = new ModelAndView("/admin/user/delete");
        User user = userService.getById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("admin/user/delete")
    public String deleteUser(
            @RequestParam long userId
    ){
        userService.deleteUserById(userId);
        return "redirect:/admin/user";
    }

    /**
     * ****************** Edit user
     * @param userId
     * @return
     */
    @GetMapping("admin/user/edit")
    public ModelAndView showEditDialog(
            @RequestParam long userId
    ){
        ModelAndView modelAndView = new ModelAndView("admin/user/edit");
        User user = userService.getById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("admin/user/edit")
    public String editUser(
            @RequestParam long userId,
            @ModelAttribute User user
    ){
        user.setId(userId);
        userService.save(user);
        return "redirect:/admin/user";
    }
    /**
     * Activation invert
     */

    @GetMapping("/admin/user/invertActivation")
    public String invertActive(
            @RequestParam long userId
    ){
        User user = userService.getById(userId);
        int active = user.getActive() == 0 ? 1 : 0;
        user.setActive(active);
        userService.save(user);
        return "redirect:/admin/user";
    }

    private Map<Long, Integer> initIndices(List<User> userList, int pageNumber){
        Map<Long, Integer> result = new HashMap<>();

        int num = pageNumber*COUNT_USERS_ON_PAGE + 1;
        for (User u:
             userList) {
            result.put(u.getId(), num++);
        }

        return result;
    }

    private int getCountOfPage(long countOfNotes){
        return countOfNotes/COUNT_USERS_ON_PAGE +
                countOfNotes%COUNT_USERS_ON_PAGE > 0 ? 1 : 0;
    }

    /**
     *
     */
}
