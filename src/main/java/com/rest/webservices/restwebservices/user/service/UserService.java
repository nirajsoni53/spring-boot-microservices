package com.rest.webservices.restwebservices.user.service;

import com.rest.webservices.restwebservices.exception.UserNotFoundException;
import com.rest.webservices.restwebservices.user.dto.User;
import com.rest.webservices.restwebservices.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users =
            new ArrayList<>(Arrays.asList(new User(1, "niraj")));

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findOne(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public User deleteById(long id) {
        Optional<User> userOptional = findOne(id);
        userOptional.ifPresent(users::remove);
        userOptional.orElseThrow(() ->
                new UserNotFoundException("User Not Found with id = " + id));
        return userOptional.get();
    }
}
