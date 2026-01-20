package com.matt.wellbeing.workout.dao;

import com.matt.wellbeing.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutDao extends JpaRepository<Workout, Long> {
}
