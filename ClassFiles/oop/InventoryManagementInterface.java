package oop;
public interface InventoryManagementInterface {
    public void viewInventory();
    public void addMedicineStock(String name, int count);
    public void updateMedicineStock(String name, int count);
    public void deleteMedicineStock(String name);
    public void updateLowStockLevel(String name, int newLevel);
    public void addMedicineStock(MedicineStock stock);
}