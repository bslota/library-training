package com.bslota.refactoring.library;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;

    BookService(BookRepository bookRepository, PatronRepository patronRepository, NotificationSender emailService, MailDetailsFactory mailDetailsFactory) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.mailDetailsFactory = mailDetailsFactory;
        this.emailService = emailService;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookRepository.findBy(BookId.of(bookId));
        Patron patron = patronRepository.findBy(PatronId.of(patronId));
        boolean flag = false;
        if (thereIsA(book) && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book);
            if (result instanceof BookPlacedOnHold) {
                book.placedOnHold(patron.getPatronId(), days);
                patron.getPatronLoyalties().addLoyaltyPoints();
                if (patron.getPatronLoyalties().isQualifiesForFreeBook()) {
                    sendNotificationToEmployeesAboutFreeBookRewardFor(patron.getPatronLoyalties());
                }
                bookRepository.save(book);
                patronRepository.save(patron);
                flag = true;
            }
        }
        return flag;
    }

    private void sendNotificationToEmployeesAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = mailDetailsFactory.getFreeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

}
