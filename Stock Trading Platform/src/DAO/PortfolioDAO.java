package DAO;

import Model.Portfolio;
import Connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioDAO {

    public List<Portfolio> getUserPortfolio(int userId) {
        List<Portfolio> portfolioList = new ArrayList<>();
        String sql = "SELECT * FROM portfolio WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Portfolio portfolio = new Portfolio(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("stock_id"),
                        rs.getInt("quantity")
                );
                portfolioList.add(portfolio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return portfolioList;
    }

    public void addStockToPortfolio(int userId, int stockId, int quantity) {
        String sql = "INSERT INTO portfolio (user_id, stock_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, stockId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStockQuantity(int userId, int stockId, int quantity) {
        String sql = "UPDATE portfolio SET quantity = ? WHERE user_id = ? AND stock_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, stockId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

