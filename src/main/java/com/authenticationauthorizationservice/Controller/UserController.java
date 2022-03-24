package com.authenticationauthorizationservice.Controller;


import com.authenticationauthorizationservice.Feign.UserFeign;
import com.authenticationauthorizationservice.Model.*;
import com.authenticationauthorizationservice.Repository.AuthorisationRepository;
import com.authenticationauthorizationservice.Service.UserService;
import com.authenticationauthorizationservice.Utility.JWTUtili;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtili jwtUtili;

    @Autowired
    private UserService userService;

    @Autowired
    UserFeign feignUser;

    @Autowired
    AuthorisationRepository authorisationRepo;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                     loginRequest.getPassword()));


        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtili.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token, feignUser.getUserDetailsByEmail(loginRequest.getEmail())));
    }

    @PostMapping("/signup")
    public ResponseEntity<JWTResponse> signup(@RequestBody UserDto userDto) throws Exception {
        JWTRequest jwtRequest = new JWTRequest();
        jwtRequest.setEmail(userDto.getEmail());
        jwtRequest.setPassword(userDto.getPassword());
        authorisationRepo.save(jwtRequest);
        UserWithOutPassword userWithOutPassword = feignUser.createUser(userDto);
        final UserDetails userDetails = new User(userDto.getEmail(),userDto.getPassword(), new ArrayList<>());
        final String token = jwtUtili.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token,userWithOutPassword));
    }
}
