package ru.itis.servlet.repository;

import ru.itis.servlet.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

}
