package com.matt.wellbeing.user.dao;

import com.matt.wellbeing.user.model.ExerciseDate;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public class ExerciseDateGroup {
    private LocalDate date;
    private List<ExerciseDate> exercises;

    public ExerciseDateGroup(LocalDate date, List<ExerciseDate> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ExerciseDate> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDate> exercises) {
        this.exercises = exercises;
    }
}
