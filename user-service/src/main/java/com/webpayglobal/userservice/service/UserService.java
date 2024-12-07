package com.webpayglobal.userservice.service;

import com.webpayglobal.userservice.dto.UserRegistrationDto;
import com.webpayglobal.userservice.exception.EmailAlreadyExistsException;
import com.webpayglobal.userservice.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> registerUser(UserRegistrationDto registrationDto) throws EmailAlreadyExistsException;

    String authenticateUser(String email, String password);

    void verifyEmail(String token);

    void enableGoogleAuth(Long userId, String secret);
}