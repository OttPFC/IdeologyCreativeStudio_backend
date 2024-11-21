package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.ProjectDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ProjectResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ProjectService;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Project;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.repositories.ProjectRepository;
import com.ideologyCreativeStudio.test.datalayer.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Mapper<ProjectDTO, Project> dtoToEntity;

    @Autowired
    private Mapper<Project, ProjectResponseDTO> entityToDto;

    @Autowired
    private Mapper<User, RegisteredUserDTO> userEntityToUserDTOMapper;

    @Autowired
    private Mapper<RegisteredUserDTO, User> registeredToUser;

    @Override
    public ProjectResponseDTO getByTitle(String title) {
        Optional<Project> project = projectRepo.findOneByTitle(title);
        return project.map(entityToDto::map).orElse(null);
    }

    @Override
    public ProjectResponseDTO getByUser(RegisteredUserDTO user) {
        Optional<Project> project = projectRepo.findByUsers_Id(registeredToUser.map(user).getId());
        return project.map(entityToDto::map).orElse(null);
    }

    @Override
    public ProjectResponseDTO addUsers(Long projectId, Long userId) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));


        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!project.getUsers().contains(user)) {
            project.getUsers().add(user);
        }

        Project updatedProject = projectRepo.save(project);


        return entityToDto.map(updatedProject);
    }



    @Override
    public Page<ProjectResponseDTO> getAll(Pageable pageable) {

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        log.info(username);
        User user = userRepo.findOneByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        log.info(String.valueOf(user));
        //Page<Project> projects = projectRepo.findAllByAuthor_Id(user.getId(), pageable);
        Page<Project> projects = projectRepo.findAll(pageable);


        return projects.map(entityToDto::map);
    }


    @Override
    public ProjectResponseDTO getById(Long id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        return entityToDto.map(project);
    }


    @Override
    public ProjectResponseDTO save(ProjectDTO projectDTO) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepo.findOneByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user);
        Project project = dtoToEntity.map(projectDTO);
        project.setAuthor(user);
        Project savedProject = projectRepo.save(project);
        return entityToDto.map(savedProject);
    }


    @Override
    public ProjectResponseDTO update(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        Project updatedProject = dtoToEntity.map(projectDTO);
        updatedProject.setId(existingProject.getId());
        projectRepo.save(updatedProject);
        return entityToDto.map(updatedProject);
    }

    @Override
    public ProjectResponseDTO delete(Long id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepo.delete(project);
        return entityToDto.map(project);
    }
}

