package oop.AdministratorLogic;

import oop.MedicineStock;
import oop.Hospital;

public class ReplenishmentRequest {
    public int amount;
    public String medicineName;
    public String status = "Pending"; //When administrator approve, update it to Approved please

    
    /** 
     * @param name
     * @param amt
     * @return 
     */
    public ReplenishmentRequest(String name, int amt)
    {
        amount = amt;
        medicineName = name;
    }

    public void setApproved() {
        if (status.equals("Approved")) {
            System.out.println("Replenishment request already approved.");
            return;
        }
        // otherwise set it to approved and update inventory
        status = "Approved";

        for (MedicineStock stock : Hospital.inventory) {
            if (stock.getName().equals(medicineName)) {
                stock.setStock(stock.getStock() + amount);
            }
        }
    }

    public void printInfo() {
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }
}
