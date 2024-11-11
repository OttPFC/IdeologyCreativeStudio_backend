package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;

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
    private boolean isEnabled;

}
