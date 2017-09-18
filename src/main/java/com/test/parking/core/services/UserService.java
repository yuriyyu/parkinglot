package com.test.parking.core.services;

import com.test.parking.core.models.users.Manager;
import com.test.parking.core.models.users.User;
import com.test.parking.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dmitry on 9/17/2017.
 */
@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User createStubUser() {
        User user = new Manager("jsmith", "12345", "John Smith");

        user = userRepository.save(user);

        return user;
    }
}
