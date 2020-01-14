package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class PatronDAO implements PatronRepository {
    @Override
    public Patron findBy(PatronId id) {
        throw new DatabaseNotChosenYetException();
    }

    @Override
    public void save(Patron patron) {
        findBy(null);
    }
}
