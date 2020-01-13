package com.bslota.refactoring.library;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookDAO bookDAO;
    private final PatronDAO patronDAO;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;
    private final PatronLoyaltiesDAO patronLoyaltiesDAO;

    BookService(BookDAO bookDAO, PatronDAO patronDAO, NotificationSender emailService, MailDetailsFactory mailDetailsFactory, PatronLoyaltiesDAO patronLoyaltiesDAO) {
        this.bookDAO = bookDAO;
        this.patronDAO = patronDAO;
        this.mailDetailsFactory = mailDetailsFactory;
        this.emailService = emailService;
        this.patronLoyaltiesDAO = patronLoyaltiesDAO;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookDAO.getBookFromDatabase(bookId);
        Patron patron = patronDAO.getPatronFromDatabase(patronId);
        boolean flag = false;
        if (thereIsA(book) && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book);
            if (result instanceof BookPlacedOnHold) {
                book.placedOnHold(patron.getPatronId(), days);
                PatronLoyalties patronLoyalties = getPatronLoyalties(patron.getPatronId());
                patronLoyalties.addLoyaltyPoints();
                if (patronLoyalties.isQualifiesForFreeBook()) {
                    sendNotificationToEmployeesAboutFreeBookRewardFor(patronLoyalties);
                }
                bookDAO.update(book);
                patronDAO.update(patron);
                patronLoyaltiesDAO.update(patronLoyalties);
                flag = true;
            }
        }
        return flag;
    }

    private PatronLoyalties getPatronLoyalties(PatronId patronId) {
        return Optional.ofNullable(patronLoyaltiesDAO.getLoyaltiesFromDatabase(patronId))
                .orElse(PatronLoyalties.emptyFor(patronId));
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
