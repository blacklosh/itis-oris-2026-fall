package ru.itis.servlet.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servlet.config.DatabaseConfig;
import ru.itis.servlet.repository.UserRepository;
import ru.itis.servlet.repository.impl.UserRepositoryConnectionImpl;
import ru.itis.servlet.repository.impl.UserRepositoryJdbcImpl;
import ru.itis.servlet.service.AuthService;
import ru.itis.servlet.service.PasswordEncoder;
import ru.itis.servlet.service.impl.AuthServiceImpl;
import ru.itis.servlet.service.impl.PasswordEncoderHashImpl;

import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //UserRepository userRepository = new UserRepositoryConnectionImpl(DatabaseConfig.getDbConnection());
        UserRepository userRepository = new UserRepositoryJdbcImpl(DatabaseConfig.getJdbcTemplate());
        PasswordEncoder passwordEncoder = new PasswordEncoderHashImpl();
        authService = new AuthServiceImpl(userRepository, passwordEncoder);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(!authService.isValidUser(username, password)) {
            resp.sendRedirect("/sign-in");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);

        resp.sendRedirect("/chat");
    }
}
