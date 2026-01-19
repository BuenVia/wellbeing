package com.matt.wellbeing.dateTracking.service;

import com.matt.wellbeing.dateTracking.dao.DateTrackingDao;
import com.matt.wellbeing.dateTracking.model.DateTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateTrackingService {

    private final DateTrackingDao dateTrackingDao;

    @Autowired
    public DateTrackingService(DateTrackingDao dateTrackingDao) { this.dateTrackingDao = dateTrackingDao; }

    public DateTracking getDateById(Long dateId) {
        DateTracking dateTracking = dateTrackingDao.findById(dateId).orElse(null);
        if (dateTracking == null) {
            throw new RuntimeException("Could not find date: " + dateId);
        }
        return  dateTracking;
    }

    public List<DateTracking> getDatesByUserId(Long userId) {
        List<DateTracking> datesByUser = dateTrackingDao.findByUserId(userId);
        if (datesByUser == null) {
            throw new RuntimeException("Could not find dates for user: " + userId);
        }
        return datesByUser;
    }

    public List<DateTracking> getAllDates() { return dateTrackingDao.findAll(); }

    public DateTracking save(DateTracking dateTracking) {
        if (dateTracking.getUserId() == null) {
            throw new IllegalArgumentException("User ID is mandatory.");
        }
        if (dateTracking.getDate() == null) {
            throw new IllegalArgumentException("Date is mandatory.");
        }
        return dateTrackingDao.save(dateTracking);
    }

    public DateTracking updateDate(Long dateId, DateTracking date) {
        if (dateId == null) {
            throw new IllegalArgumentException("Date ID is mandatory");
        }
        DateTracking dateToUpdate = dateTrackingDao.findById(dateId).orElse(null);
        if (dateToUpdate == null) {
            throw new IllegalArgumentException("No date found with ID");
        }

        dateToUpdate.setDate(date.getDate());
        dateToUpdate.setUserId(date.getUserId());
        dateToUpdate.setExerciseId(date.getExerciseId());
        return dateTrackingDao.save(dateToUpdate);
    }

    public DateTracking deleteDate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Date is mandatory.");
        }
        DateTracking dateToDelete = dateTrackingDao.findById(id).orElse(null);
        if (dateToDelete == null) {
            throw new IllegalArgumentException("No date found with this Id.");
        }
        dateTrackingDao.delete(dateToDelete);

        return dateToDelete;
    }

}
