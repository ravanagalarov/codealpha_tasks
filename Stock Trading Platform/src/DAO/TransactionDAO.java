package DAO;

import Model.Transaction;
import Connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, stock_id, quantity, price_per_stock, type, date_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getStockId());
            pstmt.setInt(3, transaction.getQuantity());
            pstmt.setDouble(4, transaction.getPricePerStock());
            pstmt.setString(5, transaction.getType());
            pstmt.setTimestamp(6, Timestamp.valueOf(transaction.getDateTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionsByUser(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("stock_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price_per_stock"),
                        rs.getString("type"),
                        rs.getTimestamp("date_time").toLocalDateTime()
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

