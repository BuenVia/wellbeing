package com.matt.wellbeing.user.resource;

import com.matt.wellbeing.dateTracking.model.DateTracking;
import com.matt.wellbeing.dateTracking.service.DateTrackingService;
import com.matt.wellbeing.exercise.model.Exercise;
import com.matt.wellbeing.exercise.service.ExerciseService;
import com.matt.wellbeing.user.dao.ExerciseDateGroup;
import com.matt.wellbeing.user.model.ExerciseDate;
import com.matt.wellbeing.user.model.User;
import com.matt.wellbeing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;
    private final DateTrackingService dateTrackingService;
    private final ExerciseService exerciseService;

    @Autowired
    public UserResource(UserService userService, DateTrackingService dateTrackingService,
                        ExerciseService exerciseService) {
        this.userService = userService;
        this.dateTrackingService = dateTrackingService;
        this.exerciseService = exerciseService;
    }

    // Get all users
    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    // Get a specific user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserId(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new user
    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update a user
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userData) {
        try {
            User userToUpdate = userService.updateUser(userId, userData);
            return ResponseEntity.ok(userToUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        User deleteUser = userService.delete(userId);
        return ResponseEntity.ok(deleteUser);
    }

    // Get all user exercises AND dates
    @GetMapping("/{userId}/exercises")
    @CrossOrigin
    public ResponseEntity<List<ExerciseDate>> getAllUserExercises(@PathVariable Long userId) {
        try {
            User user = userService.findById(userId);

            List<DateTracking> dateTrackingList = dateTrackingService.getDatesByUserId(userId);
            List<Exercise> exerciseList = exerciseService.getAllExercises();

            Map<Long, Exercise> exerciseMap =
                    exerciseList.stream().collect(
                            Collectors.toMap(
                                    Exercise::getId,
                                    Function.identity()
                            )
                    );

            List<ExerciseDate> exerciseDateList =
                    dateTrackingList.stream()
                            .map(date -> new ExerciseDate(
                                    exerciseMap.get(date.getExerciseId()),
                                    date
                    ))
                    .filter(ed -> ed.getExercise() != null)
                    .toList();

            return ResponseEntity.ok(exerciseDateList);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all user exercises and dates grouped by dates
    @GetMapping("/{userId}/exercises-by-date")
    @CrossOrigin
    public ResponseEntity<List<ExerciseDateGroup>> getAllUserExercisesGroupedByDate(@PathVariable Long userId) {
        try {
            List<DateTracking> dateTrackingList = dateTrackingService.getDatesByUserId(userId);
            List<Exercise> exerciseList = exerciseService.getAllExercises();

            Map<Long, Exercise> exerciseMap =
                    exerciseList.stream().collect(
                            Collectors.toMap(
                                    Exercise::getId,
                                    Function.identity()
                            )
                    );

            Map<LocalDate, List<ExerciseDate>> groupeMap =
                    dateTrackingList.stream()
                            .map(date -> new ExerciseDate(
                                    exerciseMap.get(date.getExerciseId()),
                                    date
                            ))
                            .filter(ed -> ed.getExercise() != null)
                            .collect(Collectors.groupingBy(ed -> ed.getDateTracking().getDate()));

            List<ExerciseDateGroup> result = groupeMap.entrySet().stream()
                    .map(entry -> new ExerciseDateGroup(entry.getKey(), entry.getValue()))
                    .sorted(Comparator.comparing(ExerciseDateGroup::getDate))
                    .toList();

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
