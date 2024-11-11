package oop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import oop.AdministratorLogic.Administrator;
import oop.AdministratorLogic.ReplenishmentRequest;
/**
 * The Hospital class manages the operations and entities in a hospital system.
 * It handles operations related to patients, staff, medicine inventory, replenishment requests,
 * appointments, and available time slots for doctors.
 * 
 * This class provides functionality for managing hospital data, including adding and removing
 * staff members, updating staff information, handling patient registration, login, and managing
 * medicine stock, replenishment requests, and appointments.
 * 
 * @author Ryan Ching, Tan Zhe Kai, Kuang Yu Heng, Kuang Yu Xuan
 * @version 1.0
 * @since 2024-11-09
 */
public class Hospital {
    /**
     * List of all medicine stocks in the hospital.
     */
    public static ArrayList<MedicineStock> inventory = new ArrayList<MedicineStock>();
    /**
     * List of all appointments scheduled in the hospital.
     */
    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    /**
     * List of all patients in the hospital.
     */
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    /**
     * List of all hospital staff members.
     */
    public static ArrayList<HospitalStaff> staffs = new ArrayList<HospitalStaff>();
    /**
     * List of all replenishment requests for medicines.
     */
    public static ArrayList<ReplenishmentRequest> replenishmentRequests = new ArrayList<ReplenishmentRequest>();

    /**
     * Path to the file containing patient data.
     */
    private static final String patientFilePath = "Patient_List.csv";
    /**
     * Path to the file containing staff data.
     */
    private static final String staffFilePath = "Staff_List.csv";
    /**
     * Path to the file containing patient login credentials.
     */
    private static final String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
    /**
     * Path to the file containing staff login credentials.
     */
    private static final String staffCredentialsDatabase = "StaffCredentialsDatabase.csv";
    /**
     * Path to the file containing medicine data.
     */
    private static final String medFilePath = "Medicine_List.csv";

    /**
     * Initializes the hospital system by loading data from files.
     */
    public static final void initialise() {
        loadHospitalData();
    }

