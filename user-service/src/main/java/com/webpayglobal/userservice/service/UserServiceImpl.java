package com.webpayglobal.userservice.service;

import com.webpayglobal.userservice.dto.UserRegistrationDto;
import com.webpayglobal.userservice.model.User;
import com.webpayglobal.userservice.model.UserStatus;
import com.webpayglobal.userservice.repository.UserRepository;
import com.webpayglobal.userservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    @Transactional
    public Optional<User> registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            // Optionally, you could log or carry out other operations here.
            return Optional.empty(); // Instead of throwing an exception
        }

        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user.setCountry(registrationDto.getCountry());
        user.setState(registrationDto.getState());
        user.setCity(registrationDto.getCity());
        user.setZipCode(registrationDto.getZipCode());
        user.setStatus(UserStatus.PENDING);
        user.setEmailVerified(false);
        user.setGoogleAuthEnabled(false);

        User savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }

    @Override
    public String authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return tokenProvider.generateToken(authentication);
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {
        User user = userRepository.findByEmailVerificationToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        user.setEmailVerified(true);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void enableGoogleAuth(Long userId, String secret) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setGoogleAuthSecret(secret);
        user.setGoogleAuthEnabled(true);
        userRepository.save(user);
    }
}