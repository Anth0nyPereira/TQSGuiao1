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

    @ParameterType("([0-9]{4})")
    public LocalDateTime year(String year) throws ParseException {
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(year), 01, 01, 0, 0);
        return ldt;
    }
    /*
    @ParameterType("(([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }
    */

    @ParameterType("([0-9]{2}) (January/March/August) ([0-9]{4})")
    public LocalDateTime date(String day, String month, String year) {
        int monthNumber = 0;
        switch(month) {
            case "January":
                monthNumber = 1;
                break;

            case "March":
                monthNumber = 3;
                break;

            case "August":
                monthNumber = 8;
                break;

            default:
                monthNumber = 1;
        }
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(day), monthNumber, Integer.parseInt(year),0, 0);
        return ldt;
    }

    @Given("(a|another) book with the title {string} written by {string}, published in {date}")
    public void addNewBook(final String title, final String author, final LocalDateTime published) {
        System.out.println(published);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {year} and {year}")
    public void setSearchParameters(final LocalDateTime from, LocalDateTime to) {
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