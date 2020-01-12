package com.bslota.refactoring.library;

/**
 * @author bslota on 12/01/2020
 */
class RegularPatronPointCalculationStrategy implements PointCalculationStrategy {

    static final int TYPE_ID = 1;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() + 1;
    }
}
