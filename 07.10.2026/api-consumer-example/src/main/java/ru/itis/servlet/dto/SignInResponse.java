package ru.itis.servlet.dto;

public record SignInResponse(
        boolean success,
        String token,
        String message
) {
}
