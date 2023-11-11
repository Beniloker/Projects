package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Exception.EmsException;
import com.example.employeemanagementsystem.Model.DTOs.AdminGetIdDTO;
import com.example.employeemanagementsystem.Model.DTOs.GetEmailDTO;
import com.example.employeemanagementsystem.Model.UserResponseDTO;
import com.example.employeemanagementsystem.Model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User findById(Long id);

    User getByEmailOrderById(String email);

    AdminGetIdDTO findUserIdByEmail(GetEmailDTO emailDto);

    User updateUser(User user);

    boolean isAdmin(Long userId) throws EmsException;

    void deleteUserById(Long userId) throws EmsException;

    void deleteUserByIdByAdmin(Long id, Long adminId) throws EmsException;

    void deleteUserByEmail(String email, String request) throws EmsException;

    List<UserResponseDTO> findAllUsers(String request) throws EmsException;

}
