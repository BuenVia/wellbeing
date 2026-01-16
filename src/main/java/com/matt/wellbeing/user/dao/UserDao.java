package com.matt.wellbeing.user.dao;

import com.matt.wellbeing.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    // No logic needed as already provided by Spring Data JPA
}
