package com.matt.wellbeing.exercise.service;

import com.matt.wellbeing.exercise.dao.ExerciseDao;
import com.matt.wellbeing.exercise.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseDao exerciseDao;

    @Autowired
    public ExerciseService(ExerciseDao exerciseDao) { this.exerciseDao = exerciseDao; }

    public Exercise getByExerciseId(Long id) {
        Exercise exercise = exerciseDao.findById(id).orElse(null);
        if (exercise == null) {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
        return exercise;
    }

    public List<Exercise> getByUserId(Long userId) {
        List<Exercise> exercisesByUser = exerciseDao.findByUserId(userId);
        if (exercisesByUser == null) {
            throw new RuntimeException("Could not find exercises for user: " + userId);
        }
        return exercisesByUser;
    }

    public List<Exercise> getAllExercises() { return exerciseDao.findAll(); }

    public Exercise save(Exercise exercise) {
        if (exercise.getUserId() == null) {
            throw new IllegalArgumentException("User ID is mandatory.");
        }
        return exerciseDao.save(exercise);
    }

}
