package com.authenticationauthorizationservice.Service;

import com.authenticationauthorizationservice.Feign.UserFeign;
import com.authenticationauthorizationservice.Model.JWTRequest;
import com.authenticationauthorizationservice.Model.UserDto;
import com.authenticationauthorizationservice.Model.UserWithOutPassword;
import com.authenticationauthorizationservice.Repository.AuthorisationRepository;
import com.authenticationauthorizationservice.Utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceAuthentication {
    @Autowired
    private AuthorisationRepository authorisationRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserFeign userFeign;


    public String login(JWTRequest jwtRequest) throws Exception {
        try {
            JWTRequest jwtReq = authorisationRepository.findByemail(jwtRequest.getEmail());
            if (jwtReq.getPassword().equals(jwtRequest.getPassword())) {
                String token = jwtUtil.generateToken(jwtRequest.getEmail());
                return token;
            } else {
                return "Invalid Email Address";
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public UserWithOutPassword signup(UserDto userDto) {
        JWTRequest jwtRequest = new JWTRequest(userDto.getEmail(), userDto.getPassword());
        authorisationRepository.save(jwtRequest);
        userFeign.createUser(userDto);
        return userFeign.createUser(userDto);


    }
}