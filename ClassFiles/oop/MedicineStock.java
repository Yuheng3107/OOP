package oop;

/**
 * The {@code MedicineStock} class represents a medicine item in a pharmacy or hospital stock system.
 * It keeps track of essential information such as the medicine's name, stock quantity, price, and low stock level.
 * This class also allows updating the stock and managing stock levels, including the rolling stock which reflects stock movements.
 * 
 * <p>The {@code MedicineStock} class provides methods to get and set the details of the medicine, including
 * stock quantity, price, and low stock threshold.</p>
 * @author: Kuang Yu Heng, Kuang Yu Xuan, Ryan Ching
 * @version: 1.0
 * @since: 2024-11-09
 */
public class MedicineStock {
    /**
     * The name of the medicine.
     */
    private String name;

    /**
     * The current stock quantity of the medicine.
     */
    private int stock;

    /**
     * The threshold level for low stock warning.
     */
    public int lowStockLevel;

    /**
     * The price of the medicine.
     */
    private int price;

    /**
     * The rolling stock reflects the current stock level after any recording of appointments and pending of medication collection.
     */
    private int rollingStock = 0;

    /**
     * Constructs a new {@code MedicineStock} instance with the specified name, stock quantity, low stock level, and price.
     *
     * @param name The name of the medicine.
     * @param stock The current stock of the medicine.
     * @param lowStockLevel The low stock level that triggers a warning.
     * @param price The price of the medicine.
     */
    public MedicineStock(String name, int stock, int lowStockLevel, int price) {
        this.name = name;
        this.stock = stock;
        this.lowStockLevel = lowStockLevel;
        this.price = price;
    }

    /**
     * Returns the name of the medicine.
     *
     * @return The name of the medicine.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the medicine.
     *
     * @param name The new name of the medicine.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the current stock quantity of the medicine.
     *
     * @return The current stock of the medicine.
     */
    public int getStock()
    {
        return stock;
    }

    /**
     * Sets the stock quantity of the medicine.
     *
     * @param newStock The new stock quantity for the medicine.
     */
    public void setStock(int newStock)
    {
        this.stock = newStock;
    }

    /**
     * Returns the price of the medicine.
     *
     * @return The price of the medicine.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Returns the low stock level threshold.
     *
     * @return The low stock level for the medicine.
     */
    public int getLowStockLevel() {
        return this.lowStockLevel;
    }

    /**
     * Sets the low stock level threshold for the medicine.
     *
     * @param lowStockLevel The new low stock level for the medicine.
     */
    public void setLowStockLevel(int lowStockLevel)
    {
        this.lowStockLevel = lowStockLevel;
    }

    /**
     * Returns the rolling stock, which reflects the stocks that are pending to be collected by the patient.
     *
     * @return The rolling stock of the medicine.
     */
    public int getRollingStock()
    {
        return rollingStock;
    }

    /**
     * Sets the rolling stock level of the medicine.
     *
     * @param newRollingStock The new rolling stock level.
     */
    public void setRollingStock(int newRollingStock)
    {
        rollingStock = newRollingStock;
    }
}
