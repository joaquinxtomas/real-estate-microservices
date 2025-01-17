package com.jtp.spring_microservice_1_properties.repository;

import com.jtp.spring_microservice_1_properties.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {



}
