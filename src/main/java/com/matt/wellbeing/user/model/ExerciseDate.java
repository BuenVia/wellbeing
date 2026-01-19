package com.matt.wellbeing.user.model;

import com.matt.wellbeing.dateTracking.model.DateTracking;
import com.matt.wellbeing.exercise.model.Exercise;

public class ExerciseDate {
    private Exercise exercise;
    private DateTracking dateTracking;

    public ExerciseDate(Exercise exercise, DateTracking dateTracking) {
        this.exercise = exercise;
        this.dateTracking = dateTracking;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}