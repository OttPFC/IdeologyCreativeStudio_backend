package com.ideologyCreativeStudio.test.presentationlayer.controller;

import com.ideologyCreativeStudio.test.businesslayer.dto.ProjectDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ProjectResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.impl.ProjectServiceImpl;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects")
public class ProjectController {


    @Autowired
    private ProjectServiceImpl projectServiceImpl;


    @GetMapping("/title/{title}")
    public ProjectResponseDTO getByTitle(@PathVariable String title) {
        return projectServiceImpl.getByTitle(title);
    }


    @GetMapping("/user")
    public ProjectResponseDTO getByUser(@RequestBody RegisteredUserDTO user) {
        return projectServiceImpl.getByUser(user);
    }


    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> getAll(Pageable pageable) {
        Page<ProjectResponseDTO> allProject = projectServiceImpl.getAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Project", String.valueOf(allProject.getTotalElements()));
        return new ResponseEntity<>(allProject, headers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ProjectResponseDTO getById(@PathVariable Long id) {
        return projectServiceImpl.getById(id);
    }


    @PostMapping
    public ProjectResponseDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return projectServiceImpl.save(projectDTO);
    }

    @PostMapping("/{projectId}/users/{userId}")
    public ProjectResponseDTO addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        return projectServiceImpl.addUsers(projectId, userId);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectServiceImpl.update(id, projectDTO);
    }


    @DeleteMapping("/{id}")
    public ProjectResponseDTO deleteProject(@PathVariable Long id) {
        return projectServiceImpl.delete(id);
    }
}
