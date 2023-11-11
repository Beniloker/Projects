package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.Exception.EmsException;
import com.example.employeemanagementsystem.Model.DTOs.*;
import com.example.employeemanagementsystem.Model.UserResponseDTO;
import com.example.employeemanagementsystem.Security.Authentication.AuthenticationService;
import com.example.employeemanagementsystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/serv/register")
    public ResponseEntity<AuthenticationResponseDTO> registerUser(@RequestBody RegisterDTO request)
            throws EmsException {
        return ResponseEntity.ok(authenticationService.createUser(request));
    }

    @DeleteMapping("/user/delete/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String email,
                                               @RequestHeader String authorization)
            throws EmsException {
        userService.deleteUserByEmail(email, authorization);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/delete/{adminId}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable("id") Long id,
                                               @PathVariable("adminId") Long adminId)
            throws EmsException {
        userService.deleteUserByIdByAdmin(id, adminId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/serv/login")
    public ResponseEntity<AuthenticationResponseDTO> loginUser(@RequestBody LoginDTO loginDto)
            throws EmsException {
        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }

    @PostMapping("/admin/findId")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminGetIdDTO> findUserId(@RequestBody GetEmailDTO emailDto) {
        return ResponseEntity.ok(userService.findUserIdByEmail(emailDto));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> showAllUsers(@RequestHeader String authorization)
            throws EmsException {
        return ResponseEntity.ok().body(userService.findAllUsers(authorization));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader String authorization) {
        return ResponseEntity.ok().body(authenticationService.logout(authorization));
    }
}