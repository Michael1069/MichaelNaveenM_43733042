package com.hostelsphere.app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String category; 
    // Water, Electricity, Cleanliness, Security, Roommate, etc.

    @Column(nullable = false)
    private String priority; 
    // LOW, MEDIUM, HIGH

    @Column(nullable = false)
    private String status; 
    // PENDING, IN_PROGRESS, RESOLVED

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User raisedBy;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public Complaint() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Complaint(String title, String description, String category, String priority, User raisedBy, Room room) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.raisedBy = raisedBy;
        this.room = room;
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public User getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(User raisedBy) {
        this.raisedBy = raisedBy;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
