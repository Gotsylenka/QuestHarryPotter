package com.javarush.krynitsyna.demoquest.servlet;

import com.javarush.krynitsyna.demoquest.entity.Role;
import com.javarush.krynitsyna.demoquest.entity.User;
import com.javarush.krynitsyna.demoquest.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
private UserRepository userRepository = new UserRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
         session.getAttribute("userRepository");

        if (userRepository == null) {
            // Либо обработать ошибку, либо инициализировать
            userRepository = new UserRepository();
            session.setAttribute("userRepository", userRepository);
        }

        // Получаем параметры из формы регистрации
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Создаем нового пользователя
        Optional<User> expectedName = userRepository.getByUsername(username);
        if (expectedName.isEmpty()){
            User user = User.builder()
                    .login(username)
                    .password(password)
                    .role(Role.GUEST)
                    .build();

            userRepository.create(user);
            resp.sendRedirect("login");
        }else {
            resp.getWriter().println("Данное имя пользователя занято!");
        }
    }
}






