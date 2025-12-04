package com.eventmgmt.model;

import javax.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String status; // PENDING, APPROVED, REJECTED

    public Registration() {}
    // getters & setters
    public Long getRegId() { return regId; }
    public void setRegId(Long regId) { this.regId = regId; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
