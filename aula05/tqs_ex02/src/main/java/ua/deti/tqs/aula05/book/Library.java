package ua.deti.tqs.aula05.book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDateTime from, final LocalDateTime to) {
        Calendar end = Calendar.getInstance();
        // https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        Date fromDate = java.sql.Timestamp.valueOf(from);
        Date toDate = java.sql.Timestamp.valueOf(to);
        end.setTime(toDate);
        end.roll(Calendar.YEAR, 1);

        return store.stream().filter(book -> {
            return fromDate.before(java.sql.Timestamp.valueOf(book.getPublished())) && end.getTime().after(java.sql.Timestamp.valueOf(book.getPublished()));
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book: store) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }
}