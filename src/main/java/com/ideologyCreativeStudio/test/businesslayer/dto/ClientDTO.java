package com.ideologyCreativeStudio.test.businesslayer.dto;

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
public class ClientDTO extends BaseDTO {

    private Long id;
    private String name;

    private String email;

    private String address;

    private String phone;

    private String note;

    private boolean enabled;

    private LocalDate createDate;
    private LocalDate deleteDate;
    private RegisteredUserDTO createBy;
}
