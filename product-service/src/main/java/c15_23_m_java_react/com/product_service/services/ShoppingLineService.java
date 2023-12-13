package c15_23_m_java_react.com.product_service.services;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import c15_23_m_java_react.com.product_service.repository.ShoppingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingLineService {
    
    @Autowired
    ShoppingLineRepository shoppingLineRepository;

    //Creamos una nueva shoppingLine
    //El ID del usuario se pasa dentro del cuerpo
    //El producto relacionado se pasa dentro del cuerpo también
    public ResponseEntity<ShoppingLine> createShoppingLine(ShoppingLine shoppingLine) {
        try {
            shoppingLineRepository.save(shoppingLine);
            return new ResponseEntity<>(shoppingLine, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void increaseQuantity(Long lineID){
        try {
            ShoppingLine shoppingLine = shoppingLineRepository.findById(lineID).get();

            Integer quantity = shoppingLine.getQuantity();

            //TODO: hacer comprobación de que no supere el stock
            quantity += 1;
            shoppingLine.setQuantity(quantity);

            shoppingLineRepository.save(shoppingLine);
        }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    public void reduceQuantity(Long lineID) {
        try {
            ShoppingLine shoppingLine = shoppingLineRepository.findById(lineID).get();
            Integer quantity = shoppingLine.getQuantity();

            //Para evitar que vaya a numeros negativos
            if (quantity > 0) {
                quantity -= 1;
                shoppingLine.setQuantity(quantity);
            }

            shoppingLineRepository.save(shoppingLine);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<ShoppingLine> getAllShoppingLines() {
        return shoppingLineRepository.findAll();
    }


    public ResponseEntity<List<ShoppingLine>> getAllShoppingLinesByUserID(Long userID) {
        List<ShoppingLine> shoppingLines = shoppingLineRepository.getAllShoppingLinesByUserID(userID);
        return new ResponseEntity<>(shoppingLines, HttpStatus.FOUND);
    }
}
