package com.hascode.tutorial.cucumber.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public Date date(String year, String month, String day) {
       LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
       return date;
    }

    @ParameterType("[0-9]{4}")
    public Date year(String initialYear) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date year = sdf.parse(initialYear);
        System.out.println(year);
        return year;
    }
/*
    @Given("(a|another) book with the title {string} written by {string}, published in {date}")
    public void addNewBook(final String title, final String author, final Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

 */

    @Given("(a|another) book with the title {string}, written by {string}, published in {int} - {int} - {int}")
    public void addNewBook(final String title, final String author, final int year, final int month, final int day) {
        LocalDateTime ldt = LocalDateTime.of(year, month, day, 0, 0);
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        Book book = new Book(title, author, date);
        library.addBook(book);
    }

    @When("the customer searches for books published between {year} and {year}")
    public void setSearchParameters(final Date from, final Date to) {
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }
}