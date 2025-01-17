package com.jtp.spring_microservice3_api_gateway.service;

import com.jtp.spring_microservice3_api_gateway.model.Role;
import com.jtp.spring_microservice3_api_gateway.model.User;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    @Transactional
    void changeRole(Role newRole, String username);
}
