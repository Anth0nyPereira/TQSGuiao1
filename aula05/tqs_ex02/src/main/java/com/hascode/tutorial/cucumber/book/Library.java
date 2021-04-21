package com.hascode.tutorial.cucumber.book;

import java.time.Instant;
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
        Date fromDate = Date.from(Instant.from(from));
        Date toDate = Date.from(Instant.from(to));
        end.setTime(toDate);
        end.roll(Calendar.YEAR, 1);

        return store.stream().filter(book -> {
            return fromDate.before(Date.from(Instant.from(book.getPublished()))) && end.getTime().after(Date.from(Instant.from(book.getPublished())));
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }
}