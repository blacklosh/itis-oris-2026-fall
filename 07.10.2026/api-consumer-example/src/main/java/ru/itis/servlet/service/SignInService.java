package ru.itis.servlet.service;

import ru.itis.servlet.dto.SignInRequest;
import ru.itis.servlet.dto.SignInResponse;

public interface SignInService {

    SignInResponse signIn(SignInRequest request);

}
