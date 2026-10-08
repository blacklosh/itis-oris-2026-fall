package ru.itis.servlet.service.impl;

import ru.itis.servlet.service.PasswordEncoder;

public class PasswordEncoderHashImpl implements PasswordEncoder {

    @Override
    public String getHash(String rawPassword) {
        return "h" + rawPassword.hashCode();
    }

    @Override
    public boolean matches(String rawPassword, String hashPassword) {
        return hashPassword.equals(getHash(rawPassword));
    }
}
