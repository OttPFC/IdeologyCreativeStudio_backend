package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class User extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 100, nullable = false, unique = true)
    @Email
    private String email;
    @Column(length = 125, nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
    @Column(nullable = false)
    private boolean isEnabled;
}
