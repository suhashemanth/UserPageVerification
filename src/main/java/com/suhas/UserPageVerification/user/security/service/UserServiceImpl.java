package com.suhas.UserPageVerification.user.security.service;

import com.suhas.UserPageVerification.user.models.Role;
import com.suhas.UserPageVerification.user.models.User;
import com.suhas.UserPageVerification.user.models.UserRole;
import com.suhas.UserPageVerification.user.repository.RoleRepository;
import com.suhas.UserPageVerification.user.repository.UserRepository;
import com.suhas.UserPageVerification.user.security.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository)
    {
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void UpdateUserRole(Long id, String roleName) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserRole appRole = UserRole.valueOf(roleName);
        Role role = roleRepository.findByRole(appRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found exception"));
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNotLocked(),
                user.isAccountNotExpired(),
                user.isCredentialsNotExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
