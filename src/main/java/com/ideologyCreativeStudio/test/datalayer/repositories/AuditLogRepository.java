package com.ideologyCreativeStudio.test.datalayer.repositories;

import com.ideologyCreativeStudio.test.datalayer.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long>,
        PagingAndSortingRepository<AuditLog,Long> {
}
