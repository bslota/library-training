package com.bslota.refactoring.library;

public class PatronLoyalties {
    private static final int DEFAULT_POINTS_COUNT = 0;
    private static final boolean FREE_BOOK_BY_DEFAULT = false;
    private static final int DEFAULT_PATRON_TYPE = 0;
    private PatronId patronId;
    private LoyaltyPoints loyaltyPoints;
    private boolean qualifiesForFreeBook;
    private PointCalculationStrategy pointCalculationStrategy;

    PatronLoyalties(PatronId patronId, int type, int points, boolean qualifiesForFreeBook) {
        this.patronId = patronId;
        this.loyaltyPoints = LoyaltyPoints.of(points);
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.pointCalculationStrategy = PointCalculationStrategy.from(type);
    }

    static PatronLoyalties emptyFor(PatronId patronId) {
        return new PatronLoyalties(patronId, DEFAULT_PATRON_TYPE, DEFAULT_POINTS_COUNT, FREE_BOOK_BY_DEFAULT);
    }

    PatronId getPatronId() {
        return patronId;
    }

    public int getPoints() {
        return loyaltyPoints.asInt();
    }

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    void addLoyaltyPoints() {
        this.loyaltyPoints = LoyaltyPoints.of(pointCalculationStrategy.calculate(this));
        if (this.loyaltyPoints.asInt() > 10000) {
            setQualifiesForFreeBook(true);
        }
    }
}