package lightmarket.mvc.model.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Transactional
@Entity(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "producer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Producer producer;

    public long getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public Producer getProducer() {
        return producer;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", producer=" + producer +
                '}';
    }
}
