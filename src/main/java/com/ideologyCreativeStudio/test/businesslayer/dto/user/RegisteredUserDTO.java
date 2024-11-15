package com.ideologyCreativeStudio.test.businesslayer.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.AttachmentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserDTO {
    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
    LocalDate createDate;
    @JsonIgnore
    String password;
    boolean enabled;
    List<RolesResponseDTO> roles;

}
