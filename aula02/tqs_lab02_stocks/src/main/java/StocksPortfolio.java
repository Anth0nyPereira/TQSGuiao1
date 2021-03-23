import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private String name;
    private IStockMarket market;
    private List<Stock> stockList;

    public StocksPortfolio(IStockMarket market) {
        this.market = market;
        this.stockList = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IStockMarket getMarketService() {
        return market;
    }

    public void setMarketService(IStockMarket market) {
        this.market = market;
    }

    public void addStock(Stock stock) {
        this.stockList.add(stock);
    }

    public double getTotalValue() {
        double total = 0;
        for (Stock stock: stockList) {
            total = total + stock.getQuantity() * market.getPrice(stock.getName());
        }
        return total;
    }
}
