package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO implements BookRepository {
    public Book getBookFromDatabase(int bookId) {
        throw new DatabaseNotChosenYetException();
    }

    public void update(Book book) {
        throw new DatabaseNotChosenYetException();
    }

    @Override
    public void save(Book book) {
        update(book);
    }

    @Override
    public Book findBy(BookId bookId) {
        return getBookFromDatabase(bookId.asInt());
    }
}
