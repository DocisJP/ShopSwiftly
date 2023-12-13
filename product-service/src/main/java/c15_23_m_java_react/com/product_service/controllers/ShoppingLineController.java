package c15_23_m_java_react.com.product_service.controllers;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import c15_23_m_java_react.com.product_service.services.ShoppingLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
@RequestMapping("/lines")
public class ShoppingLineController {

    @Autowired
    ShoppingLineService shoppingLineService;


    @GetMapping(path = "/{userID}")
    public ResponseEntity<List<ShoppingLine>> getAllShoppingLinesByUserID(@PathVariable Long userID){
        return shoppingLineService.getAllShoppingLinesByUserID(userID);
    }

    @PostMapping(path = "/")
    public ResponseEntity<ShoppingLine> createShoppingLine(@RequestBody ShoppingLine shoppingLine){
        return shoppingLineService.createShoppingLine(shoppingLine);
    }

    @PostMapping(path = "/increase/{lineID}")
    public void increaseQuantity(@PathVariable Long lineID){
        shoppingLineService.increaseQuantity(lineID);
    }

    @PostMapping(path = "/reduce/{lineID}")
    public void reduceQuantity(@PathVariable Long lineID){
        shoppingLineService.reduceQuantity(lineID);
    }

    @GetMapping(path = "/")
    public List<ShoppingLine> getAllShoppingLines(){
        return shoppingLineService.getAllShoppingLines();
    }
}
