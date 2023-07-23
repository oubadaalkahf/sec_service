package org.sid.sec_service.entities;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sid.sec_service.repositories.UserRepository;
import org.sid.sec_service.security.JwtService;
import org.sid.sec_service.web.AddRoleToUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final JwtService jwtService;
     private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
var user = User.builder()
        .name(registerRequest.getName())
        .email(registerRequest.getEmail())
        .phoneNumber(registerRequest.getPhoneNumber())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Role.USER)
        .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getPhoneNumber(),
                        authenticationRequest.getPassword())
        );
        var user = userRepository.findByPhoneNumber(authenticationRequest.getPhoneNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    public String addRole(AddRoleToUserRequest addRoleToUserRequest) {
        User user = userRepository.findByPhoneNumber(addRoleToUserRequest.getPhoneNumber()).orElseThrow();
        user.setRole(Role.RESTAURANT);
        userRepository.save(user);
        return "Role Added";
    }
}
