package com.bslota.refactoring.library;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


    static class MailDetails {
        private final String title;
        private final String body;
        private final List<String> recipients;

        private MailDetails(String title, String body, List<String> recipients) {
            this.title = title;
            this.body = body;
            this.recipients = recipients;
        }

        static MailDetails from(String title, String body, List<String> recipient) {
            return new MailDetails(title, body, recipient);
        }

        String title() {
            return title;
        }

        String body() {
            return body;
        }

        String[] recipients() {
            return recipients.toArray(new String[]{});
        }
    }
}
