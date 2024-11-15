package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;

import com.ideologyCreativeStudio.test.businesslayer.dto.ProjectDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ProjectResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Project;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.stream.Collectors;


@Configuration
public class ProjectConfiguration {

    @Bean
    @Scope("singleton")
    public Mapper<ProjectDTO, Project> dtoToEntity() {
        return input -> Project.builder()
                .withTitle(input.getTitle())
                .withDescription(input.getDescription())
                .withCreateDate(LocalDate.now())
                .withStartDate(input.getStartDate())
                .withEndDate(input.getEndDate())
                .withStatus(input.getStatus())
                .withAuthor(BeansConfiguration.toUserEntity(input.getAuthor()))
                .withUsers(input.getUsers().stream().map(BeansConfiguration::toUserEntity).collect(Collectors.toList()))
                .build();
        }



    @Bean
    @Scope("singleton")
    public Mapper<Project, ProjectResponseDTO> entityToDto() {
        return input -> ProjectResponseDTO.builder()
                .withId(input.getId())
                .withTitle(input.getTitle())
                .withDescription(input.getDescription())
                .withCreateDate(input.getCreateDate())
                .withStartDate(input.getStartDate())
                .withEndDate(input.getEndDate())
                .withStatus(input.getStatus())
                .withAuthor(BeansConfiguration.toRegisteredUserDTO(input.getAuthor()))
                .withUsers(input.getUsers().stream().map(BeansConfiguration::toRegisteredUserDTO).collect(Collectors.toList()))
                .build();

    }



//private RegisteredUserDTO toRegisteredUserDTO(User user) {
//    if (user == null) {
//        return null;
//    }
//    return RegisteredUserDTO.builder()
//            .withId(user.getId())
//            .withFirstName(user.getFirstName())
//            .withLastName(user.getLastName())
//            .withUsername(user.getUsername())
//            .withEmail(user.getEmail())
//            .withRoles(user.getRoles().stream().map(BeansConfiguration::toRoleDTO).collect(Collectors.toList()))
//            .build();
//}


}
