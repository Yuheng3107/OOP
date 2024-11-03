package oop;

import oop.AdministratorLogic.ReplenishmentRequest;
import java.util.Scanner;

public class Pharmacist extends HospitalStaff {

    private String pharmacistID;
    
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
        this.pharmacistID = staffID;
        Hospital.staffs.add(this);
    }

    public String getID()
    {
        return pharmacistID;
    }

    public void viewAppointmentOutcomeRecord(AppointmentOutcome appointmentOutcome)
    {

    }
    public void updatePrescriptionStatus(String status, AppointmentOutcome appointmentOutcome)
    {

    }
    public void viewMedicationInventory()
    {
        for (MedicineStock medStock : Hospital.medStocks)
        {
            System.out.println(medStock.getName() + "'s stock: " + medStock.getStock());
        }
    }

    public void submitReplenishRequest()
    {
        Scanner sc = new Scanner(System.in);
        String medName;
        int medNewStock;
        viewMedicationInventory();
        System.out.print("\nEnter the name of the medicine to submit: ");
        medName = sc.nextLine();
        System.out.print("Enter quantity of the medicine in replenishment request: ");
        medNewStock = Integer.parseInt(sc.nextLine());
        ReplenishmentRequest request = new ReplenishmentRequest(medName, medNewStock);
        Hospital.replenishmentRequests.add(request);
    }
}
