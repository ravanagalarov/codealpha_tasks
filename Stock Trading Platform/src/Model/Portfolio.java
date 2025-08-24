package Model;

public class Portfolio {
    private int id;
    private int userId;
    private int stockId;
    private int quantity;

    public Portfolio() {
    }

    public Portfolio(int id, int userId, int stockId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "StockID: " + stockId + " | Quantity: " + quantity;
    }
}

