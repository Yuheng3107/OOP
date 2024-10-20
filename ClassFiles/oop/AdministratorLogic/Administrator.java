package oop.AdministratorLogic;

import oop.Appointment;
import oop.Hospital;
import oop.HospitalStaff;
import oop.MedicineStock;
import oop.Patient;
import oop.MedicineStock;
import oop.AppointmentOutcome;
import java.util.Scanner;
import oop.Gender;

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
        // print appointment outcomes
        for (AppointmentOutcome outcome : appointment.appointmentOutcome) {
            // TODO implement logic to get appointment outcomes
        }
    }


    public void manageHospitalStaff()
    {
        // choose whether to add, update, remove or delete staff member
        System.out.println("Choose an option: ");
        System.out.println("1. Add staff member");
        System.out.println("2. Update staff member");
        System.out.println("3. Remove staff member");
        System.out.println("4. Display staff members");
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();

        switch (opt) {
            case 1:
                // add staff member
                System.out.println("Enter staff name: ");
                String staffName = sc.next();

                System.out.println("Enter staff age: ");
                int age = sc.nextInt();

                System.out.println("Enter staff ID: ");
                String staffID = sc.next();

                System.out.println("Enter staff gender: ");
                try {
                    String genderString = sc.next();
                    Gender gender = Gender.valueOf(genderString);
                    addStaffMember(staffName, age, staffID, gender);
                }

                catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender. Please enter M or F.");
                }   

                break;
            case 2:
                // update staff member
                updateStaffMember(null);

                break;
            case 3:
                // remove staff member
                System.out.println("Enter name of staff member to remove: ");
                String name = sc.next();
                removeStaffMember(name);            
                break;
            case 4:
                // display staff members, choose filter
                System.out.println("Enter filter: ");
                String filter = sc.next();
                displayStaff(filter);
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }

        sc.close();

    }
    public void addStaffMember(String staffName, int age, String staffID, Gender gender)
    {

    }
    public void updateStaffMember(String staffName)
    {
        // not sure what to update, gender???
        
    }
    public void removeStaffMember(String staffName)
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
