package com.suhas.UserPageVerification.user.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    @Column(name="role_name",unique = true)
    private UserRole role;
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JsonBackReference
    private Set<User> users=new HashSet<>();

    public Role(UserRole role) {
        this.role = role;
    }
}
