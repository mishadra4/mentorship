package model;

import dao.BookDao;
import dao.impl.BookDaoImpl;

import java.util.List;

public class Reader {
    private int ID;
    private String firstName;
    private String lastName;
    private List<Book> books;

    private BookDao bookDao = new BookDaoImpl();

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        books = bookDao.getBooksByReaderID(ID);
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
