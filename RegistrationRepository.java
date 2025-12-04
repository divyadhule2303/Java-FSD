package com.eventmgmt.repository;

import com.eventmgmt.model.Registration;
import com.eventmgmt.model.Event;
import com.eventmgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEvent(Event event);
    List<Registration> findByStudent(Student student);
    Optional<Registration> findByEventAndStudent(Event event, Student student);
}
