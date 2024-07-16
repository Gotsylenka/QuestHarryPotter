package com.javarush.krynitsyna.demoquest.servlet;

import com.javarush.krynitsyna.demoquest.repository.StatisticRepository;
import com.javarush.krynitsyna.demoquest.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private final StatisticRepository statisticRepository = new StatisticRepository();
    private final UserRepository userRepository = new UserRepository();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("statisticRepository",statisticRepository);
        session.setAttribute("userRepository", userRepository);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clickedButton = request.getParameter("clickedButton");
        if ("loginButton".equals(clickedButton)) {
            response.sendRedirect("login");
        } else if ("signButton".equals(clickedButton)) {
            response.sendRedirect("signup");
        }
    }
}
