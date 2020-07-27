package com.example.amazon.reviews.security;

import com.example.amazon.reviews.model.User;

public interface AuthenticationService {

    User register(String profileName, String password);

}
