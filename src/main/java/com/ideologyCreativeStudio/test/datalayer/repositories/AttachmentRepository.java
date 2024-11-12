package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Long>,
        PagingAndSortingRepository<Attachment,Long> {
}
