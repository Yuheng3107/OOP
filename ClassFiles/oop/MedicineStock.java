package oop;
public class MedicineStock {
    private String name;
    private int stock;
    public int lowStockLevel;

    public MedicineStock(String name, int stock, int lowStockLevel) {
        this.name = name;
        this.stock = stock;
        this.lowStockLevel = lowStockLevel;
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
}
