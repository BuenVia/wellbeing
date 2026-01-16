package com.matt.wellbeing.user.service;

import com.matt.wellbeing.user.dao.UserDao;
import com.matt.wellbeing.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
//    private final UserDao userDao;
//
//    @Autowired
//    public UserService(UserDao userDao) { this.userDao = userDao; }
//
//    public List<User>getAllUsers() {
//        return userDao.findAll();
//    }
//
//    public User getUserById(Long id) {
//        User user = userDao.findById(id);
//        if (user == null) {
//            throw new RuntimeException("User not found with id: " + id);
//        }
//        return user;
//    }
//
//    public User createUser(User user) {
//        if (user.getFirst_name() == null || user.getFirst_name().isEmpty()
//        || user.getLast_name() == null || user.getLast_name().isEmpty()) {
//            throw new IllegalArgumentException("Both first_name and last_name fields must be included in body.");
//        }
//        return userDao.save(user);
//    }

}
