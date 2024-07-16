import com.javarush.krynitsyna.demoquest.repository.StatisticRepository;
import com.javarush.krynitsyna.demoquest.repository.UserRepository;
import com.javarush.krynitsyna.demoquest.servlet.IndexServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class IndexServletTest {

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private HttpSession mockSession;

    @Mock
    private RequestDispatcher mockDispatcher;

    IndexServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new IndexServlet();
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher("WEB-INF/index.jsp")).thenReturn(mockDispatcher);

        servlet.doGet(mockRequest, mockResponse);

        verify(mockSession).setAttribute(eq("statisticRepository"), any(StatisticRepository.class));
        verify(mockSession).setAttribute(eq("userRepository"), any(UserRepository.class));
        verify(mockRequest).getRequestDispatcher("WEB-INF/index.jsp");
        verify(mockDispatcher).forward(mockRequest, mockResponse);
    }

    @Test
    public void testDoPostLoginButton() throws ServletException, IOException {
        when(mockRequest.getParameter("clickedButton")).thenReturn("loginButton");

        servlet.doPost(mockRequest, mockResponse);

        verify(mockResponse).sendRedirect("login");
        verify(mockResponse, never()).sendRedirect("signup");
    }

    @Test
    public void testDoPostSignButton() throws ServletException, IOException {
        when(mockRequest.getParameter("clickedButton")).thenReturn("signButton");

        servlet.doPost(mockRequest, mockResponse);

        verify(mockResponse).sendRedirect("signup");
        verify(mockResponse, never()).sendRedirect("login");
    }
}
