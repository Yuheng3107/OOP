package oop;

import oop.AdministratorLogic.Inventory;
import oop.AdministratorLogic.Administrator;
import oop.UserLogic.Role;
import oop.MedicineStock;

public class Pharmacist extends HospitalStaff {
    
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
        Hospital.staff.add(this);
    }
    private Inventory inventory;

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
