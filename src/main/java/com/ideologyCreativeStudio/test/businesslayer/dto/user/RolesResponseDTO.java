package com.ideologyCreativeStudio.test.businesslayer.dto.user;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RolesResponseDTO extends BaseDTO {
    private String roleType;
}
