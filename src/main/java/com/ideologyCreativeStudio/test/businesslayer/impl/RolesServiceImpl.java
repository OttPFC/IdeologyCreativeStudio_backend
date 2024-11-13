package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.RoleEntityDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.RolesService;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import com.ideologyCreativeStudio.test.datalayer.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleRepository roleEntityRepository;

    @Autowired
    private Mapper<RoleEntityDTO, Role> roleDTOToEntityMapper;

    @Autowired
    private Mapper<Role, RolesResponseDTO> roleEntityToResponseMapper;

    @Override
    public Page<RolesResponseDTO> getAll(Pageable pageable) {
        Page<Role> roles = roleEntityRepository.findAll(pageable);
        return roles.map(roleEntityToResponseMapper::map);
    }

    @Override
    public RolesResponseDTO getById(Long id) {
        Optional<Role> role = roleEntityRepository.findById(id);
        return role.map(roleEntityToResponseMapper::map).orElse(null);
    }

    @Override
    @Transactional
    public RolesResponseDTO save(RoleEntityDTO roleEntityDTO) {
        Role roleEntity = roleDTOToEntityMapper.map(roleEntityDTO);
        Role savedRoleEntity = roleEntityRepository.save(roleEntity);
        return roleEntityToResponseMapper.map(savedRoleEntity);
    }

    @Override
    @Transactional
    public RolesResponseDTO update(Long id, RoleEntityDTO roleEntityDTO) {
        Optional<Role> optionalRoleEntity = roleEntityRepository.findById(id);
        if (optionalRoleEntity.isPresent()) {
            Role existingRoleEntity = optionalRoleEntity.get();
            existingRoleEntity.setRoleType(roleEntityDTO.getRoleType());
            Role updatedRoleEntity = roleEntityRepository.save(existingRoleEntity);
            return roleEntityToResponseMapper.map(updatedRoleEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public RolesResponseDTO delete(Long id) {
        Optional<Role> optionalRoleEntity = roleEntityRepository.findById(id);
        if (optionalRoleEntity.isPresent()) {
            Role roleEntity = optionalRoleEntity.get();
            roleEntityRepository.delete(roleEntity);
            return roleEntityToResponseMapper.map(roleEntity);
        }
        return null;
    }
}
