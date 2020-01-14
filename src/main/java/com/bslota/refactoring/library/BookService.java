package com.bslota.refactoring.library;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;
    private final PatronLoyaltiesRepository patronLoyaltiesRepository;

    BookService(BookRepository bookRepository, PatronRepository patronRepository, NotificationSender emailService, MailDetailsFactory mailDetailsFactory, PatronLoyaltiesRepository patronLoyaltiesRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.mailDetailsFactory = mailDetailsFactory;
        this.emailService = emailService;
        this.patronLoyaltiesRepository = patronLoyaltiesRepository;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookRepository.findBy(BookId.of(bookId));
        Patron patron = patronRepository.findBy(PatronId.of(patronId));
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
                bookRepository.save(book);
                patronRepository.save(patron);
                patronLoyaltiesRepository.update(patronLoyalties);
                flag = true;
            }
        }
        return flag;
    }

    private PatronLoyalties getPatronLoyalties(PatronId patronId) {
        return Optional.ofNullable(patronLoyaltiesRepository.getLoyaltiesFromDatabase(patronId))
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
