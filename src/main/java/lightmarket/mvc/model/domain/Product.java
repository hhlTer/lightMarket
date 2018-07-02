package lightmarket.mvc.model.domain;

import javax.persistence.*;

@Entity(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String productName;

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

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProducer(Producer producer) {
        this.producer = producer;
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
