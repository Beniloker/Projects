package com.ecm.ecommerce.Security.Authentication;


import com.ecm.ecommerce.Exception.*;
import com.ecm.ecommerce.Model.DTOs.AuthenticationResponseDTO;
import com.ecm.ecommerce.Model.DTOs.LoginDTO;
import com.ecm.ecommerce.Model.DTOs.RegisterDTO;
import com.ecm.ecommerce.Model.RoleType;
import com.ecm.ecommerce.Model.User;
import com.ecm.ecommerce.Repository.UserRepository;
import com.ecm.ecommerce.Security.Configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO createUser(RegisterDTO request) throws EcException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(ExceptionMessage.USER_ALREADY_EXIST.getMessage());
        }
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .join(LocalDate.now()).role(RoleType.USER).build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDTO authenticate(LoginDTO request) throws EcException {

        if (request.getEmail().isEmpty()) {
            throw new EmailIsMissingException();
        } else if (request.getPassword().isEmpty()) {
            throw new PasswordIsMissingException();
        } else if (!authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()))
                .isAuthenticated()) {
            throw new BadCredentialsException("Email or Password is incorrect!");
        }
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDTO logout(String request) {
        jwtService.blacklist(request);
        return AuthenticationResponseDTO.builder().token("You are logged out!").build();
    }

}
