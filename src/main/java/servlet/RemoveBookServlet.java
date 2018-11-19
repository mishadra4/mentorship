package servlet;

import com.mysql.cj.util.StringUtils;
import dao.BookDao;
import exception.MissingFieldsException;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {
    private Book book;
    private final BookDao bookDao = new BookDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/page/updateBookPage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("id"))) {
            throw new MissingFieldsException();
        } else {
            bookDao.RemoveBookById(Integer.valueOf(request.getParameter("id")));
            doGet(request, response);
        }
    }

}
