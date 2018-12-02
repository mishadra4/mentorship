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
import java.util.Collections;
import java.util.List;

import static constant.MentorshipConstants.BOOKS;
import static constant.MentorshipConstants.BOOK_ID;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    private static final String UPDATE_BOOK_JSP_PATH = "/WEB-INF/view/page/updateBookPage.jsp";
    private final BookDaoImpl bookDaoImpl = new BookDaoImpl();

    private List<Book> books;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(UPDATE_BOOK_JSP_PATH).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(BOOK_ID))) {
            throw new MissingFieldsException();
        } else {
            books = Collections.singletonList(bookDaoImpl.getBookById(Integer.valueOf(request.getParameter(BOOK_ID))));
            request.setAttribute(BOOKS, books);
            doGet(request, response);
        }
    }
}
