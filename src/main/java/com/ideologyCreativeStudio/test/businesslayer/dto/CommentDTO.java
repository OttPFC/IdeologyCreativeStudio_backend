package com.ideologyCreativeStudio.test.businesslayer.dto;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class CommentDTO extends BaseDTO{

    private String content;
    private LocalDate createDate;
    private LocalDate lastModifiedDate;
    private RegisteredUserDTO author;
}