    /**
     * Updates the name of a staff member.
     * @param staffName The current name of the staff member.
     * @param newStaffName The new name to update to.
     */
    public static void updateStaffName(String staffName, String newStaffName) {
        for (HospitalStaff staff : staffs) {
            if (staff.getName().equals(staffName)) {
                staff.setName(newStaffName);
                System.out.println("Staff name updated successfully.");
                return;
            }
        }
        System.out.println("No staff with name " + staffName + " found.");
    }
    /**
     * Updates the age of a staff member.
     * @param staffName The name of the staff member.
     * @param newStaffAge The new age to update to.
     */
    public static void updateStaffAge(String staffName, int newStaffAge) {

        for (HospitalStaff staff : staffs) { 
            if (staff.getName().equals(staffName)) {
                staff.setAge(newStaffAge);
                System.out.println("Staff age updated successfully.");
                return;
            }
        }
        System.out.println("No staff with name " + staffName + " found.");
    }
    /**
     * Updates the gender of a staff member.
     * @param staffName The name of the staff member.
     * @param newStaffGender The new gender to update to.
     */
    public static void updateStaffGender(String staffName, Gender newStaffGender) {

        for (HospitalStaff staff : staffs) {
            if (staff.getName().equals(staffName)) {
                staff.setGender(newStaffGender);
                System.out.println("Staff gender updated successfully.");
                return;
            }
        }
        System.out.println("No staff with name " + staffName + " found.");
    }
    /**
     * Loads data for patients, staff, inventory, and available time slots from CSV files.
     */
    private static void loadHospitalData()
    {
        patients = ImportUsers.readPatientsFromCSV(patientFilePath);
        staffs = ImportUsers.readStaffFromCSV(staffFilePath);
        inventory = ImportMed.readMedicineFromCSV(medFilePath);
    }
    /**
     * Finds a patient by their ID.
     * @param id The patient ID.
     * @return The patient if found, otherwise null.
     */
    public static Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getID().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }
    /**
     * Finds a hospital staff member by their ID.
     * @param id The staff ID.
     * @return The staff member if found, otherwise null.
     */
    public static HospitalStaff findStaffById(String id) {
        for (HospitalStaff staff : staffs) {
            if (staff.getID().equalsIgnoreCase(id)) {
                return staff;
            }
        }
        return null;
    }
    /**
     * Processes login for a patient, verifying their ID and password.
     * @param reader BufferedReader to read credentials.
     * @param id The patient ID.
     * @param password The password entered by the patient.
     * @param matchedPatient The patient object to match the ID with.
     * @param sc Scanner object for user input.
     * @return true if login is successful, false otherwise.
     * @throws IOException If an error occurs while reading the file.
     */
    public static boolean processLoginPatient(BufferedReader reader, String id, String password, Patient matchedPatient, Scanner sc)
            throws IOException{
        String line, hashedPassword = Password.hashPassword(password), defaultHashedPassword = Password.hashPassword("password");
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns[0].equalsIgnoreCase(id) && hashedPassword.equals(columns[1])) {
                if (defaultHashedPassword.equals(hashedPassword)) {
                    Password.updateDefaultPassword(id, sc, "Patient", patientCredentialsDatabase);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Processes login for a hospital staff member, verifying their ID and password.
     * @param reader BufferedReader to read credentials.
     * @param id The staff ID.
     * @param password The password entered by the staff member.
     * @param matchedHospitalStaff The staff member object to match the ID with.
     * @param sc Scanner object for user input.
     * @return true if login is successful, false otherwise.
     * @throws IOException If an error occurs while reading the file.
     */
    public static boolean processLoginHospitalStaff(BufferedReader reader, String id, String password, HospitalStaff matchedHospitalStaff, Scanner sc)
            throws IOException {
                String line, hashedPassword = Password.hashPassword(password), defaultHashedPassword = Password.hashPassword("password");
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns[0].equalsIgnoreCase(id) && hashedPassword.equals(columns[1])) {
                if (defaultHashedPassword.equals(hashedPassword)) {
                    Password.updateDefaultPassword(id, sc, "Staff", staffCredentialsDatabase);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Displays the current inventory of medicines and their stock levels.
     */
    public static void viewMedicineStock()
    {
        System.out.println("Medicine Inventory Stock");
        System.out.println("------------------------");
        for (MedicineStock medStock : inventory)
        {
            System.out.println(medStock.getName() + "'s stock: " + medStock.getStock());
        }
    }
    /**
     * Adds a new replenishment request to the list.
     * @param request The replenishment request to be added.
     */
    public static void addReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.add(request);
    }
    /**
     * Removes a replenishment request from the list.
     * @param request The replenishment request to be removed.
     */
    public static void removeReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.remove(request);
    }

    /**
     * Retrieves the list of hospital staff members.
     * @return List of hospital staff members.
     */
    public static ArrayList<HospitalStaff> getStaff() {
        return staffs;
    }

    /**
     * Counts the number of doctors in the hospital.
     * @return The number of doctors.
     */
    public static int numberOfDoctors()
    {
        int count = 0;
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Displays the names of all doctors in the hospital.
     */
    public static void namesOfDoctors()
    {
        int i = 1;
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor)
            {
                System.out.println(i + ") Dr. " + member.getName());
                i++;
            }
            
        }
    }

    /**
     * Retrieves a doctor by their index in the list of staff.
     * @param index The index of the doctor.
     * @return The doctor object.
     */
    public static Doctor getDoctorByIndex(int index) {
        int doctorCount = 0; // To keep track of the current doctor count
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor) {
                if (doctorCount == index) {
                    return (Doctor) member; // Cast to Doctor and return when the index matches
                }
                doctorCount++; // Increment the doctor count
            }
        }
        // If no matching doctor is found, handle the invalid index
        System.out.println("Invalid index. Please choose a valid doctor.");
        return null;
    }

    /**
     * Retrieves the name of a doctor by their staff ID.
     * @param staffID The staff ID of the doctor.
     * @return The name of the doctor.
     */
    public static String getDoctorNameByStaffID(String staffID)
    {
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor && member.getID().equals(staffID))
            {
                return member.getName();
            }
        }
        return null;
    }

    /**
     * Retrieves a doctor object by their staff ID.
     * @param staffID The staff ID of the doctor.
     * @return The doctor object.
     */
    public static Doctor getDoctorObjectByStaffID(String staffID) {
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor && member.getID().equals(staffID))
            {
                return (Doctor) member; // Cast to Doctor
            }
        }
        return null; // Return null if no matching doctor is found
    }
    
    /**
     * Retrieves a pharmacist object by their staff ID.
     * @param staffID The staff ID of the pharmacist.
     * @return The pharmacist object.
     */
    public static Pharmacist getPharmacistObjectByStaffID(String staffID)
    {
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Pharmacist && member.getID().equals(staffID))
            {
                return (Pharmacist) member; // Cast to Pharmacist
            }
        }
        return null; // Return null if no matching doctor is found
    }

    /**
     * Retrieves an administrator object by their staff ID.
     * @param staffID The staff ID of the administrator.
     * @return The administrator object.
     */
    public static Administrator getAdministratorObjectByStaffID(String staffID)
    {
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Administrator && member.getID().equals(staffID))
            {
                return (Administrator) member; // Cast to Administrator
            }
        }
        return null; // Return null if no matching doctor is found
    }
    
    /**
     * Adds a new staff member to the hospital system.
     * @param staffName The name of the new staff member.
     * @param age The age of the new staff member.
     * @param staffID The staff ID for the new staff member.
     * @param gender The gender of the new staff member.
     * @param role The role of the new staff member (either doctor or pharmacist).
     */
    public static void addStaffMember(String staffName, int age, String staffID, Gender gender, String role)
    {
        // check whether role is valid
        role = role.toLowerCase();

        if (!role.equals("doctor") && !role.equals("pharmacist"))
        {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }

        // instantiate staff member
        HospitalStaff staffMember;
        if (role.equals("doctor"))
        {
            staffMember = new Doctor(staffName, staffID, age, gender);

        }
        else if (role.equals("pharmacist"))
        {
            staffMember = new Pharmacist(staffName, staffID, age, gender);
        }
        else
        {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }
    }
    
    /**
     * Removes a staff member from the hospital system.
     * @param staffName The name of the staff member to be removed.
     */
    public static void removeStaffMember(String staffName)
    {
        for (int i = 0; i < staffs.size(); i++)
        {
            if (staffs.get(i).getName().equals(staffName))
            {
                staffs.remove(i);
                System.out.println("Staff member removed: " + staffName);   
                return;
            }
        }
        System.out.println("Staff member not found: " + staffName);
    }

    /**
     * Retrieves the name of a patient using their patient ID.
     * @param patientID The ID of the patient whose name is to be retrieved.
     * @return The name of the patient if found, otherwise returns null.
     */
    public static String getPatientNameFromPatientID(String patientID)
    {
        for (Patient patient : patients)
        {
            if (patient.getID().equals(patientID))
            {
                return patient.getName();
            }
        }
        return null;
    }

    /**
     * Retrieves the Patient object corresponding to a given patient ID.
     * @param patientID The ID of the patient to retrieve.
     * @return The Patient object if found, otherwise returns null.
     */
    public static Patient getPatientFromPatientID(String patientID)
    {
        for (Patient patient : patients)
        {
            if (patient.getID().equals(patientID))
            {
                return patient;
            }
        }
        return null;
    }

    /**
     * Checks the inventory of medicines in the hospital and alerts if any medicine is low in stock.
     * Compares the current stock of each medicine with its low stock threshold.
     * Will be used as a system alert to the pharmacist/administrator upon their login.
     */
    public static void checkStockAlert()
    {
        for (MedicineStock med : Hospital.inventory)
        {
            if (med.getStock() <= med.getLowStockLevel())
            {
                System.out.println("System alert: " + med.getName() + " is currently low on stock with " + med.getStock() + " units");
            }
        }
    }

    /**
     * Checks for any pending replenishment by the pharmacist.
     * If there are, it will be printed out.
     */
    public static void checkPendingReplenishment()
    {
        for (ReplenishmentRequest request : replenishmentRequests)
        {
            if (request.status == "Pending")
            {
                System.out.println("System alert: Pending replenishment request for " + request.medicineName);
            }
        }
    }
    
    /**
     * Registers a new patient by prompting for their personal and medical details, 
     * then adds the patient to the system and updates the CSV patient list records.
     */
    public static void registerNewPatient()
    {
        Scanner sc = new Scanner(System.in);
        int blood;
        String name, email, patientID, genderString, dateString;
        LocalDate dateOfBirth = null;
        Gender gender;
        BloodType bloodType;

        System.out.print("Enter your name: ");
        name = sc.nextLine();

        while(true)
        {
            System.out.print("Enter your date of birth, separated by hyphen (YYYY-MM-DD): ");
            dateString = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try
            {
                dateOfBirth = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            }
            if (dateOfBirth != null)
            {
                break;
            }
        }
        while (true)
        {
            System.out.print("Select your gender (M/F): ");
            genderString = sc.nextLine();
            if (genderString.equalsIgnoreCase("M"))
            {
                gender = Gender.Male;
                break;
            }
            else if (genderString.equalsIgnoreCase("F"))
            {
                gender = Gender.Female;
                break;
            }
            else
            {
                System.out.println("Invalid input! Please try again!");
            }
        }
        while (true)
        {
            System.out.println("Select from the following bloodtypes:");
            System.out.println("1. A+");
            System.out.println("2. A-");
            System.out.println("3. B+");
            System.out.println("4. B-");
            System.out.println("5. O+");
            System.out.println("6. O-");
            System.out.println("7. AB+");
            System.out.println("8. AB-");
            System.out.print("Choice: ");
            blood = Integer.parseInt(sc.nextLine());
            if (blood == 1)
            {
                bloodType = BloodType.APlus;
                break;
            }
            else if (blood == 2)
            {
                bloodType = BloodType.AMinus;
                break;
            }
            else if (blood == 3)
            {
                bloodType = BloodType.BPlus;
                break;
            }
            else if (blood == 4)
            {
                bloodType = BloodType.BMinus;
                break;
            }
            else if (blood == 5)
            {
                bloodType = BloodType.OPlus;
                break;
            }
            else if (blood == 6)
            {
                bloodType = BloodType.OMinus;
                break;
            }
            else if (blood == 7)
            {
                bloodType = BloodType.ABPlus;
                break;
            }
            else if (blood == 8)
            {
                bloodType = BloodType.ABMinus;
                break;
            }
            else
            {
                System.out.println("Invalid input! Please try again!");
            }
        }

        System.out.print("Enter your email address: ");
        email = sc.nextLine();

        patientID = getLatestPatientID();

        Patient patient = new Patient(name, patientID, dateOfBirth, gender, bloodType, email);
        updatePatientInCSV(patientID, name, dateOfBirth, gender, bloodType, email);
        patients.add(patient);
        insertNewPatientCredentials(patientID);
        System.out.println("Please note Hospital ID is " + patientID + ", your default password to login is 'password', you will be prompted to change upon first login!");
    }

    /**
     * Retrieves the latest patient ID by scanning the CSV file and finding the latest existing ID.
     * @return The next available patient ID.
     */
    public static String getLatestPatientID()
    {
        String latestPatientID = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(patientFilePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String patientID = fields[0].trim();
                
                // Compare and update latestPatientID if the current one is greater
                if (latestPatientID.isEmpty() || patientID.compareTo(latestPatientID) > 0) {
                    latestPatientID = patientID;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extract the numeric part and increment it by 1
        if (!latestPatientID.isEmpty()) {
            int numericID = Integer.parseInt(latestPatientID.substring(1)) + 1;
            return "P" + numericID;
        }

        // Return a unknown ID if no patients exist
        return "Unknown";
    }

    /**
     * Updates the patient record in the CSV file with the provided details.
     * @param patientID The patient's ID.
     * @param name The patient's name.
     * @param dateOfBirth The patient's date of birth.
     * @param gender The patient's gender.
     * @param bloodType The patient's blood type.
     * @param email The patient's email address.
     */
    public static void updatePatientInCSV(String patientID, String name, LocalDate dateOfBirth, Gender gender, BloodType bloodType, String email)
    {
        String dateOfBirthStr = dateOfBirth.toString();
        String genderStr = gender.name();
        String bloodTypeStr = "", phoneNumber = "null";

        if (bloodType.name().equalsIgnoreCase("APlus"))
        {
            bloodTypeStr = "A+";
        }
        else if (bloodType.name().equalsIgnoreCase("AMinus"))
        {
            bloodTypeStr = "A-";
        }
        else if (bloodType.name().equalsIgnoreCase("BPlus"))
        {
            bloodTypeStr = "B+";
        }
        else if (bloodType.name().equalsIgnoreCase("BMinus"))
        {
            bloodTypeStr = "B-";
        }
        else if (bloodType.name().equalsIgnoreCase("OPlus"))
        {
            bloodTypeStr = "O+";
        }
        else if (bloodType.name().equalsIgnoreCase("OMinus"))
        {
            bloodTypeStr = "O-";
        }
        else if (bloodType.name().equalsIgnoreCase("ABPlus"))
        {
            bloodTypeStr = "AB+";
        }
        else if (bloodType.name().equalsIgnoreCase("ABMinus"))
        {
            bloodTypeStr = "AB-";
        }

        String newPatientRecord = String.join(",",patientID, name, dateOfBirthStr, genderStr, bloodTypeStr, email, phoneNumber);

        try (FileWriter writer = new FileWriter(patientFilePath, true)) { // Open file in append mode
            writer.write(newPatientRecord); // Write new patient record to a new line
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialise();
    }

    /**
     * Inserts the default credentials for a newly registered patient into the csv credentials file
     * @param id The patient ID.
     */
    public static void insertNewPatientCredentials(String id)
    {
        String hashedPassword = Password.hashPassword("password");
        // Create the new entry line
        String newEntry = id + "," + hashedPassword;

        try (FileWriter writer = new FileWriter(patientCredentialsDatabase, true)) { // Open file in append mode
            writer.write(newEntry); // Append the new patient ID and hashed password on a new line
            System.out.println("New patient credentials added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}