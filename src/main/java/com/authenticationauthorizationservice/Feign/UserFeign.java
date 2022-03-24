package com.authenticationauthorizationservice.Feign;

import com.authenticationauthorizationservice.Model.UserDto;
import com.authenticationauthorizationservice.Model.UserWithOutPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {
    @PostMapping("/users")
    UserWithOutPassword createUser(UserDto userDto);
    @GetMapping("/users/getUserByEmail/{emailId}")
    UserWithOutPassword getUserDetailsByEmail(@PathVariable("emailId") String emailId);
}
