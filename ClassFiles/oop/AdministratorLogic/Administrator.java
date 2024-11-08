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

public class Administrator extends HospitalStaff implements StaffManagementInterface, AppointmentManagementInterface, InventoryManagementInterface {
    private int age;
    private String filePath = "Medicine_List.csv";
    
    /** 
     * @param name
     * @param id
     * @param gender
     * @param age
     * @return 
     */
    
    public Administrator(String name, String id, int age, Gender gender)
    {
        super(name, id, age, gender);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getID()
    {
        return super.getID();
    }

    public void manageInventory() {
        // choose whether to add, update, remove or delete inventory
        System.out.println("Choose an option: ");
        System.out.println("1. Add to inventory");
        System.out.println("2. Update medicine stock in inventory");
        System.out.println("3. Remove medicine from inventory");
        System.out.println("4. View Inventory");
        System.out.println("5. Update low stock level alert line of medicine");
        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String name;
        int quantity;
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:

                // add inventory
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
            case 2:
                // update inventory
                System.out.print("Enter name of the medicine to update: ");
                //scanner.nextLine();
                name = scanner.nextLine();
                System.out.print("Enter new quantity of the medicine: ");
                quantity = Integer.parseInt(scanner.nextLine());
                updateMedicineStock(name, quantity);
                break;
            case 3:

                // remove inventory
                System.out.print("Enter name of the medicine to remove: ");
                name = scanner.nextLine();
                deleteMedicineStock(name);
                break;
            case 4:

                // view inventory
                viewInventory();
                break;
            case 5:

                // update low stock level alert line of medicine
                System.out.print("Enter name of the medicine to update: ");
                name = scanner.nextLine();
                System.out.print("Enter new low stock level of the medicine: ");
                lowStockLevel = Integer.parseInt(scanner.nextLine());
                updateLowStockLevel(name, lowStockLevel);
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
                System.out.print("Enter staff name: ");
                String staffName = sc.nextLine();

                System.out.print("Enter staff age: ");
                int age = Integer.parseInt(sc.nextLine());

                System.out.print("Enter staff ID: ");
                String staffID = sc.nextLine();

                System.out.print("Enter staff role: ");
                String role = sc.nextLine();

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
                System.out.print("Enter name of staff member to update: ");
                staffName = sc.nextLine();
                updateStaffMember(staffName);

                break;
            case 3:
                // remove staff member
                sc.nextLine();
                System.out.print("Enter name of staff member to remove: ");
                String name = sc.nextLine();
                removeStaffMember(name);            
                break;
            case 4:
                // display staff members, choose filter
                System.out.print("Enter filter: ");
                String filter = sc.nextLine();
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
                System.out.print("Enter new staff name: ");
                String newStaffName = sc.nextLine();
                Hospital.updateStaffName(staffName, newStaffName);
                break;
            case 2:
                // update staff age
                System.out.print("Enter new staff age: ");
                age = Integer.parseInt(sc.nextLine());
                Hospital.updateStaffAge(staffName, age);
                break;
            case 3:
                // update staff gender
                System.out.print("Enter new staff gender: ");
                String gender = sc.nextLine();
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
            System.out.println("ID: " + member.getID());
            System.out.println("Role: " + member.getRole());
            System.out.println("Gender: " + member.getGender());
        }
    }
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
    public void approveReplenishmentRequest(int index)
    {
        // updates the replenishment request of hospital
        Hospital.replenishmentRequests.get(index).setApproved();

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
                lowStockLevel = Hospital.inventory.get(i).getLowStockLevel();
                updateMedStockCSV(name, count);
                Hospital.inventory.remove(i);
                break; // Exit the loop after removing
            }
        }
        addMedicineStock(new MedicineStock(name, count, lowStockLevel, 0)); //change the price afterwards pls
    }
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
    public void updateLowStockLevel(String name, int newLevel) {
        // first we need to find the index of the medicine
        for (int i = 0; i < Hospital.inventory.size(); i++) {
            if (Hospital.inventory.get(i).getName().equals(name)) {
                Hospital.inventory.get(i).setLowStockLevel(newLevel);
                updateMedLowStockCSV(name, newLevel);
                break; // Exit the loop after removing
            }
        }

    }
    public void updateMedStockCSV(String name, int newStock)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
                    fields[1] = String.valueOf(newStock); // Update the stock
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public void updateMedLowStockCSV(String name, int newStock)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public void updateMedDeleteStockCSV(String name)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;
        // Read the CSV file and store lines except the one to delete
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                // Only add the line to `data` if the medicine name does not match
                if (!fields[0].equals(name)) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public void updateMedNewCSV(String name, int iniStock, int lowStockLevel, int price)
    {
        boolean exists = false;
        // Check if the medicine already exists in the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String newEntry = name + "," + iniStock + "," + lowStockLevel + "," + price;
            bw.write(newEntry);
            bw.newLine();
            System.out.println("New medicine entry added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
