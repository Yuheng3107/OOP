package oop.AdministratorLogic;

import oop.Appointment;
import oop.Hospital;
import oop.HospitalStaff;
import oop.MedicineStock;
import oop.Patient;
import oop.MedicineStock;

public class Administrator implements StaffManagementInterface, AppointmentManagementInterface, InventoryManagementInterface, SystemInitialisationInterface {
    public Hospital hospital;


    @Override
    public void viewAppointmentDetails(Appointment appointment)
    {
        // prints out appointment details of the appointment
        // prints patient ID, doctor ID, appointment status
        // date and time of appointment
        // appointment outcome record
        System.out.println("Appointment details: ");
        System.out.println("Patient ID: " + appointment.patientId);
        System.out.println("Doctor ID: " + appointment.doctorId);
        System.out.println("Appointment status: " + appointment.status);
        System.out.println("Date: " + appointment.date);
        System.out.println("Start time: " + appointment.timeSlot.start);
        System.out.println("End time: " + appointment.timeSlot.end);
        System.out.println("Appointment outcome: " + appointment.appointmentOutcome);


    }


    public void manageHospitalStaff()
    {

    }
    public void addStaffMember(HospitalStaff staffMember)
    {

    }
    public void updateStaffMember(HospitalStaff staffMember)
    {
        
    }
    public void removeStaffMember(HospitalStaff staffMember)
    {
        
    }
    public void displayStaff(String filter)
    {

    }
    public void viewInventory()
    {

    }
    public void approveReplenishmentRequest(MedicineStock medicine, int amount)
    {

    }
    public void addMedicineStock(MedicineStock stock)
    {

    }
    public void updateMedicineStock(MedicineStock stock)
    {
        
    }
    public void deleteMedicineStock(MedicineStock stock)
    {
        return;
    }
    
    @Override
    public void updateLowStockLevel(String name, int newLevel) {

    }
    public HospitalStaff[] importStaff(String filename)
    {
        return HospitalStaff[0]; //Filler
    }
    public Patient[] importPatients(String filename)
    {
        return Patient[0]; //Filler
    }
    public Inventory importInventory(String filename)
    {
        return Inventory; //Filler
    }
    public void initialise(String staffFilename, String patientFilename, Inventory inventoryFilename)
    {

    }
}
