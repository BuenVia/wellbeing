package com.matt.wellbeing.dateTracking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "date_tracker")
public class DateTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private Long userId;
    private Long exerciseId;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public DateTracking() {}

    public DateTracking(String date, Long userId, Long exerciseId) {
        this.date = date;
        this.userId = userId;
        this.exerciseId = exerciseId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
