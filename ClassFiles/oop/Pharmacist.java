package oop;

import oop.AdministratorLogic.Inventory;
import oop.AdministratorLogic.Administrator;
import oop.UserLogic.Role;
import oop.MedicineStock;

public class Pharmacist extends HospitalStaff {

    private String pharmacistID;
    
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
        this.pharmacistID = staffID;
        Hospital.staff.add(this);
    }
    private Inventory inventory;

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

    }
    public void submitReplenishRequest(MedicineStock medicine, int amount, Administrator administrator)
    {
        
    }
}
