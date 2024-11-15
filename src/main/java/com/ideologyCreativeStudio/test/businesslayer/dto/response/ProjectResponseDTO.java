package com.ideologyCreativeStudio.test.businesslayer.dto.response;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class ProjectResponseDTO extends BaseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate createDate;
    private LocalDate lastModifiedDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private RegisteredUserDTO author;
    private List<RegisteredUserDTO> users = new ArrayList<>();
}
