package com.bslota.refactoring.library;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.bslota.refactoring.library.PatronFixture.PatronBuilder.newPatron;
import static java.util.stream.Collectors.toList;

/**
 * @author bslota on 15/11/2019
 */
class PatronFixture {

    private static final PatronId SOME_PATRON_ID = PatronId.of(10);

    static Patron patronWithoutHolds() {
        return newPatron().build();
    }

    static Patron patronWithMaxNumberOfHolds() {
        return newPatron()
                .withHolds(IntStream.range(0, 5).boxed().collect(toList()))
                .build();
    }

    static class PatronBuilder {
        private PatronId patronId = SOME_PATRON_ID;
        private List<Integer> holds = new LinkedList<>();

        static PatronBuilder newPatron() {
            return new PatronBuilder();
        }


        PatronBuilder withHolds(List<Integer> holds) {
            this.holds = holds;
            return this;
        }

        PatronBuilder withId(PatronId patronId) {
            this.patronId = patronId;
            return this;
        }

        Patron build() {
            return new Patron(patronId, holds);
        }
    }
}
