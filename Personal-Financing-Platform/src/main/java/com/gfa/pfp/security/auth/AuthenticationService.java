package com.gfa.pfp.security.auth;

import com.gfa.pfp.exception.EmailIsMissingException;
import com.gfa.pfp.exception.ExceptionMessage;
import com.gfa.pfp.exception.PasswordIsMissingException;
import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.exception.UserAlreadyExistsException;
import com.gfa.pfp.models.dto.authentication.AuthenticationResponseDTO;
import com.gfa.pfp.models.dto.authentication.LoginDTO;
import com.gfa.pfp.models.dto.authentication.RegisterDTO;
import com.gfa.pfp.models.entities.user.RoleType;
import com.gfa.pfp.models.entities.user.User;
import com.gfa.pfp.repositories.UserRepository;
import com.gfa.pfp.security.config.JwtService;
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

  public AuthenticationResponseDTO createUser(RegisterDTO request) throws PfpException {
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

  public AuthenticationResponseDTO authenticate(LoginDTO request) throws PfpException {

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