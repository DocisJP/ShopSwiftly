package c15_23_m_java_react.com.product_service.services;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import c15_23_m_java_react.com.product_service.repository.ShoppingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingLineService {
    
    @Autowired
    ShoppingLineRepository shoppingLineRepository;

    public void increaseQuantity(Long lineID) {
        ShoppingLine shoppingLine = shoppingLineRepository.findById(lineID).get();

        Integer quantity = shoppingLine.getQuantity();

        //TODO: hacer comprobación de que no supere el stock
        quantity += 1;
        shoppingLine.setQuantity(quantity);

    }

    public void reduceQuantity(Long lineID) {
        ShoppingLine shoppingLine = shoppingLineRepository.findById(lineID).get();
        Integer quantity = shoppingLine.getQuantity();

        //Para evitar que vaya a numeros negativos
        if (quantity > 0){
            quantity -= 1;
            shoppingLine.setQuantity(quantity);
        }
    }

    /*public ShoppingLine createNewShoppingLine(ShoppingLine shoppingLine) {
        //return shoppingLineRepository.save(shoppingLine);
    }*/
}
