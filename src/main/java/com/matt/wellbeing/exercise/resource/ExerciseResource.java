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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Exercise>> getExerciseByUser(@PathVariable Long userId) {
        try {
            List<Exercise> exerciseList = exerciseService.getByUserId(userId);
            return ResponseEntity.ok(exerciseList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        try {
            Exercise exercise = exerciseService.getByExerciseId(exerciseId);
            return ResponseEntity.ok(exercise);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        try {
            List<Exercise> exerciseList = exerciseService.getAllExercises();
            return ResponseEntity.ok(exerciseList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Exercise> createUser(@RequestBody Exercise exercise) {
        try {
            Exercise createdExercise = exerciseService.save(exercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } catch (IllegalArgumentException e) {
            System.out.println("\n\n\nerror\n\n\n");
            return ResponseEntity.badRequest().build();
        }
    }

}
