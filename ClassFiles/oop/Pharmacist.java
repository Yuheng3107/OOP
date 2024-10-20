package oop;

public class Pharmacist extends HospitalStaff {
    
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
    }
    private Inventory inventory;

    public void viewAppointmentOutcomeRecord(AppointmentOutcome appointmentOutcome)
    {

    }
    public void updatePrescriptionStatus(String status, AppoointmentOutcome appointmentOutcome)
    {

    }
    public void viewMedicationInventory()
    {

    }
    public void submitReplenishRequest(Medicine medicine, int amount, Administrator administrator)
    {
        
    }
}
