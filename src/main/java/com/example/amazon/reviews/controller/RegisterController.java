package com.example.amazon.reviews.controller;

import com.example.amazon.reviews.mapper.UserMapper;
import com.example.amazon.reviews.model.User;
import com.example.amazon.reviews.model.dto.UserRequestDto;
import com.example.amazon.reviews.model.dto.UserResponseDto;
import com.example.amazon.reviews.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    public RegisterController(UserMapper userMapper,
                              AuthenticationService authenticationService) {
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        User user = authenticationService.register(userRequestDto.getProfileName(),
                userRequestDto.getPassword());
        return userMapper.getUserResponseDtoFromUser(user);
    }
}
