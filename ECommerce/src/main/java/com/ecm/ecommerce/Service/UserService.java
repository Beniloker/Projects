package com.ecm.ecommerce.Service;



import com.ecm.ecommerce.Exception.EcException;
import com.ecm.ecommerce.Model.DTOs.AdminGetIdDTO;
import com.ecm.ecommerce.Model.DTOs.GetEmailDTO;
import com.ecm.ecommerce.Model.User;
import com.ecm.ecommerce.Model.UserResponseDTO;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User findById(Long id);

    User getByEmailOrderById(String email);

    AdminGetIdDTO findUserIdByEmail(GetEmailDTO emailDto);

    User updateUser(User user);

    boolean isAdmin(Long userId) throws EcException;

    void deleteUserById(Long userId) throws EcException;

    void deleteUserByIdByAdmin(Long id, Long adminId) throws EcException;

    void deleteUserByEmail(String email, String request) throws EcException;

    List<UserResponseDTO> findAllUsers(String request) throws EcException;

}
