package com.ideologyCreativeStudio.test.datalayer.entities;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{

    private String content;
    private LocalDate date;
    private User author;

}
