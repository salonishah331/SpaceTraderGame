package sample;

public class Good {

    private int buyPrice;
    private int sellPrice;
    private int quantity;
    private String name;

    public Good(String name, int regionTechLevel, int basePrice) {
        this.buyPrice = basePrice + (regionTechLevel * Main.getUser().getMerchant());
        this.sellPrice = basePrice + (regionTechLevel * 2);
        this.name = name;
        this.quantity = 1;
    }

    public String toString() {
        return "Name: " + this.name + " | Quantity: " + this.quantity
                + " | Sell Price: " + this.sellPrice + " | Buy Price: " + this.buyPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
