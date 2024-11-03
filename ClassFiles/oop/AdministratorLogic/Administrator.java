package oop.AdministratorLogic;

import oop.Appointment;
import oop.Hospital;
import oop.HospitalStaff;
import oop.MedicineStock;
import oop.Patient;
import oop.Pharmacist;
import oop.AppointmentOutcome;
import oop.BloodType;
import oop.Doctor;
import oop.UserLogic.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import oop.Gender;
import oop.AdministratorLogic.ReplenishmentRequest;
public class Administrator extends HospitalStaff implements StaffManagementInterface, AppointmentManagementInterface, InventoryManagementInterface {
    
    private String id;
    private int age;

    public Administrator(String name, String id, Gender gender, int age)
    {
        super(name, id, age, gender);
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getAdminID()
    {
        return id;
    }

    public void manageInventory() {
        // choose whether to add, update, remove or delete inventory
        System.out.println("Choose an option: ");
        System.out.println("1. Add to inventory");
        System.out.println("2. Update medicine stock in inventory");
        System.out.println("3. Remove medicine from inventory");
        System.out.println("4. View Inventory");
        System.out.println("5. Update low stock level alert line of medicine");
        System.out.println("6. Approve replenishment request:");
        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String name;
        int quantity;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:

                // add inventory
                System.out.println("Enter name of the medicine: ");
                name = scanner.next();
                System.out.println("Enter quantity of the medicine: ");
                quantity = scanner.nextInt();
                System.out.println("Enter low stock level of the medicine: ");
                int lowStockLevel = scanner.nextInt();
                MedicineStock stock = new MedicineStock(name, quantity, lowStockLevel);
                addMedicineStock(stock);
                break;
            case 2:
                // update inventory
                System.out.println("Enter name of the medicine to update: ");
                name = scanner.next();
                System.out.println("Enter new quantity of the medicine: ");
                quantity = scanner.nextInt();
                updateMedicineStock(name, quantity);
                break;
            case 3:

                // remove inventory
                System.out.println("Enter name of the medicine to remove: ");
                name = scanner.next();
                deleteMedicineStock(name);
                break;
            case 4:

                // view inventory
                viewInventory();
                break;
            case 5:

                // update low stock level alert line of medicine
                System.out.println("Enter name of the medicine to update: ");
                name = scanner.next();
                System.out.println("Enter new low stock level of the medicine: ");
                lowStockLevel = scanner.nextInt();
                updateLowStockLevel(name, lowStockLevel);
                break;
            case 6:

                // approve replenishment request
                System.out.println("Enter name of the medicine to approve: ");
                name = scanner.next();
                System.out.println("Enter quantity of the medicine in replenishment request: ");
                quantity = scanner.nextInt();
                approveReplenishmentRequest(name, quantity);
                break;
        }

    }

