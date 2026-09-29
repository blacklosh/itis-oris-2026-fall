package ru.itis.servlet.service;

public interface AuthService {

    boolean isValidUser(String username, String password);

}
