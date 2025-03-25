package com.suhas.UserPageVerification.user.security.service;

import com.suhas.UserPageVerification.user.models.User;
import com.suhas.UserPageVerification.user.security.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void UpdateUserRole(Long id,String roleName);
    UserDTO getUserById(Long id);
}
