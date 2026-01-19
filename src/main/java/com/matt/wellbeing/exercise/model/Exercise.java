package com.matt.wellbeing.exercise.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private String description;
    private String type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // No-arg constructor (required by JPA)
    public Exercise() {
    }

    public Exercise(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
