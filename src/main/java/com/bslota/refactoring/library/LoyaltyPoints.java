package com.bslota.refactoring.library;

public class LoyaltyPoints {
    private final int value;

    private LoyaltyPoints(int value) {
        this.value = value;
    }

    static LoyaltyPoints of(int value) {
        return new LoyaltyPoints(value);
    }

    int asInt() {
        return this.value;
    }
}