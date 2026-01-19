package com.matt.wellbeing.dateTracking.resource;

import com.matt.wellbeing.dateTracking.model.DateTracking;
import com.matt.wellbeing.dateTracking.service.DateTrackingService;
import com.matt.wellbeing.exercise.model.Exercise;
import com.matt.wellbeing.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/date")
public class DateTrackingResource {

    private final DateTrackingService dateTrackingService;
    private final ExerciseService exerciseService;

    @Autowired
    public DateTrackingResource(DateTrackingService dateTrackingService, ExerciseService exerciseService) {
        this.dateTrackingService = dateTrackingService;
        this.exerciseService = exerciseService;
    }

    // Get all dates by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DateTracking>> getDatesByUser(@PathVariable Long userId) {
        try {
            List<DateTracking> dateTrackingList = dateTrackingService.getDatesByUserId(userId);
            return ResponseEntity.ok(dateTrackingList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a specific date
    @GetMapping("/{dateId}")
    public ResponseEntity<DateTracking> getDateById(@PathVariable Long dateId) {
        try {
            DateTracking dateTracking = dateTrackingService.getDateById(dateId);
            return ResponseEntity.ok(dateTracking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all dates
    @GetMapping("/all")
    public ResponseEntity<List<DateTracking>> getAllExercises() {
        try {
            List<DateTracking> dateTrackingList = dateTrackingService.getAllDates();
            return ResponseEntity.ok(dateTrackingList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new date
    @PostMapping("/new")
    public ResponseEntity<DateTracking> createUser(@RequestBody DateTracking date) {
        try {
            DateTracking createdExercise = dateTrackingService.save(date);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update date
    @PutMapping("/update/{dateId}")
    public ResponseEntity<DateTracking> updateDate(@PathVariable Long dateId, @RequestBody DateTracking date) {
        try {
            DateTracking dateToUpdate = dateTrackingService.updateDate(dateId, date);
            return ResponseEntity.ok(dateToUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete date
    @DeleteMapping("/delete/{dateId}")
    public ResponseEntity<DateTracking> deleteDate(@PathVariable Long dateId) {
        try {
            DateTracking deleteDate = dateTrackingService.deleteDate(dateId);
            return ResponseEntity.ok(deleteDate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
