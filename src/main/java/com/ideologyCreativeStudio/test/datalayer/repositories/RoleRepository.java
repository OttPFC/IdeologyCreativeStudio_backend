package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>,
        PagingAndSortingRepository<Role,Long> {

    Optional<Role> findOneByRole(String role);
}
