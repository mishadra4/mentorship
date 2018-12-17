package filter;

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

import static constant.MentorshipConstants.INTEGER_ONE;
import static constant.MentorshipConstants.LOGIN_URI;
import static constant.MentorshipConstants.SUCCESS_URI;

@WebFilter(SUCCESS_URI)
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserDao userDao = new UserDaoImpl();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        if(Objects.isNull(user)){
            res.sendRedirect(LOGIN_URI);
            return;
        }

        user = userDao.getUserByUsername(user.getUsername());
        for (Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals("token") && cookie.getValue().equals(user.getTokenId())){
                if(user.getTokenExpires().isAfter(LocalDateTime.now())) {
                    filterChain.doFilter(req, res);
                    user.setTokenExpires(LocalDateTime.now().plusMinutes(INTEGER_ONE));
                    userDao.updateUser(user);
                }
            }
        }
        res.sendRedirect(LOGIN_URI);
    }

    @Override
    public void destroy() {

    }
}
