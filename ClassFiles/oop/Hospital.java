package oop;
import java.io.*;
import java.util.*;

import oop.AdministratorLogic.Inventory;
import oop.AdministratorLogic.ReplenishmentRequest;
public class Hospital {
    public static Inventory inventory = new Inventory();
    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    public static ArrayList<HospitalStaff> staffs = new ArrayList<HospitalStaff>();
    public static ArrayList<ReplenishmentRequest> replenishmentRequests = new ArrayList<ReplenishmentRequest>();

    private static final String patientFilePath = "Patient_List.csv";
    private static final String staffFilePath = "Staff_List.csv";
    private static final String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
    private static final String staffCredentialsDatabase = "StaffCredentialsDatabase.csv";
    // private static final String medFilePath = "Medicine_List.csv";

    public Hospital()
    {
        loadHospitalData();
        login();
    }

    private void loadHospitalData()
    {
        patients = ImportUsers.readPatientsFromCSV(patientFilePath);
        staffs = ImportUsers.readStaffFromCSV(staffFilePath);
    }

    public void login() {
        Scanner sc;
        boolean loginSuccess = false, userLogout = false, systemLogout = false;
        String id, password;
        sc = new Scanner(System.in);
        while (systemLogout != true)
        {
            loginSuccess = false;
            userLogout = false;
            while ((loginSuccess == false) && (userLogout == false))
            {
                System.out.print("--------------\nEnter -1 to quit\nEnter your id: ");
                id = sc.nextLine();
                if (id.equals("-1"))
                {
                    systemLogout = true;
                    break;
                }
                System.out.print("Enter your password: ");
                password = sc.nextLine();

                Patient matchedPatient = findPatientById(id);
                HospitalStaff matchedHospitalStaff = findStaffById(id);

                if ((matchedPatient != null) && (matchedHospitalStaff == null)) //Found patient
                {
                    try (BufferedReader reader = new BufferedReader(new FileReader(patientCredentialsDatabase)))
                    {
                        loginSuccess = processLoginPatient(reader, id, password, matchedPatient, sc);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if ((matchedPatient == null) && (matchedHospitalStaff != null)) //Found hospitalstaff
                {
                    try (BufferedReader reader = new BufferedReader(new FileReader(staffCredentialsDatabase)))
                    {
                        loginSuccess = processLoginHospitalStaff(reader, id, password, matchedHospitalStaff, sc);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("Invalid ID. Please try again!");
                    continue;
                }

                int menuChoice;
                if (loginSuccess == false)
                {
                    System.out.println("Invalid credentials or user ID not found.");
                }
                else if (matchedPatient != null)
                {
                    System.out.println("\nWelcome " + matchedPatient.getName() + "!");
                    loginSuccess = true;
                    while (userLogout == false)
                    {
                        Menu.printPatientMenu();
                        menuChoice = Integer.parseInt(sc.nextLine());
                        switch (menuChoice) {
                            case 1:
                                System.out.println("View Medical Record");
                                break;
                            case 2:
                                System.out.println("Update Personal Information");
                                break;
                            case 3:
                                matchedPatient.viewAvailableAppointmentSlots();
                                break;
                            case 4:
                                matchedPatient.scheduleAppointment();
                                break;
                            case 5:
                                matchedPatient.rescheduleAppointment();
                                break;
                            case 6:
                                matchedPatient.cancelAppointment();
                                break;
                            case 7:
                                matchedPatient.viewScheduledAppointmentStatus();
                                break;
                            case 8:
                                System.out.println("View Past Appointment Outcome Records");
                                break;
                            case 9:
                                System.out.println("Goodbye " + matchedPatient.getName() + "!\n");
                                userLogout = true;
                                loginSuccess = false;
                                break;
                            default:
                                System.out.println("Invalid input! Please try again!");
                                break;
                        }
                    }
                }
                else if (matchedHospitalStaff != null)
                {
                    System.out.println("\nWelcome " + matchedHospitalStaff.getName() + "!");
                    loginSuccess = true;
                    while (userLogout == false)
                    {
                        switch (matchedHospitalStaff.getRole())
                        {
                            case "Doctor":
                                int docChoice = 0;
                                Menu.printDoctorMenu();
                                menuChoice = Integer.parseInt(sc.nextLine());
                                Doctor doc = Hospital.getDoctorObjectByStaffID(matchedHospitalStaff.getStaffID());
                                switch (menuChoice)
                                {
                                    case 1:
                                        System.out.println("View Patient Medical Records");
                                        break;
                                    case 2:
                                        System.out.println("Update Patient Medical Records");
                                        break;
                                    case 3:
                                        System.out.println("View Personal Schedule");
                                        break;
                                    case 4:
                                        System.out.println("Set Availability for Appointments");
                                        break;
                                    case 5:
                                        int loop = 1;
                                        while (loop != -1)
                                        {
                                            System.out.println("Accept or Decline Appointment Request");
                                            System.out.println("1. Accept\n2. Decline\nEnter your choice: ");
                                            docChoice = Integer.parseInt(sc.nextLine());
                                            
                                            if (docChoice == 1)
                                            {
                                                doc.acceptAppointmentRequest();
                                                break;
                                            }
                                            else if (docChoice == 2)
                                            {
                                                doc.declineAppointmentRequest();
                                                break;
                                            }
                                            else if (docChoice == -1)
                                            {
                                                break;
                                            }
                                            else
                                            {
                                                System.out.println("Invalid input! Enter -1 to return to previous menu...");
                                            }
                                        }
                                        break;
                                    case 6:
                                        doc.viewUpcomingAppointments();
                                        break;
                                    case 7:
                                        doc.recordAppointmentOutcome();
                                        break;
                                    case 8:
                                        System.out.println("Goodbye " + doc.getName() + "!\n");
                                        userLogout = true;
                                        loginSuccess = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input! Please try again!");
                                        break;
                                }
                                break;
                            case "Pharmacist":
                                Menu.printPharmacistMenu();
                                System.out.print("Choice: ");
                                menuChoice = Integer.parseInt(sc.nextLine());
                                break;
                            case "Staff Member":
                                Menu.printAdminMenu();
                                System.out.print("Choice: ");
                                menuChoice = Integer.parseInt(sc.nextLine());
                                break;
                            default:
                                System.out.println("Invalid input! Please try again!");
                                break;
                        }
                    }
                }
            }
        }
        sc.close();
    }

    private Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getPatientID().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    private HospitalStaff findStaffById(String id) {
        for (HospitalStaff staff : staffs) {
            if (staff.getStaffID().equalsIgnoreCase(id)) {
                return staff;
            }
        }
        return null;
    }

    private boolean processLoginPatient(BufferedReader reader, String id, String password, Patient matchedPatient, Scanner sc)
            throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns[0].equalsIgnoreCase(id) && password.equals(columns[1])) {
                if ("password".equals(password)) {
                    updateDefaultPassword(id, sc, "Patient");
                }
                return true;
            }
        }
        return false;
    }

