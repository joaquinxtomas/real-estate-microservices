package com.jtp.spring_microservice_2_shopping.service;

import com.jtp.spring_microservice_2_shopping.model.Shop;
import com.jtp.spring_microservice_2_shopping.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop saveShop(Shop shop){
        shop.setDateOfBuy(LocalDateTime.now());

        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> findAllShopsOfUser(Long userId){
        return shopRepository.findAllByUserId(userId);
    }
}
