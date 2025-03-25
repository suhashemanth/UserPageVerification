package com.suhas.UserPageVerification.user.security.controller;

import com.suhas.UserPageVerification.user.models.User;
import com.suhas.UserPageVerification.user.security.dto.UserDTO;
import com.suhas.UserPageVerification.user.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private UserService userService;

    AdminController(UserService userService)
    {
        this.userService=userService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-role")
    public ResponseEntity<String> updateUser(@RequestParam Long id,@RequestParam String roleName)
    {
        userService.UpdateUserRole(id,roleName);
        return new ResponseEntity<>("Okay",HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getAUser(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

}
