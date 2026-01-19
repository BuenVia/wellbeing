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

    public List<Exercise> getAllExercises() { return exerciseDao.findAll(); }

    public Exercise save(Exercise exercise) {
        if (exercise.getName() == null || exercise.getType() == null) {
            throw new IllegalArgumentException("User ID is mandatory.");
        }
        return exerciseDao.save(exercise);
    }

    public Exercise updateExercise(Long exerciseId, Exercise exercise) {
        if (exerciseId == null) {
            throw new IllegalArgumentException("ExerciseId is mandatory");
        }
        Exercise exerciseToUpdate = exerciseDao.findById(exerciseId).orElse(null);
        if (exerciseToUpdate == null) {
            throw new IllegalArgumentException("Exercise not found with this ID");
        }

        exerciseToUpdate.setName(exercise.getName());
        exerciseToUpdate.setDescription(exercise.getDescription());
        exerciseToUpdate.setType(exercise.getType());
        return exerciseDao.save(exerciseToUpdate);
    }

    public Exercise deleteExercise(Long exerciseId) {
        if (exerciseId == null) {
            throw new IllegalArgumentException("Id is mandatory");
        }
        Exercise exerciseToDelete = exerciseDao.findById(exerciseId).orElse(null);
        if(exerciseToDelete == null) {
            throw new IllegalArgumentException("No exercise found");
        }
        exerciseDao.delete(exerciseToDelete);
        return exerciseToDelete;
    }

}
