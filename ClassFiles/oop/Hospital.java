package oop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.AdministratorLogic.Administrator;
import oop.AdministratorLogic.ReplenishmentRequest;
public class Hospital {
    public static ArrayList<MedicineStock> inventory = new ArrayList<MedicineStock>();
    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    public static ArrayList<HospitalStaff> staffs = new ArrayList<HospitalStaff>();
    public static ArrayList<ReplenishmentRequest> replenishmentRequests = new ArrayList<ReplenishmentRequest>();

    private static final String patientFilePath = "../Patient_List.csv";
    private static final String staffFilePath = "../Staff_List.csv";
    private static final String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
    private static final String staffCredentialsDatabase = "StaffCredentialsDatabase.csv";
    private static final String medFilePath = "../Medicine_List.csv";

    public static final void initialise() {
        loadHospitalData();
    }

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

    public static void updateStaffAge(String staffName, int newStaffAge) {

        for (HospitalStaff staff : staffs) {
            System.out.println(staff.getName());   
            if (staff.getName().equals(staffName)) {
                staff.setAge(newStaffAge);
                System.out.println("Staff age updated successfully.");
                return;
            }
        }
        System.out.println("No staff with name " + staffName + " found.");
    }
    
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

    private static void loadHospitalData()
    {
        patients = ImportUsers.readPatientsFromCSV(patientFilePath);
        staffs = ImportUsers.readStaffFromCSV(staffFilePath);
        inventory = ImportUsers.readMedicineFromCSV(medFilePath);
    }

    public static Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getID().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    public static HospitalStaff findStaffById(String id) {
        for (HospitalStaff staff : staffs) {
            if (staff.getID().equalsIgnoreCase(id)) {
                return staff;
            }
        }
        return null;
    }

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

    public static void viewMedicineStock()
    {
        System.out.println("Medicine Inventory Stock");
        System.out.println("------------------------");
        for (MedicineStock medStock : inventory)
        {
            System.out.println(medStock.getName() + "'s stock: " + medStock.getStock());
        }
    }

    public static void addReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.add(request);
    }

    public static void removeReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.remove(request);
    }
    public static ArrayList<HospitalStaff> getStaff() {
        return staffs;
    }

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

    public static void namesOfDoctors()
    {
        int i = 1;
        for (HospitalStaff member : staffs)
        {
            if (member instanceof Doctor)
            {
                System.out.println(i + ") Dr. " + member.getName());
            }
            i++;
        }
    }

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
}