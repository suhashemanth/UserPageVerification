package com.suhas.UserPageVerification.user.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user",uniqueConstraints = {@UniqueConstraint(columnNames = "username"),@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String userName;
    @NotBlank
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max=120)
    @Column(name = "password")
    @JsonIgnore
    private String password;
    private boolean accountNotLocked=true;
    private boolean accountNotExpired=true;
    private boolean credentialsNotExpired=true;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private boolean isTwoFactorEnabled=false;
    private String signUpMethod;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return userId != null && userId.equals(((User) o).getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

