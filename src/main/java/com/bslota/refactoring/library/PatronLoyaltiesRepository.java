package com.bslota.refactoring.library;

/**
 * @author bslota on 14/01/2020
 */
interface PatronLoyaltiesRepository {
    PatronLoyalties getLoyaltiesFromDatabase(PatronId patronId);

    void update(PatronLoyalties loyalties);
}
