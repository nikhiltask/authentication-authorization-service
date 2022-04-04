package com.authenticationauthorizationservice.Feign;

import com.authenticationauthorizationservice.Model.UserDto;
import com.authenticationauthorizationservice.Model.UserWithOutPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service")
public interface UserFeign {
    @PostMapping("/users")
    UserWithOutPassword createUser(UserDto userDto);
}
