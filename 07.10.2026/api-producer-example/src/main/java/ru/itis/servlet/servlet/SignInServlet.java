package ru.itis.servlet.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.servlet.dto.SignInRequest;
import ru.itis.servlet.dto.SignInResponse;
import ru.itis.servlet.service.SignInService;
import ru.itis.servlet.service.impl.SignInServiceInMemoryImpl;

import java.io.IOException;

@WebServlet("/api/sign-in")
public class SignInServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SignInService signInService = new SignInServiceInMemoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInRequest rq;
        try {
            rq = objectMapper.readValue(req.getReader(), SignInRequest.class);
        } catch (Exception e) {
            rq = null;
        }

        SignInResponse rs = signInService.signIn(rq);

        resp.getWriter().print(objectMapper.writeValueAsString(rs));
        resp.getWriter().flush();
    }
}
