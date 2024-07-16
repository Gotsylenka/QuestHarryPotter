package com.javarush.krynitsyna.demoquest.servlet;

import com.javarush.krynitsyna.demoquest.entity.Question;
import com.javarush.krynitsyna.demoquest.entity.User;
import com.javarush.krynitsyna.demoquest.repository.AnswerRepository;
import com.javarush.krynitsyna.demoquest.repository.QuestionRepository;
import com.javarush.krynitsyna.demoquest.repository.StatisticRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
    private final QuestionRepository questionRepository = new QuestionRepository();
    private final AnswerRepository answerRepository = new AnswerRepository();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("id");
        if (idParameter == null || idParameter.isEmpty()) {
            Optional<Question> firstQuestion = questionRepository.get(1L);
            req.setAttribute("question",firstQuestion);
        } else {
            Long questionId = Long.parseLong(idParameter);
            Optional<Question> question = questionRepository.get(questionId);
            req.setAttribute("question",question);
        }
        req.setAttribute("answers", answerRepository);
        req.getRequestDispatcher("WEB-INF/question.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        StatisticRepository statisticRepository = (StatisticRepository) session.getAttribute("statisticRepository");
        User user = (User) session.getAttribute("user");

        String idParameter = req.getParameter("id");
        if (idParameter != null && !idParameter.isEmpty()) {
            long questionId = Long.parseLong(idParameter);
            long userAnswerId = Long.parseLong(req.getParameter("answer"));
            long rightAnswerId = questionRepository.getRightAnswerById(questionId).orElseThrow();

            if (userAnswerId == rightAnswerId) {
                long nextQuestionId = questionId + 1;
                Optional<Question> nextQuestion = questionRepository.get(nextQuestionId);
                if (nextQuestion.isPresent()) {
                    resp.sendRedirect(req.getContextPath() + "/question?id=" + nextQuestionId);
                } else {
                    statisticRepository.updateUserStatistic(user.getId());
                    req.setAttribute("message", "Вы выиграли!");
                    req.setAttribute("restart", true);
                    req.getRequestDispatcher("WEB-INF/endgame.jsp").forward(req, resp);
                }
            } else {
                statisticRepository.updateUserStatistic(user.getId());
                req.setAttribute("message", "Ответ неверен! Вы проиграли!");
                req.setAttribute("restart", true);
                req.getRequestDispatcher("WEB-INF/endgame.jsp").forward(req, resp);
            }
        } else {
            resp.getWriter().println("Некорректный запрос!");
        }
    }
}
