package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>
, PagingAndSortingRepository<Client,Long> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findOneByName(String name);
    List<Client> findByNameContainingIgnoreCase(String firstName);
    @Query("SELECT c FROM Client c WHERE c.id IN :ids")
    List<Client> findAllByIdIn(@Param("ids") List<Long> ids);


}
