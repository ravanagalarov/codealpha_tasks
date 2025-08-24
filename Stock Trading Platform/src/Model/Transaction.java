package Model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int userId;
    private int stockId;
    private int quantity;
    private double pricePerStock;
    private String type;
    private LocalDateTime dateTime;

    public Transaction() {
    }

    public Transaction(int id, int userId, int stockId, int quantity, double pricePerStock, String type, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
        this.type = type;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerStock() {
        return pricePerStock;
    }

    public void setPricePerStock(double pricePerStock) {
        this.pricePerStock = pricePerStock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return type + " | StockID: " + stockId + " | Qty: " + quantity + " | Price: $" + pricePerStock + " | Date: " + dateTime;
    }
}

