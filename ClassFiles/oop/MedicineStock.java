package oop;
public class MedicineStock {
    private String name;
    private int stock;
    public int lowStockLevel;
    private int price;

    public MedicineStock(String name, int stock, int lowStockLevel, int price) {
        this.name = name;
        this.stock = stock;
        this.lowStockLevel = lowStockLevel;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getStock()
    {
        return stock;
    }
    public void setStock(int newStock)
    {
        this.stock = newStock;
    }
    public int getPrice()
    {
        return price;
    }

    public int getLowStockLevel() {
        return this.lowStockLevel;
    }

    public void setLowStockLevel(int lowStockLevel) {
        this.lowStockLevel = lowStockLevel;
    }
}
