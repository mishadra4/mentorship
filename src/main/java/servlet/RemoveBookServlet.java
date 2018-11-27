package servlet;

import com.mysql.cj.util.StringUtils;
import dao.impl.BookDaoImpl;
import exception.MissingFieldsException;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.MentorshipConstants.BOOK_ID;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {

    private static final String UPDATE_BOOK_JSP_PATH = "/WEB-INF/view/page/updateBookPage.jsp";
    private final BookDaoImpl bookDaoImpl = new BookDaoImpl();
    private Book book;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(UPDATE_BOOK_JSP_PATH).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(BOOK_ID))) {
            throw new MissingFieldsException();
        } else {
            bookDaoImpl.RemoveBookById(Integer.valueOf(request.getParameter(BOOK_ID)));
            doGet(request, response);
        }
    }

}
