package com.ideologyCreativeStudio.test.businesslayer.dto.response;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
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
public class ClientResponseDTO extends BaseDTO {

    private Long id;
    private String name;

    private String email;

    private String address;
    private String phone;

    private String note;

    private boolean enabled;

    private LocalDate createDate;
    private LocalDate deleteDate;
    private LocalDate lastModifiedDate;
    private RegisteredUserDTO createBy;
}
