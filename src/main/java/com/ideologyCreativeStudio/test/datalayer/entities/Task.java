package com.ideologyCreativeStudio.test.datalayer.entities;

import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task extends BaseEntity{

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 250)
    private String description;

    @Column(updatable = false,nullable = false)
    private LocalDate startDate = LocalDate.now();

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "task_users",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();
}
