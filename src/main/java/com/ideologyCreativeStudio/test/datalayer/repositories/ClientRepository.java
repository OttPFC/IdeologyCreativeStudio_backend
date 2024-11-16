package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>
, PagingAndSortingRepository<Client,Long> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findOneByName(String name);
    List<Client> findByNameContainingIgnoreCase(String firstName);

}
