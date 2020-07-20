package com.example.amazon.reviews.mapper;

import com.example.amazon.reviews.model.User;
import com.example.amazon.reviews.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto getUserResponseDtoFromUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setProfileName(user.getProfileName());
        userResponseDto.setUserId(user.getUserId());
        return userResponseDto;
    }
}
