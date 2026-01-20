package com.matt.wellbeing.workout.service;

import com.matt.wellbeing.workout.dao.WorkoutDao;
import com.matt.wellbeing.workout.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutDao workoutDao;

    @Autowired
    public WorkoutService(WorkoutDao workoutDao) { this.workoutDao = workoutDao; }

    public Workout save(Workout workout) {
        if (workout.getName() == null) {
            throw new IllegalArgumentException("Workout name is mandatory.");
        }
        return workoutDao.save(workout);
    }

    public Workout getByWorkoutId(Long workoutId) {
        Workout workout = workoutDao.findById(workoutId).orElse(null);
        if (workout == null) {
            throw new RuntimeException("Workout not found with id: " + workoutId);
        }
        return workout;
    }

    public List<Workout> getAllWorkouts() { return workoutDao.findAll(); }

    public Workout updateWorkout(Long workoutId, Workout workout) {
        if (workoutId == null) {
            throw new IllegalArgumentException("ID is mandatory");
        }
        Workout workoutToUpdate = workoutDao.findById(workoutId).orElse(null);
        if (workoutToUpdate == null) {
            throw new IllegalArgumentException("Workout not found with ID");
        }

        workoutToUpdate.setName(workout.getName());
        workoutToUpdate.setDescription(workout.getDescription());
        return workoutDao.save(workoutToUpdate);
    }

    public Workout deleteWorkout(Long workoutId) {
        if (workoutId == null) {
            throw new IllegalArgumentException("ID is mandatory.");
        }
        Workout workoutToDelete = workoutDao.findById(workoutId).orElse(null);
        if (workoutToDelete == null) {
            throw new IllegalArgumentException("No workout found.");
        }
        workoutDao.delete(workoutToDelete);
        return workoutToDelete;
    }

}
