package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Project;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>,
        PagingAndSortingRepository<Project,Long> {

    Optional<Project> findOneByTitle(String title);

    Optional<Project> findByUsers_Id(Long userId);

    Optional<Project> findByStatus(Status status);

    Page<Project> findAllByAuthor_Id(Long userId, Pageable pageable);

}
