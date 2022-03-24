package com.authenticationauthorizationservice.Repository;

import com.authenticationauthorizationservice.Model.JWTRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorisationRepository extends JpaRepository<JWTRequest,Integer> {

    JWTRequest findByEmail(String email);
}
