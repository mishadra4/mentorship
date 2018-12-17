package filter;

import controller.UserController;
import controller.impl.UserControllerImpl;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import static constant.MentorshipConstants.*;

@WebFilter(SUCCESS_URI)
public class AuthenticationFilter implements Filter {

    UserController userController = new UserControllerImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute(SESSION_USER);

        if(Objects.isNull(user)){
            res.sendRedirect(LOGIN_URI);
            return;
        }

        for (Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals(USER_TOKEN) && cookie.getValue().equals(user.getTokenId())){
                if(user.getTokenExpires().isAfter(LocalDateTime.now())) {
                    userController.extendTokenExpirationDate(user);
                    filterChain.doFilter(req, res);
                }
            }
        }
        res.sendRedirect(LOGIN_URI);
    }

    @Override
    public void destroy() {

    }
}
