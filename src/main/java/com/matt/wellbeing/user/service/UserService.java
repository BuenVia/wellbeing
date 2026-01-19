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

    public User updateUser(Long userId, User userData) {
        if (userId == null) {
            throw new IllegalArgumentException("ID is mandatory.");
        }
        User userToUpdate = userDao.findById(userId).orElse(null);
        if (userToUpdate == null) {
            throw new IllegalArgumentException("No user found with this ID.");
        }

        userToUpdate.setFirstName(userData.getFirstName());
        userToUpdate.setLastName(userData.getLastName());
        return userDao.save(userToUpdate);

    }

    public User delete(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("ID is mandatory.");
        }
        User userToDelete = userDao.findById(userId).orElse(null);
        if (userToDelete == null) {
            throw new IllegalArgumentException("No user found with this ID.");
        }
        userDao.delete(userToDelete);
        return userToDelete;
    }

}
