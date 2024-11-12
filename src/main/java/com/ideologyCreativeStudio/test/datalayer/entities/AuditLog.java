package com.ideologyCreativeStudio.test.datalayer.entities;

import com.ideologyCreativeStudio.test.datalayer.entities.enums.Action;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "audit_logs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuditLog extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "modified_entity_id")
    private User modifiedEntity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();
}
