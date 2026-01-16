package com.matt.wellbeing.exercise.dao;

import com.matt.wellbeing.exercise.model.Exercise;
import com.matt.wellbeing.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface ExerciseDao extends JpaRepository<Exercise, Long> {
    // Spring Data JPA will automatically implement this based on the method name
    List<Exercise> findByUserId(Long userId);
}
