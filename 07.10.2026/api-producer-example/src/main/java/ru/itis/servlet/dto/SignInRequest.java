package ru.itis.servlet.dto;

public record SignInRequest(
        String username,
        String password
) {
}
