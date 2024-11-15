package com.ideologyCreativeStudio.test.datalayer.entities;

import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Column
    private LocalDate createDate = LocalDate.now();
    @Column
    private LocalDate lastModifiedDate;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User author;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
        this.lastModifiedDate = LocalDate.now();
    }
}
