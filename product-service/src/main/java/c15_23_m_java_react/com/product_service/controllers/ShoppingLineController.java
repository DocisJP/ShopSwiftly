package c15_23_m_java_react.com.product_service.controllers;

import c15_23_m_java_react.com.product_service.services.ShoppingLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

@RestController
@RequestMapping("/api/line")
public class ShoppingLineController {

    @Autowired
    ShoppingLineService shoppingLineService;

    @PostMapping(path = "/increase/{lineID}")
    public void increaseQuantity(@PathVariable Long lineID){
        shoppingLineService.increaseQuantity(lineID);
    }

    @PostMapping(path = "/decrease/{lineID}")
    public void reduceQuantity(@PathVariable Long lineID){
        shoppingLineService.reduceQuantity(lineID);
    }
}
