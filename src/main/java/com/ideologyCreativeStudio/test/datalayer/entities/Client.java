package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private String email;

    @Column(length = 50)
    private String address;

    @Column(length = 250)
    private String note;

    @Column(nullable = false)
    private boolean enabled;

    @Column(updatable = false)
    private LocalDate createDate = LocalDate.now();

    private LocalDate deleteDate;

    @OneToOne
    @JoinColumn(name = "create_by")
    private User createBy;

}
