package com.example.amazon.reviews.service.impl;

import com.example.amazon.reviews.model.User;
import com.example.amazon.reviews.repository.UserRepository;
import com.example.amazon.reviews.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByProfileName(String profileName) {
        return userRepository.findByProfileName(profileName);
    }

    @Override
    public List<User> findMostActiveUsers(int limit) {
        return userRepository.findMostActiveUsers(PageRequest.of(0, limit));
    }
}
