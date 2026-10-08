package ru.itis.servlet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itis.servlet.dto.SignInRequest;
import ru.itis.servlet.dto.SignInResponse;
import ru.itis.servlet.service.SignInService;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SignInServiceRestImpl implements SignInService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final URI SIGN_IN_URI = URI.create("http://localhost:80/app1/api/sign-in");

    public SignInServiceRestImpl() {
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
    }

    @Override
    @SneakyThrows
    public SignInResponse signIn(SignInRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(
                    buildRequest(request),
                    HttpResponse.BodyHandlers.ofString()
            );

            return parseResponse(response);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @SneakyThrows
    private HttpRequest buildRequest(SignInRequest request) {
        String json = objectMapper.writeValueAsString(request);
        return HttpRequest.newBuilder(SIGN_IN_URI)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private SignInResponse parseResponse(HttpResponse<String> rs) {
        try {
            SignInResponse resp = objectMapper.readValue(rs.body(), SignInResponse.class);
            if(rs.statusCode() != 200) {
                return error("Not 200 code!");
            }
            return resp;
        } catch (Exception e) {
            return error("Cannot parse response");
        }
    }

    private SignInResponse error(String error) {
        return new SignInResponse(false, null, error);
    }
}
