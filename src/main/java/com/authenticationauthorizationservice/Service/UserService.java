package com.authenticationauthorizationservice.Service;

import com.authenticationauthorizationservice.Model.JWTRequest;
import com.authenticationauthorizationservice.Repository.AuthorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    AuthorisationRepository authorisationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JWTRequest jwtRequest = authorisationRepository.findByEmail(email);

        return new User(jwtRequest.getEmail(), jwtRequest.getPassword(), new ArrayList<>());
    }


}
