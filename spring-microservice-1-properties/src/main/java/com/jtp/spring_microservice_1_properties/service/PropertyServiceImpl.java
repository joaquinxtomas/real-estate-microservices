package com.jtp.spring_microservice_1_properties.service;

import com.jtp.spring_microservice_1_properties.model.Property;
import com.jtp.spring_microservice_1_properties.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(Property property){
        property.setCreationDate(LocalDateTime.now());
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long idProperty){
        propertyRepository.deleteById(idProperty);
    }

    @Override
    public List<Property> findAllProperties(){
        return propertyRepository.findAll();
    }


}
