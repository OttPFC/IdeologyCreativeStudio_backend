package com.ideologyCreativeStudio.test.datalayer.entities;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task extends BaseEntity{

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
