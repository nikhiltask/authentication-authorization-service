package com.authenticationauthorizationservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data

public class UserDto {

    private String userID;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Middle name is required")
    private String middleName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Phone Number is required")
    @Size(min=10,max = 10)
    private String phoneNumber;

    @NotNull(message = "Date of Birth is required")
    private Date dateOfBirth;

    @NotEmpty(message = "Gender is required")
    private String gender;

    @NotEmpty(message = "address is required")
    private String address ;

    @NotEmpty(message = "Employee Number is required")
    private String employeeNumber;

    @NotEmpty(message = "Blood Group is required")
    private String bloodGroup;

    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;



}
