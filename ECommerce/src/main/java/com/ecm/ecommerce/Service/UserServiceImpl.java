package com.ecm.ecommerce.Service;

import com.ecm.ecommerce.Exception.EcException;
import com.ecm.ecommerce.Model.DTOs.AdminGetIdDTO;
import com.ecm.ecommerce.Model.DTOs.GetEmailDTO;
import com.ecm.ecommerce.Model.Role;
import com.ecm.ecommerce.Model.User;
import com.ecm.ecommerce.Model.UserResponseDTO;
import com.ecm.ecommerce.Repository.UserRepository;
import com.ecm.ecommerce.Security.Configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmailOrderById(String email) {
        return userRepository.findByEmailOrderById(email);
    }

    @Override
    public AdminGetIdDTO findUserIdByEmail(GetEmailDTO emailDto) {
        var email = getByEmailOrderById(emailDto.getEmail()).getId();
        return AdminGetIdDTO.builder().id(email.toString()).build();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email, String request) throws EcException {
        final String jwt = request.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new EcException(HttpStatus.NOT_FOUND);
        } else if (!Objects.equals(email, userEmail)) {
            throw new EcException(HttpStatus.UNAUTHORIZED);
        }
        userRepository.delete(user.get());
    }

    @Override
    public List<UserResponseDTO> findAllUsers(String request) throws EcException {

        final String jwt = request.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);
        User user = userRepository.findByEmailOrderById(userEmail);

        List<UserResponseDTO> users = new ArrayList<>();

        if (user == null) {
            throw new EcException(HttpStatus.NOT_FOUND);
        } else if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ADMIN"))) {
            for (int i = 0; i < userRepository.findAll().size(); i++) {
                users.add(
                        UserResponseDTO.builder().firstName(userRepository.findAll().get(i).getFirstName())
                                .lastName(userRepository.findAll().get(i).getLastName())
                                .email(userRepository.findAll().get(i).getEmail()).build());
            }
        } else {
            throw new EcException(HttpStatus.UNAUTHORIZED);
        }

        return users;
    }

    @Override
    public boolean isAdmin(Long userId) throws EcException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EcException(HttpStatus.NOT_FOUND));
        Role userRole = user.getRoles().stream().filter(role -> role.getRoleName().equals("ADMIN"))
                .findFirst().orElse(null);
        return userRole != null;
    }

    @Override
    public void deleteUserByIdByAdmin(Long id, Long adminId) throws EcException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EcException(HttpStatus.NOT_FOUND));
        if (user.getId().equals(adminId) || isAdmin(adminId)) {
            userRepository.delete(user);
        } else {
            throw new EcException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void deleteUserById(Long userId) throws EcException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new EcException(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userOptional.get());
    }


}
