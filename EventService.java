package com.eventmgmt.service;

import com.eventmgmt.model.Event;
import com.eventmgmt.model.Coordinator;
import com.eventmgmt.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repo;
    public EventService(EventRepository repo){ this.repo = repo; }

    public Event createEvent(Event e){ return repo.save(e); }
    public Optional<Event> getEvent(Long id){ return repo.findById(id); }
    public List<Event> listEvents(){ return repo.findAll(); }
    public Event updateEvent(Event e){ return repo.save(e); }
    public void deleteEvent(Long id){ repo.deleteById(id); }
    public List<Event> searchByTitle(String title){ return repo.findByTitleContainingIgnoreCase(title); }
}
