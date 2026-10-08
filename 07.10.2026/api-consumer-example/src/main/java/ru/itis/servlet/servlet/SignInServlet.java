package ru.itis.servlet.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servlet.dto.SignInRequest;
import ru.itis.servlet.dto.SignInResponse;
import ru.itis.servlet.service.SignInService;
import ru.itis.servlet.service.impl.SignInServiceRestImpl;

import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = new SignInServiceRestImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        SignInResponse rs = signInService.signIn(new SignInRequest(username, password));

        if(!rs.success()) {
            resp.sendRedirect("/sign-in");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", rs.token());

        resp.sendRedirect("/chat");
    }
}
