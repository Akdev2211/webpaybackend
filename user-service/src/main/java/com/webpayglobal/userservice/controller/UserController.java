package com.webpayglobal.userservice.controller;

import com.webpayglobal.userservice.dto.JwtAuthenticationResponse;
import com.webpayglobal.userservice.dto.UserRegistrationDto;
import com.webpayglobal.userservice.model.User;
import com.webpayglobal.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        Optional<User> registeredUser = userService.registerUser(registrationDto);

        if (registeredUser.isPresent()) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok("Email verified successfully");
    }

    @PostMapping("/enable-2fa")
    public ResponseEntity<?> enableTwoFactorAuth(@RequestBody String secret) {
        // Get user ID from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // Assuming userId needs to be extracted from userPrincipal
        Long userId = // Extract user ID from userPrincipal appropriately

                userService.enableGoogleAuth(userId, secret);
        return ResponseEntity.ok("Two-factor authentication enabled successfully");
    }
}