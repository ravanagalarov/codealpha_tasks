package Service;

import DAO.PortfolioDAO;
import DAO.StockDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Model.Portfolio;
import Model.Stock;
import Model.Transaction;
import Model.User;

import java.sql.SQLException;
import java.util.List;

public class TradingService {

    private UserDAO userDAO;
    private StockDAO stockDAO;
    private PortfolioDAO portfolioDAO;
    private TransactionDAO transactionDAO;

    public TradingService() {
        userDAO = new UserDAO();
        stockDAO = new StockDAO();
        portfolioDAO = new PortfolioDAO();
        transactionDAO = new TransactionDAO();
    }

    public void buyStock(int userId, String stockSymbol, int quantity) throws SQLException {
        Stock stock = stockDAO.getStockBySymbol(stockSymbol);
        if (stock == null) {
            System.out.println("Stock not found: " + stockSymbol);
            return;
        }

        User user = userDAO.getUserById(userId);
        double totalPrice = stock.getCurrentPrice() * quantity;

        if (user.getBalance() < totalPrice) {
            System.out.println("Insufficient balance!");
            return;
        }


        userDAO.updateBalance(userId, user.getBalance() - totalPrice);


        List<Portfolio> portfolio = portfolioDAO.getUserPortfolio(userId);
        boolean hasStock = false;
        for (Portfolio p : portfolio) {
            if (p.getStockId() == stock.getId()) {
                portfolioDAO.updateStockQuantity(userId, stock.getId(), p.getQuantity() + quantity);
                hasStock = true;
                break;
            }
        }
        if (!hasStock) {
            portfolioDAO.addStockToPortfolio(userId, stock.getId(), quantity);
        }

        // Add transaction record
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setStockId(stock.getId());
        transaction.setQuantity(quantity);
        transaction.setPricePerStock(stock.getCurrentPrice());
        transaction.setType("BUY");
        transaction.setDateTime(java.time.LocalDateTime.now());

        transactionDAO.addTransaction(transaction);

        System.out.println("Purchase completed successfully!");
    }


    public void sellStock(int userId, String stockSymbol, int quantity) throws SQLException {
        Stock stock = stockDAO.getStockBySymbol(stockSymbol);
        if (stock == null) {
            System.out.println("Stock not found: " + stockSymbol);
            return;
        }

        List<Portfolio> portfolio = portfolioDAO.getUserPortfolio(userId);
        Portfolio ownedStock = null;
        for (Portfolio p : portfolio) {
            if (p.getStockId() == stock.getId()) {
                ownedStock = p;
                break;
            }
        }

        if (ownedStock == null || ownedStock.getQuantity() < quantity) {
            System.out.println("Not enough stocks to sell!");
            return;
        }

        User user = userDAO.getUserById(userId);
        double totalPrice = stock.getCurrentPrice() * quantity;

        userDAO.updateBalance(userId, user.getBalance() + totalPrice);

        int newQuantity = ownedStock.getQuantity() - quantity;
        if (newQuantity > 0) {
            portfolioDAO.updateStockQuantity(userId, stock.getId(), newQuantity);
        } else {

            portfolioDAO.updateStockQuantity(userId, stock.getId(), 0);
        }


        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setStockId(stock.getId());
        transaction.setQuantity(quantity);
        transaction.setPricePerStock(stock.getCurrentPrice());
        transaction.setType("SELL");
        transaction.setDateTime(java.time.LocalDateTime.now());

        transactionDAO.addTransaction(transaction);

        System.out.println("Sale completed successfully!");
    }


    public void displayMarketData() throws SQLException {
        List<Stock> stocks = stockDAO.getAllStocks();
        System.out.println("=== Market Data ===");
        for (Stock s : stocks) {
            System.out.printf("Symbol: %s | Name: %s | Price: %.2f\n", s.getSymbol(), s.getName(), s.getCurrentPrice());
        }
    }


    public void displayUserPortfolio(int userId) throws SQLException {
        List<Portfolio> portfolio = portfolioDAO.getUserPortfolio(userId);
        if (portfolio.isEmpty()) {
            System.out.println("Your portfolio is empty.");
            return;
        }

        System.out.println("=== Your Portfolio ===");
        for (Portfolio p : portfolio) {
            Stock stock = stockDAO.getStockById(p.getStockId());
            System.out.printf("Symbol: %s | Quantity: %d\n", stock.getSymbol(), p.getQuantity());
        }
    }
}

