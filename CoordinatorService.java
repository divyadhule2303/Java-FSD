package com.eventmgmt.service;

import com.eventmgmt.model.Coordinator;
import com.eventmgmt.repository.CoordinatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoordinatorService {
    private final CoordinatorRepository repo;
    public CoordinatorService(CoordinatorRepository repo){ this.repo = repo; }

    public Coordinator save(Coordinator c){ return repo.save(c); }

    public Optional<Coordinator> login(String email, String password){
        Optional<Coordinator> opt = repo.findByEmail(email);
        if(opt.isPresent() && opt.get().getPassword().equals(password)) return opt;
        return Optional.empty();
    }

    public Optional<Coordinator> findById(Long id){ return repo.findById(id); }
}
