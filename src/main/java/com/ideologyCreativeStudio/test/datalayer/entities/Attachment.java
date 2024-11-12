package com.ideologyCreativeStudio.test.datalayer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "attachments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Attachment extends BaseEntity{

    @Column(length = 100, nullable = false)
    private String fileName;
    @Column(length = 50, nullable = false)
    private String type;
    @Column
    private LocalDate updateDate;
    @Column(nullable = false)
    private String url;

}
