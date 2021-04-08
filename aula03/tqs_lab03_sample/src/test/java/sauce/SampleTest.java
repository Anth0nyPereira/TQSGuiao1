package sauce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
    private WebDriver browser;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/anthonyp/chromedriver_linux64/chromedriver");
        browser = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        browser.close();
    }

    @Test
    public void site_header_is_on_home_page() throws InterruptedException {
        browser.get("https://www.saucelabs.com");
        //Mazimize current window
        browser.manage().window().maximize();

        //Delay execution for 5 seconds to view the maximize operation
        Thread.sleep(5000);

        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        System.out.println(href);
        System.out.println(href.isDisplayed());
        assertTrue((href.isDisplayed()));
    }
}