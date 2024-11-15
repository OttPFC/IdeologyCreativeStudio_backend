package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class Client extends BaseEntity{

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    @Email
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$", message = "Email non valida")
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(length = 50)
    private String address;

    @Column(length = 250)
    private String note;

    @Column(nullable = false)
    private boolean enabled;

    @Column(updatable = false, nullable = false)
    private LocalDate createDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
    }

    @Column
    private LocalDate lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;


}
