package ru.itis.repository;

import ru.itis.model.UserModel;

import java.util.List;

public interface UserRepository {

    void save(UserModel user);

    List<UserModel> findAll();

}
