package com.jtp.spring_microservice_2_shopping.service;

import com.jtp.spring_microservice_2_shopping.model.Shop;

import java.util.List;

public interface ShopService {
    Shop saveShop(Shop shop);

    List<Shop> findAllShopsOfUser(Long userId);
}
