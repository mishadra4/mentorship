package servlet;

import com.mysql.cj.util.StringUtils;
import controller.UserController;
import controller.impl.UserControllerImpl;
import exception.MissingFieldsException;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static constant.MentorshipConstants.*;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserController userController = new UserControllerImpl();

    private static final String REGISTER_JSP_PATH = "/WEB-INF/view/page/register.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_JSP_PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(USER_USERNAME)) || StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(USER_PASSWORD))) {
            throw new MissingFieldsException();
        } else {
            User user = new User();
            user.setUsername(req.getParameter(USER_USERNAME));
            user.setPassword(req.getParameter(USER_PASSWORD));
            user.setTokenId(UUID.randomUUID().toString());
            user.setTokenExpires(LocalDateTime.now().plusMinutes(INTEGER_ONE));
            resp.addCookie(new Cookie("tokenId", user.getTokenId()));
            userController.saveUser(user);
        }
        resp.sendRedirect(LOGIN_URI);
    }
}
