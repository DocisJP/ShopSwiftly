package c15_23_m_java_react.com.product_service.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    private Long userID;

    @OneToMany
    @JoinColumn
    private Set<ShoppingLine> shoppingLines;

}
