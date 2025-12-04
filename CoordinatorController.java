package com.eventmgmt.controller;

import com.eventmgmt.model.Coordinator;
import com.eventmgmt.model.Event;
import com.eventmgmt.model.Registration;
import com.eventmgmt.service.CoordinatorService;
import com.eventmgmt.service.EventService;
import com.eventmgmt.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin(origins = "*")
public class CoordinatorController {
    private final CoordinatorService coordService;
    private final EventService eventService;
    private final RegistrationService regService;

    public CoordinatorController(CoordinatorService coordService, EventService eventService, RegistrationService regService) {
        this.coordService = coordService;
        this.eventService = eventService;
        this.regService = regService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        Optional<Coordinator> opt = coordService.login(email, password);
        if(opt.isPresent()) return ResponseEntity.ok(opt.get());
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/create-event")
    public ResponseEntity<?> createEvent(@RequestBody Map<String,String> body){
        // expected fields: title, description, date (yyyy-MM-dd), venue, capacity, coordId
        String title = body.get("title");
        String desc = body.get("description");
        LocalDate date = LocalDate.parse(body.get("date"));
        String venue = body.get("venue");
        Integer capacity = Integer.parseInt(body.get("capacity"));
        Long coordId = Long.parseLong(body.get("coordId"));

        Optional<Coordinator> coordOpt = coordService.findById(coordId);
        if(coordOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid coordinator");

        Event e = new Event();
        e.setTitle(title);
        e.setDescription(desc);
        e.setDate(date);
        e.setVenue(venue);
        e.setCapacity(capacity);
        e.setCoordinator(coordOpt.get());

        Event saved = eventService.createEvent(e);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/event-registrations/{eventId}")
    public ResponseEntity<?> eventRegistrations(@PathVariable Long eventId){
        Optional<Event> evOpt = eventService.getEvent(eventId);
        if(evOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid event");
        List<Registration> regs = regService.findByEvent(evOpt.get());
        return ResponseEntity.ok(regs);
    }

    @PutMapping("/update-registration/{regId}")
    public ResponseEntity<?> updateRegistration(@PathVariable Long regId, @RequestBody Map<String,String> body){
        String status = body.get("status");
        Optional<Registration> rOpt = regService.findById(regId);
        if(rOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid registration");
        Registration r = rOpt.get();
        r.setStatus(status);
        regService.update(r);
        return ResponseEntity.ok(r);
    }

    @PutMapping("/update-event/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody Map<String,String> body){
        Optional<Event> evOpt = eventService.getEvent(eventId);
        if(evOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid event");
        Event e = evOpt.get();
        if(body.containsKey("title")) e.setTitle(body.get("title"));
        if(body.containsKey("description")) e.setDescription(body.get("description"));
        if(body.containsKey("venue")) e.setVenue(body.get("venue"));
        if(body.containsKey("date")) e.setDate(LocalDate.parse(body.get("date")));
        if(body.containsKey("capacity")) e.setCapacity(Integer.parseInt(body.get("capacity")));
        eventService.updateEvent(e);
        return ResponseEntity.ok(e);
    }

    @DeleteMapping("/delete-event/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId){
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Deleted");
    }
    }
