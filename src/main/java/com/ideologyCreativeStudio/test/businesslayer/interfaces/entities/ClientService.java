package com.ideologyCreativeStudio.test.businesslayer.interfaces.entities;

import com.ideologyCreativeStudio.test.businesslayer.dto.ClientDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ClientResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService extends CRUDService<ClientResponseDTO,ClientDTO> {


    List<ClientResponseDTO> findByNameContainingIgnoreCase(String firstName);



}
