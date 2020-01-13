package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class PatronLoyaltiesDAO {
    public PatronLoyalties getLoyaltiesFromDatabase(PatronId patronId) {
        throw new DatabaseNotChosenYetException();
    }

    public void update(PatronLoyalties loyalties) {
        throw new DatabaseNotChosenYetException();
    }
}
