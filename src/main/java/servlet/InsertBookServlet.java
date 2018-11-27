package servlet;

import com.mysql.cj.util.StringUtils;
import dao.impl.BookDaoImpl;
import exception.MissingFieldsException;
import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.MentorshipConstants.BOOK_PAGES;
import static constant.MentorshipConstants.BOOK_TITLE;

@WebServlet("/insertBook")
public class InsertBookServlet extends HttpServlet {

    private static final String INSERT_BOOK_JSP_PATH = "/WEB-INF/view/page/insertBookPage.jsp";

    BookDaoImpl bookDaoImpl = new BookDaoImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(INSERT_BOOK_JSP_PATH);

        Book book = bookDaoImpl.getBookById(1);
        System.out.println(book.getPages());
        System.out.println(book.getTitle());
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(BOOK_TITLE)) || StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(BOOK_PAGES))) {
            throw new MissingFieldsException();
        } else {
            Book book = new Book();
            book.setTitle(request.getParameter(BOOK_TITLE));
            book.setPages(Integer.valueOf(request.getParameter(BOOK_PAGES)));
            bookDaoImpl.insertBook(book);
            doGet(request, response);
        }
    }
}
