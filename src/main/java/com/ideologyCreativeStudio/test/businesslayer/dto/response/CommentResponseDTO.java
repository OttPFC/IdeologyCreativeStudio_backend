package com.ideologyCreativeStudio.test.businesslayer.dto.response;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class CommentResponseDTO extends BaseDTO {
    private String content;
    private LocalDate createDate;
    private LocalDate lastModifiedDate;
    private RegisteredUserDTO author;
}
