package com.ideologyCreativeStudio.test.businesslayer.dto.user;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO extends BaseDTO {

    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    List<String> role;


}
