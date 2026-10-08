package ru.itis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Панель управления");
        request.setAttribute("username", "Студент");
        request.setAttribute("notifications", List.of(
                "Проверить домашнее задание",
                "Подготовиться к контрольной",
                "Загрузить проект"
        ));
        request.setAttribute("generatedAt", LocalDateTime.now());

        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }
}
