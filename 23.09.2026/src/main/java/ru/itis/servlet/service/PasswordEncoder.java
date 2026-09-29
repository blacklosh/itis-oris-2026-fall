package ru.itis.servlet.service;

public interface PasswordEncoder {

    String getHash(String rawPassword);

    boolean matches(String rawPassword, String hashPassword);

}
