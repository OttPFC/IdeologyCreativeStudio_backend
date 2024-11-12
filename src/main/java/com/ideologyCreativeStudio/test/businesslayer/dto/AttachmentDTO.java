package com.ideologyCreativeStudio.test.businesslayer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class AttachmentDTO extends BaseDTO{

    private Long id;
    private String fileName;
    private String type;
    private String url;
}
