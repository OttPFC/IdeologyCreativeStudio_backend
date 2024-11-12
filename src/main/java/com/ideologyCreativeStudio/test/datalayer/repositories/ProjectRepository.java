package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Project;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long>,
        PagingAndSortingRepository<Project,Long> {

    Optional<Project> findOneByTitle(String title);

    Optional<Project> findByUsers(User user);

    Optional<Project> findByStatus(Status status);
}
