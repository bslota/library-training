package com.bslota.refactoring.library;

/**
 * @author bslota on 12/01/2020
 */
interface PointCalculationStrategy {

    int calculate(PatronLoyalties loyalties);

    static PointCalculationStrategy from(int type) {
        if (type == RegularPatronPointCalculationStrategy.TYPE_ID) {
            return new RegularPatronPointCalculationStrategy();
        }
        if (type == ResearcherPatronPointCalculationStrategy.TYPE_ID) {
            return new ResearcherPatronPointCalculationStrategy();
        }
        return new PremiumPatronPointCalculationStrategy();
    }
}
