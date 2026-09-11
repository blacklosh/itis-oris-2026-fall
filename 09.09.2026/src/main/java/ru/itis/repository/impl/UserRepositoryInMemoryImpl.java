package ru.itis.repository.impl;

import lombok.Getter;
import ru.itis.model.UserModel;
import ru.itis.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class UserRepositoryInMemoryImpl implements UserRepository {

    private final List<UserModel> users = new CopyOnWriteArrayList<>();

    @Getter
    private static final UserRepositoryInMemoryImpl instance = new UserRepositoryInMemoryImpl();

    private UserRepositoryInMemoryImpl() {

    }

    @Override
    public void save(UserModel user) {
        for (UserModel u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email занят!");
            }
        }
        users.add(user);
    }

    @Override
    public List<UserModel> findAll() {
        return new ArrayList<>(users);
    }
}
