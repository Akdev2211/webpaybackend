package com.webpayglobal.userservice.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String zipCode;
}