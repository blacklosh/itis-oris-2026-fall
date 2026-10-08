package ru.itis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = List.of(
                new User("Анна", "ADMIN", true),
                new User("Иван", "USER", true),
                new User("Мария", "USER", false)
        );

        request.setAttribute("users", users);
        request.setAttribute("showInactive", request.getParameter("all") != null);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }
}
