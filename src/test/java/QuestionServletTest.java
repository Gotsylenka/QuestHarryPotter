import com.javarush.krynitsyna.demoquest.entity.Question;
import com.javarush.krynitsyna.demoquest.entity.User;
import com.javarush.krynitsyna.demoquest.repository.AnswerRepository;
import com.javarush.krynitsyna.demoquest.repository.QuestionRepository;
import com.javarush.krynitsyna.demoquest.repository.StatisticRepository;
import com.javarush.krynitsyna.demoquest.servlet.QuestionServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

    public class QuestionServletTest {
        @Mock
        private HttpServletRequest mockRequest;

        @Mock
        private HttpServletResponse mockResponse;

        @Mock
        private HttpSession mockSession;

        @Mock
        private RequestDispatcher mockDispatcher;

        @Mock
        private QuestionRepository mockQuestionRepository;

        @Mock
        private AnswerRepository mockAnswerRepository;

        @Mock
        private StatisticRepository mockStatisticRepository;

        @Mock
        private PrintWriter printWriter;

        private QuestionServlet servlet;

        @BeforeEach
        public void setUp() throws IOException {
            MockitoAnnotations.openMocks(this);
            servlet = new QuestionServlet();
            when(mockRequest.getSession()).thenReturn(mockSession);
            when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockDispatcher);
            when(mockResponse.getWriter()).thenReturn(printWriter);
        }

        @Test
        public void doPostWithWrongAnswer() throws ServletException, IOException{
            when(mockRequest.getParameter("id")).thenReturn("1");
            when(mockRequest.getParameter("answer")).thenReturn("24");
            when(mockQuestionRepository.getRightAnswerById(1L)).thenReturn(Optional.of(42L));
            User user = new User();
            user.setId(1L);
            when(mockSession.getAttribute("user")).thenReturn(user);
            when(mockSession.getAttribute("statisticRepository")).thenReturn(mockStatisticRepository);

            servlet.doPost(mockRequest,mockResponse);

            verify(mockStatisticRepository).updateUserStatistic(user.getId());
            verify(mockRequest).setAttribute(eq("message"),anyString());
            verify(mockRequest).setAttribute(eq("restart"),eq(true));
            verify(mockDispatcher).forward(mockRequest,mockResponse);
        }
        @Test
        public void doPostErrorMessage() throws ServletException, IOException{
            when(mockRequest.getParameter("id")).thenReturn(null);
            servlet.doPost(mockRequest,mockResponse);
            verify(printWriter).println("Некорректный запрос!");
        }

    }
