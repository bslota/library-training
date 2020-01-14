package com.bslota.refactoring.library;

/**
 * @author bslota on 14/01/2020
 */
interface BookRepository {
    Book findBy(BookId bookId);

    void save(Book book);
}