    private boolean processLoginHospitalStaff(BufferedReader reader, String id, String password, HospitalStaff matchedHospitalStaff, Scanner sc)
            throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns[0].equalsIgnoreCase(id) && password.equals(columns[1])) {
                if ("password".equals(password)) {
                    updateDefaultPassword(id, sc, "Staff");
                }
                return true;
            }
        }
        return false;
    }

    private void updateDefaultPassword(String id, Scanner sc, String role) {
        String newPassword1, newPassword2;
        System.out.println("You are required to change your password!");
        do {
            System.out.print("Enter new password: ");
            newPassword1 = sc.nextLine();
            System.out.print("Enter new password again: ");
            newPassword2 = sc.nextLine();
            if (!newPassword1.equals(newPassword2)) {
                System.out.println("Passwords do not match, please try again!");
            }
        } while (!newPassword1.equals(newPassword2));
        if (role == "Patient")
        {
            updatePasswordInCSV(patientCredentialsDatabase, id.toUpperCase(), newPassword1);
        }
        else
        {
            updatePasswordInCSV(staffCredentialsDatabase, id.toUpperCase(), newPassword1);
        }
        
        System.out.println("Your new password has been set.");
    }

    public static void updatePasswordInCSV(String filePath, String patientID, String newPassword) {
        List<String> lines = new ArrayList<>();
        String line;
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns[0].equals(patientID)) {
                    columns[1] = newPassword;
                    line = String.join(",", columns);
                    isUpdated = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
                System.out.println("Password updated successfully for ID: " + patientID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ID not found: " + patientID);
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
        for (HospitalStaff member : staffs) {
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
            if (member instanceof Doctor && member.getStaffID().equals(staffID))
            {

                return member.getName();

            }
        }
        return null;
    }

    public static Doctor getDoctorObjectByStaffID(String staffID) {
        for (HospitalStaff member : staffs) {
            if (member instanceof Doctor && member.getStaffID().equals(staffID)) {
                return (Doctor) member; // Cast to Doctor
            }
        }
        return null; // Return null if no matching doctor is found
    }
    
    
    public static void addStaffMember(String staffName, int age, String staffID, Gender gender, String role) {
        // check whether role is valid
        role = role.toLowerCase();
        if (role != "doctor" || role != "pharmacist") {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }

        // instantiate staff member
        HospitalStaff staffMember;
        if (role == "doctor") {
            staffMember = new Doctor(staffName, staffID, age, gender);

        } else if (role == "pharmacist") {
            staffMember = new Pharmacist(staffName, staffID, age, gender);
        } else {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }
        staffs.add(staffMember);
    }
    
    public static void removeStaffMember(String staffName) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getName().equals(staffName)) {
                staffs.remove(i);
                break;
            }
        }
    }

    public static String getPatientNameFromPatientID(String patientID)
    {
        for (Patient patient : patients)
        {
            if (patient.getPatientID().equals(patientID))
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
            if (patient.getPatientID().equals(patientID))
            {

                return patient;

            }
        }
        return null;
    }
}