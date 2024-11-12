package com.ideologyCreativeStudio.test.businesslayer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.AttachmentResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.RolesResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnore
    String password;
    boolean enabled;
    List<RolesResponseDTO> roles;
    AttachmentResponseDTO attachment;
}
