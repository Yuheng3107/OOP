package oop.AdministratorLogic;

import oop.MedicineStock;
/**
 * Interface to define the methods for managing the inventory of medicines.
 * This interface is implemented by classes that provide functionality for 
 * adding, updating, viewing, and managing the stock of medicines in the inventory.
 * @author: Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public interface InventoryManagementInterface {
    /**
     * Manages the inventory, which includes tasks like updating stock levels, 
     * and ensuring the integrity of the inventory system.
     */
    public void manageInventory();

    /**
     * Views the current inventory of medicines.
     * This method displays the list of all available medicines along with their stock count.
     */
    public void viewInventory();

    /**
     * Adds a new medicine stock entry to the inventory.
     * @param stock The MedicineStock object representing the medicine and its details to be added.
     */
    public void addMedicineStock(MedicineStock stock);

    /**
     * Updates the stock information of a particular medicine.
     * @param name The name of the medicine whose stock is to be updated.
     * @param count The new quantity of the medicine in stock.
     * @param newPrice The new price of the medicine.
     */
    public void updateMedicineStock(String name, int count, int newPrice);

    /**
     * Deletes a medicine from the inventory.
     * @param name The name of the medicine to be deleted from the inventory.
     */
    public void deleteMedicineStock(String name);

    /**
     * Updates the low stock level for a specific medicine.
     * @param name The name of the medicine whose low stock level is to be updated.
     * @param newLevel The new low stock level for the medicine.
     */
    public void updateLowStockLevel(String name, int newLevel);
}