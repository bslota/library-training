package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class PatronLoyaltiesDAO implements PatronLoyaltiesRepository {
    @Override
    public PatronLoyalties getLoyaltiesFromDatabase(PatronId patronId) {
        throw new DatabaseNotChosenYetException();
    }

    @Override
    public void update(PatronLoyalties loyalties) {
        throw new DatabaseNotChosenYetException();
    }
}
