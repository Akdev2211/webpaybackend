package com.webpayglobal.userservice.dto;

import lombok.Data;

/**
 * Represents a request to log in a user.
 * Contains necessary validation constraints.
 */
@Data
public class UserLoginRequest {

    private String username;
    private String password;

    // Consider extracting shared fields with UserRegistrationDto into a shared interface if needed
}