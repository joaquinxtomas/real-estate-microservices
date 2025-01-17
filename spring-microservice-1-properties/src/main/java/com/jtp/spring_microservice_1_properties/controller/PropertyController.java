package com.jtp.spring_microservice_1_properties.controller;

import com.jtp.spring_microservice_1_properties.model.Property;
import com.jtp.spring_microservice_1_properties.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> saveProperty(@RequestBody Property property){
        return new ResponseEntity<>(propertyService.saveProperty(property), HttpStatus.CREATED);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProperties(){
        return ResponseEntity.ok(propertyService.findAllProperties());
    }

}
