package com.ideologyCreativeStudio.test.presentationlayer.controller;

import com.ideologyCreativeStudio.test.businesslayer.dto.ClientDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.ClientResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.impl.ClientServiceImpl;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientServiceImpl;


    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable) {
        Page<ClientResponseDTO> clients = clientServiceImpl.getAll(pageable);
        return ResponseEntity.ok(clients);
    }

    // Search clients by name
    @GetMapping("/search")
    public ResponseEntity<List<ClientResponseDTO>> searchClientsByName(@RequestParam("name") String name) {
        List<ClientResponseDTO> clients = clientServiceImpl.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(clients);
    }

    // Get a client by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        ClientResponseDTO client = clientServiceImpl.getById(id);
        return ResponseEntity.ok(client);
    }

    // Create a new client
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientResponseDTO newClient = clientServiceImpl.save(clientDTO);
        return ResponseEntity.ok(newClient);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientResponseDTO updatedClient = clientServiceImpl.update(id, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // Delete a client by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> deleteClient(@PathVariable Long id) {
        ClientResponseDTO deletedClient = clientServiceImpl.delete(id);
        return ResponseEntity.ok(deletedClient);
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<Void> deleteMultipleClients(@RequestBody List<Long> ids) {
        clientServiceImpl.deleteMultiple(ids);
        return ResponseEntity.noContent().build();
    }

}

