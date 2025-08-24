package DAO;

import Model.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.DBConnection;


public class StockDAO {

    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stocks";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Stock stock = new Stock(
                        rs.getInt("id"),
                        rs.getString("symbol"),
                        rs.getString("name"),
                        rs.getDouble("current_price")
                );
                stocks.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public Stock getStockBySymbol(String symbol) {
        Stock stock = null;
        String sql = "SELECT * FROM stocks WHERE symbol = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, symbol);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                stock = new Stock(
                        rs.getInt("id"),
                        rs.getString("symbol"),
                        rs.getString("name"),
                        rs.getDouble("current_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    public void updateStockPrice(int stockId, double newPrice) {
        String sql = "UPDATE stocks SET current_price = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, stockId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Stock getStockById(int id) {
        Stock stock = null;
        String sql = "SELECT * FROM stocks WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                stock = new Stock(
                        rs.getInt("id"),
                        rs.getString("symbol"),
                        rs.getString("name"),
                        rs.getDouble("current_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

}
