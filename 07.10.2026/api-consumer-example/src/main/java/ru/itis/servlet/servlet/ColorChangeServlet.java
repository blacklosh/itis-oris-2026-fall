package ru.itis.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/color")
public class ColorChangeServlet extends HttpServlet {

    private static final String COOKIE_NAME = "my-color";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedColor = "black";

        if (Objects.nonNull(req.getCookies())) {
            for(Cookie c : req.getCookies()) {
                if (COOKIE_NAME.equals(c.getName())) {
                    selectedColor = c.getValue();
                }
            }
        }

        req.setAttribute("color", selectedColor);
        req.getRequestDispatcher("/jsp/color.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedColor = req.getParameter("selected-color");

        Cookie cookie = new Cookie(COOKIE_NAME, selectedColor);
        cookie.setMaxAge(30);

        resp.addCookie(cookie);
        resp.sendRedirect("/color");
    }
}
