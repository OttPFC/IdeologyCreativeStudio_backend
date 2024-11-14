package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        PagingAndSortingRepository<User,Long> {

    Optional<User> findOneByUsername(String username);

    List<User> findByRoles_RoleType(String role);
    Optional<User> findByEmail(String email);

    List<User> findByFirstNameContainingIgnoreCase(String firstName);

}
