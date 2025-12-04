package com.eventmgmt.service;

import com.eventmgmt.model.Student;
import com.eventmgmt.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }

    public Student register(Student s) {
        return repo.save(s);
    }

    public Optional<Student> login(String email, String password) {
        Optional<Student> opt = repo.findByEmail(email);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            return opt;
        }
        return Optional.empty();
    }

    public Optional<Student> findById(Long id){ return repo.findById(id); }
}
