package oop.AdministratorLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.Appointment;
import oop.Gender;
import oop.Hospital;
import oop.HospitalStaff;
import oop.MedicineStock;
import oop.Password;
import oop.StatusOfAppointment;

/**
 * Represents an Administrator in the hospital system, managing hospital staff, appointments, and inventory.
 * This class implements interfaces for staff management, appointment management, and inventory management.
 * @author: Ryan Ching, Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public class Administrator extends HospitalStaff implements StaffManagementInterface, AppointmentManagementInterface, InventoryManagementInterface {
    /** The age of the administrator. */
    private int age;

    /** The file path for the medicine list CSV file. */
    private final String  medicineFilePath = "../Medicine_List.csv";

    /** The file path for the staff list CSV file. */
    private final String staffFilePath = "../Staff_List.csv";

    /** The file path for the staff credentials CSV file. */
    private final String credentialsFilePath = "StaffCredentialsDatabase.csv";
    
    /**
     * Constructs an Administrator object with the specified details.
     * 
     * @param name The name of the administrator.
     * @param id The unique ID of the administrator.
     * @param age The age of the administrator.
     * @param gender The gender of the administrator.
     */    
    public Administrator(String name, String id, int age, Gender gender)
    {
        super(name, id, age, gender);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Manages the inventory, including adding, updating, removing, and viewing medicine stock.
     */
    public void manageInventory() {
        // choose whether to add, update, remove or delete inventory
        System.out.println("Choose an option: ");
        System.out.println("1. Add to inventory");
        System.out.println("2. Update medicine stock in inventory");
        System.out.println("3. Remove medicine from inventory");
        System.out.println("4. View Inventory");
        System.out.println("5. Update low stock level alert line of medicine");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String name;
        int quantity;
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:

                // add inventory
                try {
                System.out.print("Enter name of the medicine: ");
                name = scanner.nextLine();
                System.out.print("Enter quantity of the medicine: ");
                quantity = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter low stock level of the medicine: ");
                int lowStockLevel = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter price of the medicine: ");
                int price = Integer.parseInt(scanner.nextLine());
                MedicineStock stock = new MedicineStock(name, quantity, lowStockLevel, price);
                updateMedNewCSV(name, quantity, lowStockLevel, price);
                addMedicineStock(stock);
                break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                    
                }
            case 2:
                // update inventory
                try {
                System.out.print("Enter name of the medicine to update: ");
                //scanner.nextLine();
                name = scanner.nextLine();
                System.out.print("Enter new quantity of the medicine: ");
                quantity = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter price of the medicine: ");
                int newPrice = Integer.parseInt(scanner.nextLine());
                updateMedicineStock(name, quantity, newPrice);
                break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }
            case 3:

                // remove inventory
                try {
                System.out.print("Enter name of the medicine to remove: ");
                name = scanner.nextLine();
                deleteMedicineStock(name);
                break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }
            case 4:

                // view inventory
                viewInventory();
                break;
            case 5:

                // update low stock level alert line of medicine
                try {
                System.out.print("Enter name of the medicine to update: ");
                name = scanner.nextLine();
                System.out.print("Enter new low stock level of the medicine: ");
                int lowStockLevel = Integer.parseInt(scanner.nextLine());
                updateLowStockLevel(name, lowStockLevel);
                 break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }
               
           
        }

    }

    /**
     * Displays appointment details including patient ID, doctor ID, appointment status,
     * date, time, and outcome record if the appointment is confirmed.
     */
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
        // print appointment detail outcome for completed appointments
        if (appointment.status.equals(StatusOfAppointment.Completed)) {
            if (appointment.appointmentOutcome == null) {
                System.out.println("No appointment outcome.");
            }
            else {
             appointment.appointmentOutcome.printAppointmentOutcomeRecord();
           }
        }
    
        }
        
    }

    /**
     * Manages hospital staff, including adding, updating, removing, and displaying staff members.
     */
    public void manageHospitalStaff()
    {
        // choose whether to add, update, remove or delete staff member
        System.out.println("Choose an option: ");
        System.out.println("1. Add staff member");
        System.out.println("2. Update staff member");
        System.out.println("3. Remove staff member");
        System.out.println("4. Display staff members");
        System.out.print("Choice: ");
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();
         

        switch (opt) {
            case 1:
                // add staff member
                sc.nextLine();
                System.out.print("Enter staff name: ");
                String staffName = sc.nextLine();

                System.out.print("Enter staff age: ");
                int age = Integer.parseInt(sc.nextLine());

                System.out.print("Enter staff ID: ");
                String staffID = sc.nextLine();

                System.out.print("Enter staff role(Doctor/Pharmacist): ");
                String role = sc.nextLine();

                System.out.print("Enter staff gender (Male/Female): ");
                try {
                    String genderString = sc.nextLine();
                    Gender gender = Gender.valueOf(genderString);
                    addStaffMember(staffName, age, staffID, gender, role);
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender. Please enter Male or Female.");
                }   
                break;
            case 2:
                // update staff member
                try {
                sc.nextLine();
                System.out.print("Enter name of staff member to update: ");
                staffName = sc.nextLine();
                updateStaffMember(staffName);
                break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }

            case 3:
                // remove staff member
                try {
                sc.nextLine();
                System.out.print("Enter name of staff member to remove: ");
                String name = sc.nextLine();
                removeStaffMember(name);
                deleteStaffCSV(name);            
                break;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }
            case 4:
                // display staff members, choose filter
                try {
                    sc.nextLine();
                System.out.print("Enter filter: ");
                String filter = sc.nextLine();
                displayStaff(filter);
                break; 
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input, error: " + e.getMessage());
                    break;
                }
            default:
                System.out.println("Invalid option. Please try again.");
        }

        return;

    }

    /**
     * Adds a new staff member to the hospital.
     * 
     * @param staffName The name of the staff member.
     * @param age The age of the staff member.
     * @param staffID The unique ID of the staff member.
     * @param gender The gender of the staff member.
     * @param role The role of the staff member.
     */
    public void addStaffMember(String staffName, int age, String staffID, Gender gender, String role)
    {

        if (staffName.isEmpty() || age <= 0 || staffID.isEmpty() || role.isEmpty()) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        if (staffID.toLowerCase().charAt(0) != role.toLowerCase().charAt(0)) {
            System.out.println("Patient code must match role code. Please try again.");
            return;
        }

        // CAPS id
        staffID = staffID.toUpperCase();

        // add staff member to Hospital
        Hospital.addStaffMember(staffName, age, staffID, gender, role);
        // add them to csv file,
        updateStaffCSV(staffName, age, staffID, gender, role);
    }

    /**
     * Updates a staff member's details such as name, age, or gender.
     * 
     * @param staffName The name of the staff member to update.
     */
    public void updateStaffMember(String staffName)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option: ");
        System.out.println("1. Update staff age");
        System.out.println("2. Update staff gender");
        int option = sc.nextInt();
        

        switch (option) {
            case 1:
                // update staff age
                sc.nextLine();
                System.out.print("Enter new staff age: ");
                age = Integer.parseInt(sc.nextLine());
                Hospital.updateStaffAge(staffName, age);
                Hospital.staffs.forEach(staff -> {
                    if (staff.getName().equals(staffName)) {
                        deleteStaffCSV(staffName);
                        updateStaffCSV(staff.getName(), staff.age, staff.staffID, staff.gender, staff.getRole());
                    }
                });
                
                break;
            case 2:
                // update staff gender
                sc.nextLine();
                System.out.print("Enter new staff gender: ");
                String gender = sc.nextLine();
                Hospital.updateStaffGender(staffName, Gender.valueOf(gender));
                Hospital.staffs.forEach(staff -> {
                    if (staff.getName().equals(staffName)) {
                        deleteStaffCSV(staffName);
                        updateStaffCSV(staff.getName(), staff.age, staff.staffID, staff.gender, staff.getRole());
                    }
                });
                break;
        }
    }

    /**
     * Removes a staff member from the hospital.
     * 
     * @param staffName The name of the staff member to remove.
     */
    public void removeStaffMember(String staffName)
    {
        Hospital.removeStaffMember(staffName);
    }

    /**
     * Displays a list of hospital staff based on a filter such as role, age, or gender.
     * 
     * @param filter The filter criterion ("role", "age", or "gender").
     */
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

            default:
                System.out.println("Invalid filter. Please try again.");
                return;
        }

        for (HospitalStaff member : staffMembers) {
            // print out staff information
            System.out.println("Name of Staff: " + member.getName());
            System.out.println("Age: " + member.getAge());
            System.out.println("ID: " + member.getID());
            System.out.println("Role: " + member.getRole());
            System.out.println("Gender: " + member.getGender());
        }
    }

    /**
     * Views the current medicine inventory.
     */
    public void viewInventory()
    {

        // prints out inventory information

        if (Hospital.inventory.isEmpty()) {
            System.out.println("No medicine in inventory.");
        }

        System.out.println("Inventory: ");

        for (MedicineStock stock : Hospital.inventory) {
            System.out.println();
            System.out.println("Name: " + stock.getName());
            System.out.println("Quantity: " + stock.getStock());
            System.out.println("Low stock level: " + stock.getLowStockLevel());
        }
    }

    /**
     * Approves a replenishment request for medicine stock.
     * 
     * @param index The index of the replenishment request to approve.
     */
    public void approveReplenishmentRequest(int index)
    {
        // updates the replenishment request of hospital
        Hospital.replenishmentRequests.get(index).setApproved();
        int amount = Hospital.replenishmentRequests.get(index).amount;
        String name = Hospital.replenishmentRequests.get(index).medicineName;
        // updates file
        
        Hospital.inventory.forEach(med -> {
            if (med.getName().equals(name)) {
                updateMedStockCSV(name, amount + med.getStock(), med.getPrice());
            }
        });
        
        

    }

    /**
     * Adds a new medicine stock to the inventory.
     * 
     * @param stock The medicine stock to add.
     */
    public void addMedicineStock(MedicineStock stock)
    {
        Hospital.inventory.add(stock);
    }

    /**
     * Updates the stock and price of an existing medicine in the inventory.
     * 
     * @param name The name of the medicine to update.
     * @param count The new quantity of the medicine.
     * @param newPrice The new price of the medicine.
     */
    public void updateMedicineStock(String name, int count, int newPrice) 
    {
        // first we need to find the index of the medicine
        int lowStockLevel = 0;
        for (int i = 0; i < Hospital.inventory.size(); i++) {

            if (Hospital.inventory.get(i).getName().equals(name)) {
                lowStockLevel = Hospital.inventory.get(i).getLowStockLevel();
                updateMedStockCSV(name, count, newPrice);
                Hospital.inventory.remove(i);
                break; // Exit the loop after removing
            }
        }
        addMedicineStock(new MedicineStock(name, count, lowStockLevel, newPrice)); //change the price afterwards pls
    }

    /**
     * Deletes a medicine from the inventory.
     * 
     * @param name The name of the medicine to delete.
     */
    public void deleteMedicineStock(String name)
    {
        for (int i = 0; i < Hospital.inventory.size(); i++) {
            if (Hospital.inventory.get(i).getName().equals(name)) {
                Hospital.inventory.remove(i);
                updateMedDeleteStockCSV(name);
                break; // Exit the loop after removing
            }
        }
    }

    /**
     * Updates the low stock level of a specific medicine.
     * 
     * @param name The name of the medicine.
     * @param newLevel The new low stock level.
     */
    public void updateLowStockLevel(String name, int newLevel) {
        // first we need to find the index of the medicine
        for (int i = 0; i < Hospital.inventory.size(); i++) {
            if (Hospital.inventory.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                Hospital.inventory.get(i).setLowStockLevel(newLevel);
                updateMedLowStockCSV(name, newLevel);
                return;
            }
        }
        System.out.println("No medicine with name " + name + " found.");
    }

    /**
     * Updates the CSV file with the new stock and price for a specific medicine.
     * 
     * @param name The name of the medicine to update.
     * @param newStock The new stock level.
     * @param newPrice The new price of the medicine.
     */
    public void updateMedStockCSV(String name, int newStock, int newPrice)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(medicineFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].toLowerCase().equals(name.toLowerCase())) {
                    fields[1] = String.valueOf(newStock); // Update the stock
                    fields[2] = String.valueOf(newPrice);
                    found = true;
                }
                data.add(fields);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Medicine name not found in the CSV file.");
            return;
        }

        // Write the updated data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medicineFilePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Updates the CSV file with the new low stock level for a specific medicine.
     * 
     * @param name The name of the medicine.
     * @param newStock The new low stock level.
     */
    public void updateMedLowStockCSV(String name, int newStock)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(medicineFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].toLowerCase().equals(name.toLowerCase())) {
                    fields[2] = String.valueOf(newStock); // Update the Low stock level
                    found = true;
                }
                data.add(fields);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Medicine name not found in the CSV file.");
            return;
        }

        // Write the updated data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medicineFilePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Updates the CSV file to delete the entry for a specific medicine.
     * 
     * @param name The name of the medicine to delete.
     */
    public void updateMedDeleteStockCSV(String name)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;
        // Read the CSV file and store lines except the one to delete
        try (BufferedReader br = new BufferedReader(new FileReader(medicineFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                // Only add the line to `data` if the medicine name does not match
                if (!fields[0].toLowerCase().equals(name.toLowerCase())) {
                    data.add(fields);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Medicine name not found in the CSV file.");
            return;
        }

        // Write the updated data (without the deleted line) back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medicineFilePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Updates the CSV file with a new medicine entry.
     * 
     * @param name The name of the medicine.
     * @param iniStock The initial stock level of the medicine.
     * @param lowStockLevel The low stock level of the medicine.
     * @param price The price of the medicine.
     */
    public void updateMedNewCSV(String name, int iniStock, int lowStockLevel, int price)
    {
        boolean exists = false;
        // Check if the medicine already exists in the file
        try (BufferedReader br = new BufferedReader(new FileReader(medicineFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].toLowerCase().equals(name.toLowerCase())) {
                    exists = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (exists) {
            System.out.println("Medicine entry already exists in the CSV file.");
            return;
        }

        // Append the new entry to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medicineFilePath, true))) {
            String newEntry = name + "," + iniStock + "," + lowStockLevel + "," + price;
            bw.write(newEntry);
            bw.newLine();
            System.out.println("New medicine entry added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Updates the CSV file by removing staff
     * 
     * @param name The name of the medicine.
     * @param iniStock The initial stock level of the medicine.
     * @param lowStockLevel The low stock level of the medicine.
     * @param price The price of the medicine.
     */
    

    /**
     * Updates the CSV file with a new staff member's information.
     * 
     * @param staffName The name of the staff member.
     * @param age The age of the staff member.
     * @param staffID The ID of the staff member.
     * @param gender The gender of the staff member.
     * @param role The role of the staff member.
     */
    public void updateStaffCSV(String staffName, int age, String staffID, Gender gender, String role)
    {
        // Append the new entry to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(staffFilePath, true))) {
            String newEntry = staffID + "," + staffName + "," + role + "," + gender + "," + age;
            bw.write(newEntry);
            bw.newLine();
            System.out.println("New staff entry added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(credentialsFilePath, true))) {
            String newEntry = staffID + "," + Password.hashPassword("password");
            bw.write(newEntry);
            bw.newLine();
            System.out.println("Successfully added " + staffName + " into the system. Please take note the default password is 'password'");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        // write to credentials database
    }

    /**
     * Updates the CSV file to delete the entry for a specific staff
     * 
     * @param name The name of the medicine to delete.
     */
    public void deleteStaffCSV(String name)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;
        // Read the CSV file and store lines except the one to delete
        try (BufferedReader br = new BufferedReader(new FileReader(staffFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                // Only add the line to `data` if the staff name does not match
                if (!fields[1].toLowerCase().equals(name.toLowerCase())) {
                    data.add(fields);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Medicine name not found in the CSV file.");
            return;
        }

        // Write the updated data (without the deleted line) back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(staffFilePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Prints a description of the role for an administrator.
     */
    public void retrieveRoleDescription(){
        System.out.println("An administrator responsible for managing hospital operations.");
    }
}
