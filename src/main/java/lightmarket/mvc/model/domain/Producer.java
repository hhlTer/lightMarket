package lightmarket.mvc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "producer")
public class Producer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "producer_name")
    private String producerName;

    @JsonIgnore
    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> productSet;

    public long getId() {
        return id;
    }

    public String getProducerName() {
        return producerName;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", producerName='" + producerName + '\'' +
                ", productSet=" + productSet +
                '}';
    }
}
