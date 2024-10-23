package oop.AdministratorLogic;

import oop.MedicineStock;

public interface InventoryManagementInterface {
    public void manageInventory();
    public void viewInventory();
    public void addMedicineStock(MedicineStock stock);
    public void updateMedicineStock(String name, int count);
    public void deleteMedicineStock(String name);
    public void updateLowStockLevel(String name, int newLevel);
}