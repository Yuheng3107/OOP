package oop.AdministratorLogic;

public class ReplenishmentRequest {
    public int amount;
    public String medicineName;
    public String status = "Pending"; //When administrator approve, update it to Approved please

    public ReplenishmentRequest(String name, int amt)
    {
        amount = amt;
        medicineName = name;
    }

    public void setApproved() {
        status = "Approved";
    }

    public void printInfo() {
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }
}
