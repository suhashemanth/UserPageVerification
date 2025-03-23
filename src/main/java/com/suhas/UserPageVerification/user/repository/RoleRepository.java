package com.suhas.UserPageVerification.user.repository;

import com.suhas.UserPageVerification.user.models.Role;
import com.suhas.UserPageVerification.user.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Optional<Role> findByRole(UserRole userRole);
}
