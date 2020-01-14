package com.bslota.refactoring.library;

/**
 * @author bslota on 14/01/2020
 */
interface PatronRepository {
    Patron findBy(PatronId id);

    void save(Patron patron);
}
