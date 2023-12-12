package c15_23_m_java_react.com.product_service.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shopping_line")
public class ShoppingLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "line_id")
    private Long line_id;

    @OneToOne
    private Product product;

    private Integer quantity;

    public ShoppingLine() {
    }

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
