package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;


import com.ideologyCreativeStudio.test.businesslayer.dto.user.RoleEntityDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RoleBeanConfiguration {

    @Bean
    @Scope("singleton")
    public Mapper<Role, RolesResponseDTO> mapRoleEntityToDTO() {
        return roleEntity -> {
            if (roleEntity == null) {
                return null;
            }
            return RolesResponseDTO.builder()
                    .withRoleType(roleEntity.getRoleType())
                    .build();
        };
    }

    @Bean
    @Scope("singleton")
    public Mapper<RolesResponseDTO, Role> mapRoleDTOToEntity() {
        return roleDTO -> {
            if (roleDTO == null) {
                return null;
            }
            Role roleEntity = new Role();
            roleEntity.setRoleType(roleDTO.getRoleType());
            return roleEntity;
        };
    }

    @Bean
    @Scope("singleton")
    public Mapper<List<Role>, List<RolesResponseDTO>> mapRoleEntityListToDTOList(Mapper<Role, RolesResponseDTO> roleEntityToDTOMapper) {
        return roleEntities -> {
            if (roleEntities == null) {
                return null;
            }
            return roleEntities.stream()
                    .map(roleEntityToDTOMapper::map)
                    .collect(Collectors.toList());
        };
    }

    @Bean
    @Scope("singleton")
    public Mapper<List<RolesResponseDTO>, List<Role>> mapRoleDTOListToEntityList(Mapper<RolesResponseDTO, Role> roleDTOToEntityMapper) {
        return roleDTOs -> {
            if (roleDTOs == null) {
                return null;
            }
            return roleDTOs.stream()
                    .map(roleDTOToEntityMapper::map)
                    .collect(Collectors.toList());
        };
    }

    @Bean
    @Scope("singleton")
    public Mapper<RoleEntityDTO, Role> mapRoleEntityDTOToEntity() {
        return (input) -> Role.builder()
                .withRoleType(input.getRoleType())
                .build();
    }

    @Bean
    @Scope("singleton")
    public Mapper<Role, RoleEntityDTO> mapRoleEntityToDTO2() {
        return (input) -> RoleEntityDTO.builder()
                .withRoleType(input.getRoleType())
                .build();
    }
}
