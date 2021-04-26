package ua.deti.tqs.aula05.book;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})")
    public LocalDateTime yearDate(String yearDate) {
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(yearDate), 01, 01, 0, 0);
        return ldt;
    }

    @ParameterType("([0-9]{2})")
    public int day(String day) {
        int dayInt = Integer.parseInt(day);
        return dayInt;
    }

    @ParameterType("([0-9]{4})")
    public int year(String year) {
        int yearInt = Integer.parseInt(year);
        return yearInt;
    }

    @ParameterType("January|March|August")
    public Month month(String month) {
        Month month_;
        switch(month) {
            case "January":
                month_ = Month.JANUARY;
                break;

            case "March":
                month_ = Month.MARCH;
                break;

            case "August":
                month_ = Month.AUGUST;
                break;

            default:
                month_ = Month.JANUARY;
        }
        return month_;
    }

    public LocalDateTime makeDate(int day, Month month, int year) {
        LocalDateTime ldt = LocalDateTime.of(year, month, day,0, 0);
        System.out.println(ldt);
        return ldt;
    }


    @Given("(a/another) book with the title {string}, written by {string}, published in {day} {month} {year}")
    public void addNewBook(final String title, final String author, int day, Month month, int year) {
        LocalDateTime published = makeDate(day, month, year);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {yearDate} and {yearDate}")
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