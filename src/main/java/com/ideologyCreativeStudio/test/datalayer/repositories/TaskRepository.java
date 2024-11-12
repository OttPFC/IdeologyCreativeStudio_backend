package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Task;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long>,
        PagingAndSortingRepository<Task,Long> {

    Optional<Task> findByTitle(String title);

    Optional<Task> findByUser(User user);

    Optional<Task> findByStatus(Status status);


}
