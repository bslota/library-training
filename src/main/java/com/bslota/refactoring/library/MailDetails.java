package com.bslota.refactoring.library;

import java.util.List;

/**
 * @author bslota on 12/01/2020
 */
class MailDetails {
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
