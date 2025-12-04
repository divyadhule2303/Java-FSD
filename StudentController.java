package com.eventmgmt.controller;

import com.eventmgmt.model.Student;
import com.eventmgmt.model.Event;
import com.eventmgmt.model.Registration;
import com.eventmgmt.service.StudentService;
import com.eventmgmt.service.EventService;
import com.eventmgmt.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService studentService;
    private final EventService eventService;
    private final RegistrationService regService;

    public StudentController(StudentService studentService, EventService eventService, RegistrationService regService) {
        this.studentService = studentService;
        this.eventService = eventService;
        this.regService = regService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student s) {
        Optional<Student> ex = studentService.login(s.getEmail(), s.getPassword());
        // naive — but check email uniqueness
        Optional<Student> byEmail = studentService.findById(s.getStudentId() == null ? -1L : s.getStudentId());
        // simpler check:
        // if email exists, reject
        // we'll rely on DB unique constraint; catch exceptions in production
        Student saved = studentService.register(s);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Optional<Student> opt = studentService.login(email, password);
        if(opt.isPresent()) return ResponseEntity.ok(opt.get());
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/events")
    public ResponseEntity<?> listEvents(){
        List<Event> events = eventService.listEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/register-event/{eventId}")
    public ResponseEntity<?> registerEvent(@PathVariable Long eventId, @RequestBody Map<String,String> body) {
        Long studentId = Long.parseLong(body.get("studentId"));
        Optional<Event> evOpt = eventService.getEvent(eventId);
        Optional<Student> stOpt = studentService.findById(studentId);
        if(evOpt.isEmpty() || stOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid student or event");
        Event ev = evOpt.get();
        Student st = stOpt.get();

        Optional<Registration> existing = regService.findByEventAndStudent(ev, st);
        if(existing.isPresent()) return ResponseEntity.badRequest().body("Already registered");

        Registration r = new Registration();
        r.setEvent(ev);
        r.setStudent(st);
        r.setStatus("PENDING");
        Registration saved = regService.register(r);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/my-registrations/{studentId}")
    public ResponseEntity<?> myRegistrations(@PathVariable Long studentId){
        Optional<Student> stOpt = studentService.findById(studentId);
        if(stOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid student");
        List<Registration> regs = regService.findByStudent(stOpt.get());
        return ResponseEntity.ok(regs);
    }
                                 }