    public void viewAppointmentDetails()
    {
        // prints out appointment details of the appointment
        // prints patient ID, doctor ID, appointment status
        // date and time of appointment
        // appointment outcome record

        if (Hospital.appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        for (Appointment appointment : Hospital.appointments) {
            // print information of all appointments
            System.out.println("Appointment details: ");
        System.out.println("Patient ID: " + appointment.patientId);
        System.out.println("Doctor ID: " + appointment.doctorId);
        System.out.println("Appointment status: " + appointment.status);
        System.out.println("Date: " + appointment.date);
        System.out.println("Start time: " + appointment.timeSlot.start);
        System.out.println("End time: " + appointment.timeSlot.end);
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
                sc.nextLine();
                System.out.println("Enter staff name: ");
                String staffName = sc.nextLine();

                System.out.println("Enter staff age: ");
                int age = sc.nextInt();

                System.out.println("Enter staff ID: ");
                String staffID = sc.next();

                System.out.println("Enter staff role: ");
                String role = sc.next();

                System.out.println("Enter staff gender: ");
                try {
                    String genderString = sc.next();
                    System.out.println(genderString);
                    Gender gender = Gender.valueOf(genderString);
                    addStaffMember(staffName, age, staffID, gender, role);
                }

                catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender. Please enter Male or Female.");
                }   

                break;
            case 2:
                // update staff member
                sc.nextLine();
        System.out.println("Enter name of staff member to update: ");
        staffName = sc.nextLine();
                updateStaffMember(staffName);

                break;
            case 3:
                // remove staff member
                sc.nextLine();
                System.out.println("Enter name of staff member to remove: ");
                String name = sc.nextLine();
                removeStaffMember(name);            
                break;
            case 4:
                // display staff members, choose filter
                System.out.println("Enter filter: ");
                String filter = sc.next();
                displayStaff(filter);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

        return;

    }
    public void addStaffMember(String staffName, int age, String staffID, Gender gender, String role)
    {

        // add staff member to Hospital
        Hospital.addStaffMember(staffName, age, staffID, gender, role);

    }
    public void updateStaffMember(String staffName)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option: ");
        System.out.println("1. Update staff name");
        System.out.println("2. Update staff age");
        System.out.println("3. Update staff gender 🤡");
        int option = sc.nextInt();
        

        switch (option) {
            case 1:
                // update staff name
                sc.nextLine();
                System.out.println("Enter new staff name: ");
                String newStaffName = sc.nextLine();
                Hospital.updateStaffName(staffName, newStaffName);
                break;
            case 2:
                // update staff age
                System.out.println("Enter new staff age: ");
                age = sc.nextInt();
                Hospital.updateStaffAge(staffName, age);
                break;
            case 3:
                // update staff gender
                System.out.println("Enter new staff gender: ");
                String gender = sc.next();
                Hospital.updateStaffGender(staffName, Gender.valueOf(gender));
                break;
        }

        
    }
    public void removeStaffMember(String staffName)
    {
        Hospital.removeStaffMember(staffName);
    }
    public void displayStaff(String filter)
    {
        ArrayList<HospitalStaff> staffMembers = Hospital.getStaff();
        switch (filter) {
            
            case "role":
                // sort by role
                staffMembers.sort((s1, s2) -> s1.getRole().compareTo(s2.getRole()));
                break;

            case "age":
                // sort by age
                staffMembers.sort((s1, s2) -> s1.getAge() - s2.getAge());
                break;

            case "gender":
                // sort by gender
                staffMembers.sort((s1, s2) -> s1.getGender().compareTo(s2.getGender()));
                break;
        }

        for (HospitalStaff member : staffMembers) {
            // print out staff information
            System.out.println("Name of Staff: " + member.getName());
            System.out.println("Age: " + member.getAge());
            System.out.println("ID: " + member.getStaffID());
            System.out.println("Role: " + member.getRole());
            System.out.println("Gender: " + member.getGender());
        }
    }
    public void viewInventory()
    {

        // prints out inventory information


        for (MedicineStock stock : Hospital.inventory) {
            System.out.println("Name: " + stock.getName());
            System.out.println("Quantity: " + stock.getStock());
            System.out.println("Low stock level: " + stock.getLowStockLevel());
        }

    }
    public void approveReplenishmentRequest(String medicineName, int amount)
    {
        // search for for replenishmnet request

        for (int i = 0; i < Hospital.replenishmentRequests.size(); i++) {
            if (Hospital.replenishmentRequests.get(i).medicineName.equals(medicineName)
                    && Hospital.replenishmentRequests.get(i).amount == amount) {
                Hospital.removeReplenishmentRequest(Hospital.replenishmentRequests.get(i));
                // add amount to stock for medicine
                updateMedicineStock(medicineName, amount);
                return;
            }
        }
        System.out.println("No matching replenishment request found.");

    }
    public void addMedicineStock(MedicineStock stock)
    {

        Hospital.inventory.add(stock);


    }
    public void updateMedicineStock(String name, int count) 
    {
        // first we need to find the index of the medicine
        int lowStockLevel = 0;
        for (int i = 0; i < Hospital.inventory.size(); i++) {

            if (Hospital.inventory.get(i).getName().equals(name)) {
                count += Hospital.inventory.get(i).getStock();
                lowStockLevel = Hospital.inventory.get(i).getLowStockLevel();
                Hospital.inventory.remove(i);
                break; // Exit the loop after removing
            }
            addMedicineStock(new MedicineStock(name, count, lowStockLevel));
        }
    }
    public void deleteMedicineStock(String name)
    {
        for (int i = 0; i < Hospital.inventory.size(); i++) {
            if (Hospital.inventory.get(i).getName().equals(name)) {
                Hospital.inventory.remove(i);
                break; // Exit the loop after removing
            }
        }
    }
    
    public void updateLowStockLevel(String name, int newLevel) {
        // first we need to find the index of the medicine
        for (int i = 0; i < Hospital.inventory.size(); i++) {
            if (Hospital.inventory.get(i).getName().equals(name)) {
                Hospital.inventory.get(i).setLowStockLevel(newLevel);
                break; // Exit the loop after removing
            }
        }

    }
    

	
	


  
}
