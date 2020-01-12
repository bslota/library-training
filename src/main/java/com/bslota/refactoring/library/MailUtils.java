package com.bslota.refactoring.library;

import java.util.Collections;

/**
 * @author bslota on 12/01/2020
 */
class MailUtils {

    static MailDetails freeBookRewardNotificationFor(PatronLoyalties patronLoyalties) {
        String title = "[REWARD] Patron for free book reward waiting";
        String body = "Dear Colleague, \n" +
                "One of our patrons with ID " + patronLoyalties.getPatronId() + " gathered " + patronLoyalties.getPoints() + ". \n" +
                "Please contact him and prepare a free book reward!";
        String employees = "customerservice@your-library.com";
        return MailDetails.from(title, body, Collections.singletonList(employees));
    }

}
