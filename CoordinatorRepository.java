package com.eventmgmt.repository;

import com.eventmgmt.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    Optional<Coordinator> findByEmail(String email);
}
