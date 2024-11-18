package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.ClientDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ClientResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ClientService;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.datalayer.entities.Client;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.repositories.ClientRepository;
import com.ideologyCreativeStudio.test.datalayer.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Mapper<Client,ClientResponseDTO > entityToDto;

    @Autowired
    private Mapper<ClientDTO, Client> dtoToEntity;

    @Override
    public List<ClientResponseDTO> findByNameContainingIgnoreCase(String firstName) {
        return clientRepo.findByNameContainingIgnoreCase(firstName)
                .stream()
                .map(client -> entityToDto.map(client))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClientResponseDTO> getAll(Pageable p) {
        return clientRepo.findAll(p)
                .map(client -> entityToDto.map(client));
    }


    @Override
    public ClientResponseDTO getById(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return entityToDto.map(client);
    }

    @Override
    public ClientResponseDTO save(ClientDTO e) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepo.findOneByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Client client =  dtoToEntity.map(e);
        client.setEnabled(true);
        client.setCreateDate(LocalDate.now());
        client.setCreatedBy(user);
        Client savedClient = clientRepo.save(client);
        return entityToDto.map(savedClient);
    }

    @Override
    public ClientResponseDTO update(Long id, ClientDTO dto) {
        Client existingClient = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));


        existingClient.setName(dto.getName());
        existingClient.setEmail(dto.getEmail());
        existingClient.setPhone(dto.getPhone());
        existingClient.setAddress(dto.getAddress());
        existingClient.setNote(dto.getNote());
        existingClient.setLastModifiedDate(LocalDate.now());

        Client updatedClient = clientRepo.save(existingClient);
        return entityToDto.map(updatedClient);
    }

    @Override
    public ClientResponseDTO delete(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        clientRepo.delete(client);
        return entityToDto.map(client);
    }

    @Override
    public List<ClientResponseDTO> deleteMultiple(List<Long> ids) {
        List<Client> clients = clientRepo.findAllByIdIn(ids);
        if (clients.isEmpty()) {
            throw new RuntimeException("No clients found with the provided IDs");
        }
        clientRepo.deleteAll(clients);
        log.info("Deleted clients with ids: {}", ids);


        return clients.stream()
                .map(client -> entityToDto.map(client))
                .collect(Collectors.toList());
    }


}
