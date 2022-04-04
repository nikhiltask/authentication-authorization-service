package com.authenticationauthorizationservice.Controller;

import com.authenticationauthorizationservice.Model.JWTRequest;
import com.authenticationauthorizationservice.Model.UserDto;
import com.authenticationauthorizationservice.Model.UserWithOutPassword;
import com.authenticationauthorizationservice.Service.ServiceAuthentication;
import com.authenticationauthorizationservice.Utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ServiceAuthentication serviceAuthentication;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody JWTRequest jwtRequest) {

        return new ResponseEntity<String>(serviceAuthentication.login(jwtRequest), HttpStatus.OK);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserWithOutPassword> signup(@RequestBody UserDto userDto) {
        // Persist user to some persistent storage
//        System.out.println("Info saved...");

        return new ResponseEntity<>(serviceAuthentication.signup(userDto), HttpStatus.OK);
    }
}
