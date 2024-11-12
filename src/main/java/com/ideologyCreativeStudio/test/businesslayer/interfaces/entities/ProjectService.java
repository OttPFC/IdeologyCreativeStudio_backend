package com.ideologyCreativeStudio.test.businesslayer.interfaces.entities;

import com.ideologyCreativeStudio.test.businesslayer.dto.ProjectDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ProjectResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.CRUDService;

public interface ProjectService extends CRUDService<ProjectDTO, ProjectResponseDTO> {

    ProjectDTO getByTitle(String title);

    ProjectDTO getByUser (RegisteredUserDTO user);



}
