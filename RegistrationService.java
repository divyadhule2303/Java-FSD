package com.eventmgmt.service;

import com.eventmgmt.model.Event;
import com.eventmgmt.model.Registration;
import com.eventmgmt.model.Student;
import com.eventmgmt.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final RegistrationRepository repo;
    public RegistrationService(RegistrationRepository repo){ this.repo = repo; }

    public Registration register(Registration r){ return repo.save(r); }
    public List<Registration> findByEvent(Event e){ return repo.findByEvent(e); }
    public List<Registration> findByStudent(Student s){ return repo.findByStudent(s); }
    public Optional<Registration> findByEventAndStudent(Event e, Student s){ return repo.findByEventAndStudent(e, s); }
    public Optional<Registration> findById(Long id){ return repo.findById(id); }
    public Registration update(Registration r){ return repo.save(r); }
}
