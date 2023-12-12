package c15_23_m_java_react.com.product_service.controllers;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import c15_23_m_java_react.com.product_service.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    //Tiene que devolver la shoppingLineID para que la tengan ellos?
    @PostMapping(path = "/line")
    public void addShoppingLine(@RequestBody ShoppingLine shoppingLine){
        shoppingCartService.addShoppingLine(shoppingLine);
    }

    @DeleteMapping(path = "/line/{lineID}")
    public void deleteShoppingLine(){
        shoppingCartService.deleteShoppingLine();
    }
}
