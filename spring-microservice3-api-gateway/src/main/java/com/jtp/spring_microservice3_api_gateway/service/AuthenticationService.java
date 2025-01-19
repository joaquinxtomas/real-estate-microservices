package com.jtp.spring_microservice3_api_gateway.service;

import com.jtp.spring_microservice3_api_gateway.model.User;

public interface AuthenticationService {
    User signIn(User signInRequest);
}
