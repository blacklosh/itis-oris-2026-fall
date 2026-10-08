package ru.itis.servlet.service.impl;

import ru.itis.servlet.dto.SignInRequest;
import ru.itis.servlet.dto.SignInResponse;
import ru.itis.servlet.service.SignInService;

import java.util.UUID;

public class SignInServiceInMemoryImpl implements SignInService {

    @Override
    public SignInResponse signIn(SignInRequest request) {
        if (request == null) {
            return error("Request is empty");
        }
        if (!"Fedor".equals(request.username())) {
            return error("Invalid username");
        }
        if (!"qwerty".equals(request.password())) {
            return error("Invalid password");
        }
        return success();
    }

    private SignInResponse error(String error) {
        return new SignInResponse(false, null, error);
    }

    private SignInResponse success() {
        return new SignInResponse(true, UUID.randomUUID().toString(), null);
    }
}
