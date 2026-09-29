package ru.itis.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (Objects.isNull(session) ||
                Objects.isNull(session.getAttribute("username")) ||
                session.getAttribute("username").toString().isBlank()) {
            resp.sendRedirect("/sign-in");
            return;
        }
        req.setAttribute("username", session.getAttribute("username"));

        req.getRequestDispatcher("/jsp/chat.jsp").forward(req, resp);
    }
}
