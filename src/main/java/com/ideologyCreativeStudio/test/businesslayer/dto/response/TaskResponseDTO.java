package com.ideologyCreativeStudio.test.businesslayer.dto.response;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
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
public class TaskResponseDTO extends BaseDTO {
    private String title;

    private String description;
    private LocalDate endDate;
    private Status status;
    private List<User> users = new ArrayList<>();
}
