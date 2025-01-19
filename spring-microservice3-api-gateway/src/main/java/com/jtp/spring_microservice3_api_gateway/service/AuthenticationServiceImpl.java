package com.jtp.spring_microservice3_api_gateway.service;

import com.jtp.spring_microservice3_api_gateway.model.User;
import com.jtp.spring_microservice3_api_gateway.security.UserPrincipal;
import com.jtp.spring_microservice3_api_gateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signIn(User signInRequest){

        if(signInRequest == null || signInRequest.getUsername() == null || signInRequest.getPassword() == null){
            throw new IllegalArgumentException("El nombre de usuario o la contraseña no pueden ser nulos");
        }

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User signInUser = userPrincipal.getUser();
        signInUser.setToken(jwt);

        return signInUser;

    }

}
