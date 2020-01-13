package com.bslota.refactoring.library;

import static com.bslota.refactoring.library.PatronLoyaltiesFixture.PatronLoyaltiesBuilder.newPatronLoyalties;

/**
 * @author bslota on 15/11/2019
 */
class PatronLoyaltiesFixture {

    private static final PatronId SOME_PATRON_ID = PatronId.of(10);

    static PatronLoyalties patronQualifyingForFreeBook() {
        return newPatronLoyalties()
                .withPoints(10000)
                .build();
    }

    static class PatronLoyaltiesBuilder {
        private PatronId patronId = SOME_PATRON_ID;
        private int type;
        private int points;
        private boolean qualifiesForFreeBook;

        static PatronLoyaltiesBuilder newPatronLoyalties() {
            return new PatronLoyaltiesBuilder();
        }

        PatronLoyaltiesBuilder withType(int type) {
            this.type = type;
            return this;
        }

        PatronLoyaltiesBuilder withPoints(int points) {
            this.points = points;
            return this;
        }

        PatronLoyaltiesBuilder withQualifiesForFreeBook(boolean qualifiesForFreeBook) {
            this.qualifiesForFreeBook = qualifiesForFreeBook;
            return this;
        }


        PatronLoyaltiesBuilder withId(PatronId patronId) {
            this.patronId = patronId;
            return this;
        }

        PatronLoyalties build() {
            return new PatronLoyalties(patronId, type, points, qualifiesForFreeBook);
        }
    }
}
