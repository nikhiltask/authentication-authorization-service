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


    public String login(JWTRequest jwtRequest) {
        JWTRequest jwtReq = authorisationRepository.findByemail(jwtRequest.getEmail());
        if (jwtReq.getPassword().equals(jwtRequest.getPassword())){
            String token = jwtUtil.generateToken(jwtRequest.getEmail());
            return token;
        }else {
            return "Invalid Email Address";
        }
    }
    public UserWithOutPassword signup(UserDto userDto){
        JWTRequest jwtRequest =new JWTRequest(userDto.getEmail(),userDto.getPassword());
        authorisationRepository.save(jwtRequest);
        UserWithOutPassword userWithOutPassword1=userFeign.createUser(userDto);
//
//    UserWithOutPassword userWithOutPassword= new UserWithOutPassword(userDto.getUserID(),userDto.getFirstName(),userDto.getMiddleName(),
//            userDto.getLastName(),userDto.getPhoneNumber(),userDto.getDateOfBirth(),userDto.getGender(),userDto.getAddress(),
//            userDto.getEmployeeNumber(),userDto.getBloodGroup(),userDto.getEmail());

        return  userWithOutPassword1;



    }
}