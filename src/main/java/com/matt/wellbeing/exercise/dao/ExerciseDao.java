package com.matt.wellbeing.exercise.dao;

import com.matt.wellbeing.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseDao extends JpaRepository<Exercise, Long> {
    // Spring Data JPA will automatically implement this based on the method name
//    List<Exercise> findByUserId(Long userId);
}
