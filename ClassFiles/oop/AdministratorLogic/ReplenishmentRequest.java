package oop.AdministratorLogic;

import oop.MedicineStock;
import oop.Hospital;

/**
 * Represents a request for replenishing a medicine stock in the hospital's inventory.
 * A replenishment request contains the name of the medicine, the amount requested,
 * and its approval status. The request can be approved by the administrator, which
 * updates the inventory accordingly.
 * @author: Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public class ReplenishmentRequest {
    /** The amount of medicine requested for replenishment. */
    public int amount;

    /** The name of the medicine to be replenished. */
    public String medicineName;

     /** The status of the replenishment request. Defaults to "Pending". */
    public String status = "Pending"; //When administrator approve, update it to Approved please

    
    /**
     * Create a new replenishment request with the specified medicine name and amount.
     * 
     * @param name The name of the medicine to be replenished.
     * @param amt The amount of medicine requested for replenishment.
     */
    public ReplenishmentRequest(String name, int amt)
    {
        amount = amt;
        medicineName = name;
    }

    /**
     * Approves the replenishment request and updates the inventory.
     * If the request is already approved, a message is displayed and no action is taken.
     * 
     * The method searches for the specified medicine in the hospital's inventory and
     * updates its stock by adding the requested amount.
     */
    public void setApproved() {
        if (status.equals("Approved")) {
            System.out.println("Replenishment request already approved.");
            return;
        }
        // otherwise set it to approved and update inventory
        status = "Approved";

        for (MedicineStock stock : Hospital.inventory) {
            if (stock.getName().equalsIgnoreCase(medicineName.toLowerCase())) {
                stock.setStock(stock.getStock() + amount);
            }
        }
    }

    /**
     * Prints the details of the replenishment request, including the medicine name,
     * requested amount, and current status.
     */
    public void printInfo() {
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }
}
