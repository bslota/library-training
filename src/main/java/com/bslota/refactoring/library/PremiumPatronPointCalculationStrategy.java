package com.bslota.refactoring.library;

/**
 * @author bslota on 12/01/2020
 */
class PremiumPatronPointCalculationStrategy implements PointCalculationStrategy {

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() == 0 ? 100 : loyalties.getPoints() * 2;
    }
}
