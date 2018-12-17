package servlet;

import controller.UserController;
import controller.impl.UserControllerImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

import static constant.MentorshipConstants.LOGIN_URI;
import static constant.MentorshipConstants.LOGOUT_URI;
import static constant.MentorshipConstants.SESSION_USER;

@WebServlet(LOGOUT_URI)
public class LogoutServlet extends HttpServlet {
    UserController userController = new UserControllerImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute(SESSION_USER);
            userController.expireToken(user);
            resp.sendRedirect(LOGIN_URI);
        }
    }
}
