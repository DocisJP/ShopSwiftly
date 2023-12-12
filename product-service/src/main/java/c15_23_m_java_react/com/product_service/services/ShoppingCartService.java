package c15_23_m_java_react.com.product_service.services;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import c15_23_m_java_react.com.product_service.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ShoppingLineService shoppingLineService;

    //Crea linea de producto y la almacena dentro de su set de referencias
    //Tiene que llamar a shoppingLineRepository para guardarla
    public void addShoppingLine(ShoppingLine shoppingLine) {
       //ShoppingLine shoppingLineCreated = shoppingLineService.createNewShoppingLine(shoppingLine);

    }

    //Eliminar primero del set y luego del SQL
    public void deleteShoppingLine() {
    }
}
