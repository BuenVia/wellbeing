package com.matt.wellbeing.exercise.resource;

import com.matt.wellbeing.exercise.model.Exercise;
import com.matt.wellbeing.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseResource {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseResource(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // Get specific exercise
    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        try {
            Exercise exercise = exerciseService.getByExerciseId(exerciseId);
            return ResponseEntity.ok(exercise);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all exercises
    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        try {
            List<Exercise> exerciseList = exerciseService.getAllExercises();
            return ResponseEntity.ok(exerciseList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new exercise
    @PostMapping("/new")
    public ResponseEntity<Exercise> createUser(@RequestBody Exercise exercise) {
        try {
            Exercise createdExercise = exerciseService.save(exercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update an exercise
    @PutMapping("/update/{exerciseId}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        try {
            Exercise exerciseToUpdate = exerciseService.updateExercise(exerciseId, exercise);
            return ResponseEntity.ok(exerciseToUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an exercise
    @DeleteMapping("/delete/{exerciseId}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Long exerciseId) {
        try {
            Exercise deleteExercise = exerciseService.deleteExercise(exerciseId);
            return ResponseEntity.ok(deleteExercise);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
