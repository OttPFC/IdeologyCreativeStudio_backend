package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.ClientDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ClientResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public List<ClientDTO> searchUsersByFirstName(String firstName) {
        return null;
    }

    @Override
    public Page<ClientDTO> getAll(Pageable p) {
        return null;
    }

    @Override
    public ClientDTO getById(Long id) {
        return null;
    }

    @Override
    public ClientDTO save(ClientResponseDTO e) {
        return null;
    }

    @Override
    public ClientDTO update(Long id, ClientResponseDTO e) {
        return null;
    }

    @Override
    public ClientDTO delete(Long id) {
        return null;
    }
}
