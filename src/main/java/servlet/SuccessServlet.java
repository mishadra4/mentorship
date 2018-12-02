package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.MentorshipConstants.SUCCESS_URI;

@WebServlet(SUCCESS_URI)
public class SuccessServlet extends HttpServlet {

    private static final String SUCCESS_JSP_PATH = "/WEB-INF/view/page/success.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SUCCESS_JSP_PATH).forward(req, resp);
    }
}
