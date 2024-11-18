package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class User extends BaseEntity {

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, unique = true)
    @Email
    private String email;

    @Column
    private Long number;

    @Column(length = 125, nullable = true)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    @Column(nullable = false)
    private boolean enabled;

    @Column(updatable = false)
    private LocalDate createDate = LocalDate.now();

    @Column
    private LocalDate lastModifiedDate = LocalDate.now();

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
        this.lastModifiedDate = LocalDate.now();
    }

}
