package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class Project extends BaseEntity{

    @Column(length = 25, nullable = false)
    private String title;

    @Column(length = 250)
    private String description;


    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
