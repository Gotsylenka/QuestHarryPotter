package com.javarush.krynitsyna.demoquest.servlet;

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
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/statistic")
public class StatisticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserRepository userRepository = (UserRepository) session.getAttribute("userRepository");
        StatisticRepository statisticRepository = (StatisticRepository) session.getAttribute("statisticRepository");

        if (userRepository == null || statisticRepository == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Repositories not initialized");
            return;
        }

        Map<Long, UserStatistic> userStatisticMap = statisticRepository.getAll();
        req.setAttribute("userStatisticMap", userStatisticMap);
        req.setAttribute("users", userRepository.getAll());
        req.getRequestDispatcher("WEB-INF/statistic.jsp").forward(req, resp);
    }
}
