package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;

import com.ideologyCreativeStudio.test.businesslayer.dto.ClientDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ClientResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
public class ClientConfiguration {



    @Bean
    @Scope("singleton")
    Mapper<ClientDTO,Client> clientDtoToEntity(){
        return input -> Client.builder()
                .withName(input.getName())
                .withEmail(input.getEmail())
                .withAddress(input.getAddress())
                .withPhone(input.getPhone())
                .withEnabled(input.isEnabled())
                .withCreatedBy(BeansConfiguration.toUserEntity(input.getCreateBy()))
                .withNote(input.getNote())
                .withLastModifiedDate(LocalDate.now())
                .build();
    }

    @Bean
    @Scope("singleton")
    Mapper<Client, ClientResponseDTO> clietnEntityToDto(){
        return input -> ClientResponseDTO.builder()
                .withId(input.getId())
                .withName(input.getName())
                .withEmail(input.getEmail())
                .withAddress(input.getAddress())
                .withPhone(input.getPhone())
                .withCreateDate(input.getCreateDate())
                .withDeleteDate(input.getDeletedDate())
                .withLastModifiedDate(input.getLastModifiedDate())
                .withEnabled(input.isEnabled())
                .withNote(input.getNote())
                .withCreateBy(BeansConfiguration.toRegisteredUserDTO(input.getCreatedBy()))
                .build();
    }

}
