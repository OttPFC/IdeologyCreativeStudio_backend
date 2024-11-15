package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;


import com.ideologyCreativeStudio.test.businesslayer.dto.response.AttachmentResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.LoginResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisterUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Attachment;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.stream.Collectors;

@Configuration
public class UserBeansConfiguration {

    @Bean
    @Scope("singleton")
    public Mapper<RegisterUserDTO, User> mapRegisterToEntity() {
        return input -> User.builder()
                .withFirstName(input.getFirstName())
                .withLastName(input.getLastName())
                .withUsername(input.getUsername())
                .withEmail(input.getEmail())
                .withPassword(input.getPassword())

                .build();
    }

    @Bean
    @Scope("singleton")
    public Mapper<User, RegisteredUserDTO> mapUserEntity2RegisteredUser() {
        return input -> RegisteredUserDTO.builder()
                .withId(input.getId())
                .withFirstName(input.getFirstName())
                .withLastName(input.getLastName())
                .withUsername(input.getUsername())
                .withEmail(input.getEmail())
                .withCreateDate(input.getCreateDate())
                .withRoles(input.getRoles().stream().map(BeansConfiguration::toRoleDTO).collect(Collectors.toList()))
                .withEnabled(input.isEnabled())
                .build();
    }


    @Bean
    @Scope("singleton")
    public Mapper<RegisteredUserDTO, User> mapRegisteredUser2UserEntity() {
        return input -> User.builder()
                .withFirstName(input.getFirstName())
                .withLastName(input.getLastName())
                .withUsername(input.getUsername())
                .withEmail(input.getEmail())
                .withPassword(input.getPassword())
                .withEnabled(input.isEnabled())
                .withRoles(input.getRoles().stream().map(BeansConfiguration::toRoleEntity).collect(Collectors.toList()))
                .build();
    }

    @Bean
    @Scope("singleton")
    public Mapper<User, LoginResponseDTO> mapUserEntity2LoginResponse() {
        return input -> LoginResponseDTO.builder()
                .withUser(RegisteredUserDTO.builder()
                        .withId(input.getId())
                        .withFirstName(input.getFirstName())
                        .withLastName(input.getLastName())
                        .withUsername(input.getUsername())
                        .withEmail(input.getEmail())
                        .withRoles(input.getRoles().stream().map(BeansConfiguration::toRoleDTO).collect(Collectors.toList()))
                        .withEnabled(input.isEnabled())
                        .build())
                .build();
    }

//    @Bean
//    @Scope("singleton")
//    public Mapper<User, LoginResponseDTO> mapUserEntity2LoginResponse() {
//        return input -> LoginResponseDTO.builder()
//                .withUser(mapUserEntity2RegisteredUser().map(input))
//                .build();
//    }
//
//    private RegisteredUserDTO toRegisteredUserDTO(User user) {
//        return RegisteredUserDTO.builder()
//                .withId(user.getId())
//                .withFirstName(user.getFirstName())
//                .withLastName(user.getLastName())
//                .withUsername(user.getUsername())
//                .withEmail(user.getEmail())
//                .withRoles(user.getRoles().stream().map(BeansConfiguration::toRoleDTO).collect(Collectors.toList()))
//                .withEnabled(user.isEnabled())
//                .build();
//    }
//
//    private User toUserEntity(RegisteredUserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//        return User.builder()
//                .withFirstName(userDTO.getFirstName())
//                .withLastName(userDTO.getLastName())
//                .withUsername(userDTO.getUsername())
//                .withEmail(userDTO.getEmail())
//                .withRoles(userDTO.getRoles().stream().map(BeansConfiguration::toRoleEntity).collect(Collectors.toList()))
//                .build();
//    }
//
//    private Attachment toImageEntity(AttachmentResponseDTO attachmentResponseDTO) {
//        if (attachmentResponseDTO == null) {
//            return null;
//        }
//        Attachment attachment = new Attachment();
//        attachment.setUrl(attachmentResponseDTO.getUrl());
//        attachment.setType(attachmentResponseDTO.getType());
//        return attachment;
//    }
//
//    private AttachmentResponseDTO toImageDTO(Attachment image) {
//        if (image == null) {
//            return null;
//        }
//        return AttachmentResponseDTO.builder()
//                .withUrl(image.getUrl())
//                .withType(image.getType())
//                .build();
//    }


}
