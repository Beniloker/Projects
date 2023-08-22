package com.gfa.pfp.services;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.models.dto.admin.AdminGetIdDTO;
import com.gfa.pfp.models.dto.admin.GetEmailDTO;
import com.gfa.pfp.models.entities.user.UserResponseDTO;
import com.gfa.pfp.models.entities.user.User;
import java.util.List;

public interface UserService {

  User findByEmail(String email);

  User findById(Long id);

  User getByEmailOrderById(String email);

  AdminGetIdDTO findUserIdByEmail(GetEmailDTO emailDto);

  User updateUser(User user);

  boolean isAdmin(Long userId) throws PfpException;

  void deleteUserById(Long userId) throws PfpException;

  void deleteUserByIdByAdmin(Long id, Long adminId) throws PfpException;

  void deleteUserByEmail(String email, String request) throws PfpException;

  List<UserResponseDTO> findAllUsers(String request) throws PfpException;

}