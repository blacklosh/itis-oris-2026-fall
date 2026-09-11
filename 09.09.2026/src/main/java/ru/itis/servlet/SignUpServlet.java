package ru.itis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.UserModel;
import ru.itis.repository.impl.UserRepositoryInMemoryImpl;

import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = UserModel.builder()
                .email(req.getParameter("email"))
                .name(req.getParameter("username"))
                .build();
        UserRepositoryInMemoryImpl.getInstance().save(user);
        resp.sendRedirect("/app1/test");
    }
}
