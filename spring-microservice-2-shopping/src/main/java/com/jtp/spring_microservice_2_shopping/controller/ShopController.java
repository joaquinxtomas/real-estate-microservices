package com.jtp.spring_microservice_2_shopping.controller;

import com.jtp.spring_microservice_2_shopping.model.Shop;
import com.jtp.spring_microservice_2_shopping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<?> saveShop(@RequestBody Shop shop){
        return new ResponseEntity<>(shopService.saveShop(shop), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?>  getAllShopsOfUser(@PathVariable Long userId){
        return ResponseEntity.ok(shopService.findAllShopsOfUser(userId));
    }

}
