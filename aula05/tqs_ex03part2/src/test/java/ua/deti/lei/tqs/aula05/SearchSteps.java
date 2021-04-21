package ua.deti.lei.tqs.aula05;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchSteps {
    private WebDriver webDriver;

    @Quando("eu navego para a página {string}")
    public void navegarParaPaginaHome(String url) {
        System.setProperty("webdriver.chrome.driver", "/home/anthonyp/chromedriver_linux64/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @E("eu escolho como cidade de origem {string}")
    public void escolherCidadeOrigem(String departure) {
        // check https://www.guru99.com/select-option-dropdown-selenium-webdriver.html
        Select departureSelect = new Select(webDriver.findElement(By.name("fromPort")));
        departureSelect.selectByVisibleText(departure);
    }

    @E("eu escolho como cidade de destino {string}")
    public void escolherCidadeDestino(String destination) {
        Select destinationSelect = new Select(webDriver.findElement(By.name("toPort")));
        destinationSelect.selectByVisibleText(destination);
    }

    @E("eu clico em Find Flights")
    public void pesquisarVoos() {
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Entao("eu sou redirecionado para a página {string}")
    public void navegarPaginaVoos(String url) {
        webDriver.get(url);
    }

    @E("no cabeçalho da página aparecem tanto {word} como {word}")
    public void confirmarCidades(String departure, String destination) {
        assertThat(webDriver.findElement(By.cssSelector("h3")).getText(), is("Flights from " + departure + " to " + destination + ":"));
    }
}
