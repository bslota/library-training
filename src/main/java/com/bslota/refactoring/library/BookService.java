package com.bslota.refactoring.library;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookDAO bookDAO;
    private final PatronDAO patronDAO;
    private final NotificationSender emailService;

    BookService(BookDAO bookDAO, PatronDAO patronDAO, NotificationSender emailService) {
        this.bookDAO = bookDAO;
        this.patronDAO = patronDAO;
        this.emailService = emailService;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookDAO.getBookFromDatabase(bookId);
        Patron patron = patronDAO.getPatronFromDatabase(patronId);
        boolean flag = false;
        if (thereIsA(book) && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book);
            if (result instanceof BookPlacedOnHold) {
                book.placedOnHold(patron.getPatronId(), days);
                addLoyaltyPoints(patron.getPatronLoyalties());
                if (patron.getPatronLoyalties().isQualifiesForFreeBook()) {
                    sendNotificationToEmployeesAboutFreeBookRewardFor(patron.getPatronLoyalties());
                }
                bookDAO.update(book);
                patronDAO.update(patron);
                flag = true;
            }
        }
        return flag;
    }

    private void sendNotificationToEmployeesAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = MailUtils.freeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

    private void addLoyaltyPoints(PatronLoyalties patronLoyalties) {
        int type = patronLoyalties.getType();
        switch (type) {
            case 0: // regular patron
                patronLoyalties.setPoints(patronLoyalties.getPoints() + 1);
                break;
            case 1: // researcher
                patronLoyalties.setPoints(patronLoyalties.getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (patronLoyalties.getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = patronLoyalties.getPoints() * 2;
                }
                patronLoyalties.setPoints(newPoints);
                break;
            default:
                break;
        }
        if (patronLoyalties.getPoints() > 10000) {
            patronLoyalties.setQualifiesForFreeBook(true);
        }
    }

}
