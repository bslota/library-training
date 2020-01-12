package com.bslota.refactoring.library;

/**
 * @author bslota on 12/01/2020
 */
class ResearcherPatronPointCalculationStrategy implements PointCalculationStrategy {

    static final int TYPE_ID = 2;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() + 5;
    }
}
