package com.ideologyCreativeStudio.test.businesslayer.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDTO {

    RegisteredUserDTO user;
    String accessToken;

    @Builder(setterPrefix = "with")
    public LoginResponseDTO(RegisteredUserDTO user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }
}
