package com.eventmgmt.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String title;
    @Column(length = 2000)
    private String description;
    private LocalDate date;
    private String venue;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Coordinator coordinator;

    public Event() {}
    // getters & setters
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Coordinator getCoordinator() { return coordinator; }
    public void setCoordinator(Coordinator coordinator) { this.coordinator = coordinator; }
}
