package c15_23_m_java_react.com.product_service.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shopping_line")
public class ShoppingLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "line_id")
    private Long lineID;


    //Indico a que usuario le pertenece
    //El carro de compras se obtiene obteniendo todas las shopping lines que tengan el id del usuario que las pide
    private Long userID;

    //TODO: Realizar relación a producto - Una ShoppingLine tiene un producto - Un producto puede estar en muchas ShoppingLines
    //private Product product;

    private Integer quantity;

    public ShoppingLine() {
    }

    public ShoppingLine(Long lineID, Long userID, Integer quantity) {
        this.lineID = lineID;
        this.userID = userID;
        this.quantity = quantity;
    }

    public Long getLineID() {
        return lineID;
    }

    public void setLineID(Long lineID) {
        this.lineID = lineID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
