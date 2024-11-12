package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{

    @Column(length = 300)
    private String content;

    @Column(updatable = false, nullable = false)
    private LocalDate createDate = LocalDate.now();

    private LocalDate modifyComment;

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

}
