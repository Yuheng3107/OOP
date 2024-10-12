public interface InventoryManagementInterface {
    public void viewInventory();
    public void addMedicineStock(String name, int count);
    public void updateMedicineStock(String name, int count);
    public void deleteMedicineStock(String name);
    public type updateLowStockLevel(String name, int newLevel);
    public void addMedicineStock(MedicineStock stock);
}