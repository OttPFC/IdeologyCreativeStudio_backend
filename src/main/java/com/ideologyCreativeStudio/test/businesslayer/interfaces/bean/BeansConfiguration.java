package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

@Configuration
public class BeansConfiguration {

	@Bean
	public Pageable defaultPageable() {
		return PageRequest.of(0, 10);
	}

	public static RolesResponseDTO toRoleDTO(Role roleEntity) {
		if (roleEntity == null) {
			return null;
		}
		return RolesResponseDTO.builder()
				.withRoleType(roleEntity.getRoleType())
				.build();
	}

	public static Role toRoleEntity(RolesResponseDTO roleDTO) {
		if (roleDTO == null) {
			return null;
		}
		Role roleEntity = new Role();
		roleEntity.setRoleType(roleDTO.getRoleType());
		return roleEntity;
	}

	public static User toUserEntity(RegisteredUserDTO userDTO) {
		if (userDTO == null) {
			return null;
		}
		return User.builder()
				.withFirstName(userDTO.getFirstName())
				.withLastName(userDTO.getLastName())
				.withUsername(userDTO.getUsername())
				.withEmail(userDTO.getEmail())
				.withRoles(userDTO.getRoles().stream().map(BeansConfiguration::toRoleEntity).collect(Collectors.toList()))
				.build();
	}

	public static RegisteredUserDTO toRegisteredUserDTO(User user) {
        return RegisteredUserDTO.builder()
                .withId(user.getId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withUsername(user.getUsername())
                .withEmail(user.getEmail())
                .withRoles(user.getRoles().stream().map(BeansConfiguration::toRoleDTO).collect(Collectors.toList()))
                .withEnabled(user.isEnabled())
                .build();
    }
}
