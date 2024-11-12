package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.ProjectDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ProjectResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public ProjectDTO getByTitle(String title) {
        return null;
    }

    @Override
    public ProjectDTO getByUser(RegisteredUserDTO user) {
        return null;
    }

    @Override
    public Page<ProjectDTO> getAll(Pageable p) {
        return null;
    }

    @Override
    public ProjectDTO getById(Long id) {
        return null;
    }

    @Override
    public ProjectDTO save(ProjectResponseDTO e) {
        return null;
    }

    @Override
    public ProjectDTO update(Long id, ProjectResponseDTO e) {
        return null;
    }

    @Override
    public ProjectDTO delete(Long id) {
        return null;
    }
}
