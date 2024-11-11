package com.ideologyCreativeStudio.test.datalayer.entities;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuditLog extends BaseEntity{

    private BaseEntity modifiedEntity;
    private Action action;
    private User user;
    private LocalDate date;
}
