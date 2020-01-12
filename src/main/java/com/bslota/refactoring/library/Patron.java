package com.bslota.refactoring.library;

import java.util.List;

public class Patron {
    private static final int MAXIMUM_NUMBER_OF_HOLDS = 5;
    private PatronId patronId;
    private List<Integer> holds;
    private final PatronLoyalties patronLoyalties;

    public Patron(PatronId patronId, List<Integer> holds, PatronLoyalties patronLoyalties) {
        this.patronId = patronId;
        this.patronLoyalties = patronLoyalties;
        this.holds = holds;
    }

    PlaceOnHoldResult placeOnHold(Book book) {
        if (hasNotReachedMaximumNumberOfHolds() && book.isAvailable()) {
            this.holds.add(book.getBookIdValue());
            return BookPlacedOnHold.of(book.getBookId(), this.patronId);
        } else {
            return PlacingOnHoldFailed.of(book.getBookId(), this.patronId);
        }
    }

    public int getPatronIdValue() {
        return patronId.asInt();
    }

    public List<Integer> getHolds() {
        return this.holds;
    }

    public void setHolds(List<Integer> holds) {
        this.holds = holds;
    }

    public PatronId getPatronId() {
        return this.patronId;
    }

    private boolean hasNotReachedMaximumNumberOfHolds() {
        return this.holds.size() < MAXIMUM_NUMBER_OF_HOLDS;
    }

    public PatronLoyalties getPatronLoyalties() {
        return patronLoyalties;
    }
}
