import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
// hamcrest imports
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    // prepare a mock to substitute the remote service (@Mock annotation)
    @Mock
    IStockMarket marketService;

    // create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance
    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {

        // load the mock with the proper expectations (when...thenReturn)
        when(marketService.getPrice("GLENAT")).thenReturn(2.5);
        when(marketService.getPrice("KUROKAWA")).thenReturn(4.75);

        // execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("GLENAT", 5));
        portfolio.addStock(new Stock("KUROKAWA", 10));
        double totalResult = portfolio.getTotalValue();

        // verify the result (assert) and the use of the mock (verify)
        assertEquals(60.0, totalResult);
        verify(marketService, times(2)).getPrice(anyString());
    }

    @Test
    void getTotalValueHamcrest() {
        // load the mock with the proper expectations (when...thenReturn)
        when(marketService.getPrice("GLENAT")).thenReturn(2.5);
        when(marketService.getPrice("KUROKAWA")).thenReturn(4.75);

        // execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("GLENAT", 5));
        portfolio.addStock(new Stock("KUROKAWA", 10));
        double totalResult = portfolio.getTotalValue();

        // verify the result (assert) and the use of the mock (verify)
        assertThat(totalResult, is(60.0));
        verify(marketService, times(2)).getPrice(anyString());
    }
}