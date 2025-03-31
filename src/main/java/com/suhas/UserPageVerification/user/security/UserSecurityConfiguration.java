package com.suhas.UserPageVerification.user.security;

import com.suhas.UserPageVerification.user.models.Role;
import com.suhas.UserPageVerification.user.models.User;
import com.suhas.UserPageVerification.user.models.UserRole;
import com.suhas.UserPageVerification.user.repository.RoleRepository;
import com.suhas.UserPageVerification.user.repository.UserRepository;
import com.suhas.UserPageVerification.user.security.service.CustomLoggingFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class UserSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
        http.addFilterBefore(new CustomLoggingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository,PasswordEncoder passwordEncoder) {
            return args -> {
                Role userRole = roleRepository.findByRole(UserRole.USER_ROLE)
                        .orElseGet(() -> roleRepository.save(new Role(UserRole.USER_ROLE)));

                Role adminRole = roleRepository.findByRole(UserRole.ADMIN)
                        .orElseGet(() -> roleRepository.save(new Role(UserRole.ADMIN)));
                if (!userRepository.existsByUserName("user1")) {
                    User user1 = new User("user1", "user1@example.com", passwordEncoder.encode("password1"));
                    user1.setAccountNotLocked(false);
                    user1.setAccountNotExpired(true);
                    user1.setCredentialsNotExpired(true);
                    user1.setEnabled(true);
                    user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                    user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                    user1.setTwoFactorEnabled(false);
                    user1.setSignUpMethod("email");
                    user1.setRole(userRole);
                    userRepository.save(user1);
                }

                if (!userRepository.existsByUserName("admin")) {
                    User admin = new User("admin", "admin@example.com", passwordEncoder.encode("adminPass"));
                    admin.setAccountNotLocked(true);
                    admin.setAccountNotExpired(true);
                    admin.setCredentialsNotExpired(true);
                    admin.setEnabled(true);
                    admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                    admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                    admin.setTwoFactorEnabled(false);
                    admin.setSignUpMethod("email");
                    admin.setRole(adminRole);
                    userRepository.save(admin);
                }
            };
    }

    @Bean
    public PasswordEncoder encodePassword()
    {
        return new BCryptPasswordEncoder();
    }

}
