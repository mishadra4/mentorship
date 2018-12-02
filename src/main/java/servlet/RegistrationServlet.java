package servlet;

import com.mysql.cj.util.StringUtils;
import controller.UserController;
import controller.impl.UserControllerImpl;
import exception.MissingFieldsException;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.MentorshipConstants.LOGIN_URI;
import static constant.MentorshipConstants.USER_PASSWORD;
import static constant.MentorshipConstants.USER_USERNAME;

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
            userController.saveUser(user);
        }
        resp.sendRedirect(LOGIN_URI);
    }
}
