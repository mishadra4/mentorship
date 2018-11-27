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
import java.util.List;

import static constant.MentorshipConstants.BOOK_ID;

@WebServlet("/findBooksByReader")
public class findBookServlet extends HttpServlet {
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = null;
        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter("id"))) {
            throw new MissingFieldsException();
        } else {
            books = bookDao.getBooksByReaderID(Integer.parseInt(req.getParameter(BOOK_ID)));
            req.setAttribute("books", books);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/page/updateBookPage.jsp").forward(req, resp);
    }
}
