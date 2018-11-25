package dao;

import model.Reader;

import java.util.List;

public interface ReaderDao {
    Reader getReaderById(int ID);

    List<Reader> getReadersByReaderID(int ID);

    void RemoveReaderById(int ID);

    void insertReader(Reader reader);

    void updateReader(Reader reader);
}
