package com.jtp.spring_microservice_2_shopping.repository;

import com.jtp.spring_microservice_2_shopping.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAllByUserId(Long userId);
}
