package com.example.amazon.reviews.service;

import com.example.amazon.reviews.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User findById(String id);

    User findByProfileName(String profileName);

    List<User> findMostActiveUsers(int limit);

}
