package servlet;

import com.mysql.cj.util.StringUtils;
import controller.UserController;
import controller.impl.UserControllerImpl;
import exception.MissingFieldsException;
import model.Book;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constant.MentorshipConstants.*;

@WebServlet(LOGIN_URI)
public class LoginServlet extends HttpServlet {

    UserController userController = new UserControllerImpl();

    private static final String LOGIN_JSP_PATH = "/WEB-INF/view/page/login.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession oldSession = req.getSession(false);
        if(oldSession != null){
            oldSession.invalidate();
        }
        req.getRequestDispatcher(LOGIN_JSP_PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(USER_USERNAME)) || StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(USER_PASSWORD))) {
            throw new MissingFieldsException();
        }
            User user = new User();
            user.setUsername(req.getParameter(USER_USERNAME));
            user.setPassword(req.getParameter(USER_PASSWORD));
            if(userController.isAuthorized(user)){
                createSession(req);
                resp.sendRedirect(SUCCESS_URI);
            } else {
                doGet(req, resp);
            }
    }

    private void createSession(HttpServletRequest req){
        HttpSession oldSession = req.getSession(false);
        if(oldSession != null){
           oldSession.invalidate();
        }
        HttpSession newSession = req.getSession(true);
        newSession.setMaxInactiveInterval(FIVE_MINS);
    }
}
