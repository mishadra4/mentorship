package servlet;

import com.mysql.cj.util.StringUtils;
import dao.BookDao;
import exception.MissingFieldsException;
import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertBook")
public class InsertBookServlet extends HttpServlet {
    BookDao bookDao = new BookDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/view/page/insertBookPage.jsp");

        Book book = bookDao.getBookforId(1);
        System.out.println(book.getPages());
        System.out.println(book.getTitle());
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("title")) || StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("pages"))) {
            throw new MissingFieldsException();
        } else {
            Book book = new Book();
            book.setTitle(request.getParameter("title"));
            book.setPages(Integer.valueOf(request.getParameter("pages")));
            bookDao.insertBook(book);
            doGet(request, response);
        }
    }
}
