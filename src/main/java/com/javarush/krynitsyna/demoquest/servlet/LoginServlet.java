package com.javarush.krynitsyna.demoquest.servlet;

import com.javarush.krynitsyna.demoquest.entity.User;
import com.javarush.krynitsyna.demoquest.entity.UserStatistic;
import com.javarush.krynitsyna.demoquest.repository.StatisticRepository;
import com.javarush.krynitsyna.demoquest.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.getAttribute("userRepository");

        request.setAttribute("users", userRepository.getAll());
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StatisticRepository statisticRepository = (StatisticRepository) session.getAttribute("statisticRepository");
        if (statisticRepository == null) {
            statisticRepository = new StatisticRepository();
            session.setAttribute("statisticRepository", statisticRepository);
        }

        session.getAttribute("userRepository");

        String clickedButton = request.getParameter("clickedButton");

        if ("loginButton".equals(clickedButton)) {
            String selectUser = request.getParameter("selectUser");
            String passwordInput = request.getParameter("passwordInput");

            long userId = Long.parseLong(selectUser);
            Optional<User> userOpt = userRepository.get(userId);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (user.getPassword().equals(passwordInput)) {
                    statisticRepository.addUserStatistic(userId, new UserStatistic(userId, 0));

                    session.setAttribute("user", user);
                    response.sendRedirect("question");
                } else {
                    request.setAttribute("errorMessage", "Invalid password.");
                    request.setAttribute("users", userRepository.getAll());
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("errorMessage", "User not found");
                request.setAttribute("users", userRepository.getAll());
                System.out.println("User not found for ID: " + userId);
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else if ("regBtn".equals(clickedButton)) {
            response.sendRedirect("signup");
        }
    }
}