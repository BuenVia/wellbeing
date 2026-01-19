package com.matt.wellbeing.dateTracking.dao;

import com.matt.wellbeing.dateTracking.model.DateTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateTrackingDao extends JpaRepository<DateTracking, Long> {
    List<DateTracking> findByUserId(Long userId);
}
