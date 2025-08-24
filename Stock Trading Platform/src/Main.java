import Service.TradingService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static TradingService tradingService = new TradingService();
    private static Scanner scanner = new Scanner(System.in);

    static int userId = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to the Trading Platform!");



        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show My Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1:
                        tradingService.displayMarketData();
                        break;
                    case 2:
                        buyStock(userId);
                        break;
                    case 3:
                        sellStock(userId);
                        break;
                    case 4:
                        tradingService.displayUserPortfolio(userId);
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void buyStock(int userId) throws SQLException {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        tradingService.buyStock(userId, symbol, quantity);
    }

    private static void sellStock(int userId) throws SQLException {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        tradingService.sellStock(userId, symbol, quantity);
    }
}
