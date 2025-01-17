package com.jtp.spring_microservice_1_properties.service;

import com.jtp.spring_microservice_1_properties.model.Property;

import java.util.List;

public interface PropertyService {
    Property saveProperty(Property property);

    void deleteProperty(Long idProperty);

    List<Property> findAllProperties();
}
