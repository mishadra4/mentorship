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

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {
    private final BookDaoImpl bookDaoImpl = new BookDaoImpl();
    private List<Book> books;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/page/updateBookPage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("id"))) {
            throw new MissingFieldsException();
        } else {
            books = Collections.singletonList(bookDaoImpl.getBookById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("books", books);
            doGet(request, response);
        }
    }
}
