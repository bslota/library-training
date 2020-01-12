package com.bslota.refactoring.library;

public class PatronLoyalties {
    private PatronId patronId;
    private int points;
    private boolean qualifiesForFreeBook;
    private PointCalculationStrategy pointCalculationStrategy;

    PatronLoyalties(PatronId patronId, int type, int points, boolean qualifiesForFreeBook) {
        this.patronId = patronId;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.pointCalculationStrategy = PointCalculationStrategy.from(type);
    }

    PatronId getPatronId() {
        return patronId;
    }

    public int getPoints() {
        return this.points;
    }

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    void addLoyaltyPoints() {
        this.points = pointCalculationStrategy.calculate(this);
        if (this.points > 10000) {
            setQualifiesForFreeBook(true);
        }
    }
}