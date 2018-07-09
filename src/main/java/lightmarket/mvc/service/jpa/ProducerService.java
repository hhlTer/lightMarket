package lightmarket.mvc.service.jpa;

import lightmarket.mvc.model.domain.Producer;
import lightmarket.mvc.model.repository.ProducerRepository;
import lightmarket.mvc.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * CRUD
     */

    public Producer createProducer(Producer producer){
        Producer result = producerRepository.save(producer);
        return result;
    }

    public Producer getProducerById(long producerId){
        if (producerRepository.existsById(producerId)){
            return producerRepository.getOne(producerId);
        } else{
            return null;
        }
    }

    public List<Producer> getAll(){
        return producerRepository.findAll();
    }

    public boolean deleteProducerById(Producer producer){
        if (producerRepository.existsById(producer.getId())){
            producerRepository.delete(producer);
            return true;
        } else {
            return false;
        }
    }

    public boolean exist(long producerId) {
        return producerRepository.existsById(producerId);
    }

    public void update(Producer producer){

        String name = producer.getProducerName();
        long id = producer.getId();

//        System.out.println("name = " + name + " Id = " + id);
        producerRepository.save(producer);
//        producerRepository.update(name, id);
    }

    /**
     * Additional
     */
}
