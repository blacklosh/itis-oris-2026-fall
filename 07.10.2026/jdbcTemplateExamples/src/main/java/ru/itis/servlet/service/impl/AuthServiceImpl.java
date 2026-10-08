package ru.itis.servlet.service.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.servlet.model.UserEntity;
import ru.itis.servlet.repository.UserRepository;
import ru.itis.servlet.service.AuthService;
import ru.itis.servlet.service.PasswordEncoder;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isValidUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(UserEntity::getPassword)
                .map(p -> passwordEncoder.matches(password, p))
                .orElse(false);
    }
}
