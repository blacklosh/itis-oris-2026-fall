package ru.itis.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.UserModel;
import ru.itis.repository.impl.UserRepositoryInMemoryImpl;
import ru.itis.util.StringUtil;

import java.io.IOException;
import java.util.List;

public class HelloServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String random = StringUtil.generateRandomString();

        req.setAttribute("str", random);

        List<UserModel> users = UserRepositoryInMemoryImpl.getInstance().findAll();

        req.setAttribute("users", users);

        req.getRequestDispatcher("jsp/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}
