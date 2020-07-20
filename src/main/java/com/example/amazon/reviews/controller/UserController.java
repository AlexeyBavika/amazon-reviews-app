package com.example.amazon.reviews.controller;

import com.example.amazon.reviews.mapper.UserMapper;
import com.example.amazon.reviews.model.dto.UserResponseDto;
import com.example.amazon.reviews.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/most-active")
    public List<UserResponseDto> getMostActiveUsers(@RequestParam int limit) {
        return userService.findMostActiveUsers(limit).stream()
                .map(userMapper::getUserResponseDtoFromUser)
                .collect(Collectors.toList());
    }
}
