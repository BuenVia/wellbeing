package com.matt.wellbeing.workout.resource;

import com.matt.wellbeing.workout.model.Workout;
import com.matt.wellbeing.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutResource {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutResource(WorkoutService workoutService) { this.workoutService = workoutService; }

    @GetMapping("/{workoutId}")
    @CrossOrigin
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long workoutId) {
        try {
            Workout workout = workoutService.getByWorkoutId(workoutId);
            return ResponseEntity.ok(workout );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<Workout>> getAllWorkout() {
        try {
            List<Workout> workoutList = workoutService.getAllWorkouts();
            return ResponseEntity.ok(workoutList );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    @CrossOrigin
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        try {
            Workout createdWorkout = workoutService.save(workout);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{workoutId}")
    @CrossOrigin
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long workoutId, @RequestBody Workout workout) {
        try {
            Workout workoutToUpdate = workoutService.updateWorkout(workoutId, workout);
            return ResponseEntity.ok(workoutToUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{workoutId}")
    @CrossOrigin
    public ResponseEntity<Workout> deleteWorkout(@PathVariable Long workoutId) {
        try {
            Workout workoutToDelete = workoutService.deleteWorkout(workoutId);
            return ResponseEntity.ok(workoutToDelete );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
