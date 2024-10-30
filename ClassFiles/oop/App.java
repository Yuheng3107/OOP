package oop;
import java.io.*;
import java.util.*;

import oop.AdministratorLogic.Administrator;

import java.time.*;

public class App {
    public static void main(String[] args)
    {
        TimeSlot[] timeSlots = new TimeSlot[] {
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), LocalTime.of(11, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(11, 0), LocalTime.of(12, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 21), LocalTime.of(9, 0), LocalTime.of(10, 0))
        };

        
        Doctor doctor1 = new Doctor("Mike Adams", "D0001", 40, Gender.Male);
        Doctor doctor2 = new Doctor("Mikey Mike", "D0002", 22, Gender.Female);
        String[] s = new String[1]; //placeholder to create medicalHistory
        s[0] = "a";
        Patient patient1 = new Patient("Mary Lamb", "P0001", LocalDate.of(2000, 1, 1), Gender.Female, BloodType.ABMinus, "hi@gmail.com");
        // //Test Case 3
        // patient1.viewAvailableAppointmentSlots();
        // //Test Case 4
        // patient1.scheduleAppointment();
        // patient1.viewScheduledAppointmentStatus();
        // doctor1.viewPendingAppointments();
        // patient1.viewAvailableAppointmentSlots();
        // doctor1.acceptAppointmentRequest();
        // patient1.viewScheduledAppointmentStatus();
        // //Test Case 5
        // patient1.rescheduleAppointment();
        // patient1.viewAvailableAppointmentSlots();
        // //Test Case 6
        // patient1.scheduleAppointment();
        // patient1.viewAvailableAppointmentSlots();
        // patient1.cancelAppointment();
        // patient1.viewAvailableAppointmentSlots();
        //Test Case 7
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // patient1.viewScheduledAppointments();
        // Test Case 8
        // patient1.viewAppointmentOutcomeRecords();
        

        //Test Case 14
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // doctor1.viewPendingAppointments();
        // doctor1.acceptAppointmentRequest();
        // doctor1.acceptAppointmentRequest();
        // doctor1.viewUpcomingAppointments();
        //Test Case 15
        //patient1.scheduleAppointment();
        //doctor1.acceptAppointmentRequest();
        //doctor1.recordAppointmentOutcome();
        //patient1.viewAppointmentOutcomeRecords();

        /*
        // testing for medicalrecord for patient and doc
        Hospital hospital = new Hospital();
        String[] s = new String[1];
        s[0] = "test";
        MedicalHistory medicH = new MedicalHistory(s,s,s,s);

        Patient patient1 = new Patient("kai", "P01", LocalDate.of(2000, 1, 1), Gender.Female, BloodType.ABMinus, medicH, "kai.com", hospital);
        //patient1.getMedicalRecord();

        Doctor doctor1 = new Doctor("John", "D01", 100, Gender.Male);
        doctor1.viewMedicalRecord(patient1);*/
        login();
    }
     

    public static void login()
    {
        Scanner sc = new Scanner(System.in);

        boolean loginSuccess = false;
        String id = "", password = "", line = "";
        String newPassword1 = "", newPassword2 = "";

        String patientFilePath = "Patient_List.csv";
        String medFilePath = "Medicine_List.csv";
        String doctorFilePath = "Doctors.csv";
        String adminFilePath = "Administrators.csv";
        String pharmacistFilePath = "Pharmacists.csv";

        String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
        String doctorCredentialsDatabase = "DoctorsCredentialsDatabase.csv";
        String pharmacistCredentialsDatabase = "PharmacistsCredentialsDatabase.csv";
        String administratorCredentialsDatabase = "AdministratorsCredentialsDatabase.csv";
        ImportUsers.splitStaffList();

        List<Patient> patients = ImportUsers.readPatientsFromCSV(patientFilePath);
        List<Doctor> doctors = ImportUsers.readDoctorFromCSV(doctorFilePath);
        List<MedicineStock> medStocks = ImportUsers.readMedicineFromCSV(medFilePath);
        List<Pharmacist> pharmacists = ImportUsers.readPharmacistFromCSV(pharmacistFilePath);
        List<Administrator> administrators = ImportUsers.readAdministratorFromCSV(adminFilePath);
        
        /*for (Patient patient : patients) {
            System.out.println(patient.getGender());
            System.out.println(patient.getName());
        }
        for (MedicineStock medStock : medStocks) {
            System.out.println(medStock.getName());
            System.out.println(medStock.getStock());
        }*/

        while (loginSuccess != true)
        {
            System.out.print("Enter your id: ");
            id = sc.nextLine();
            System.out.print("Enter your password: ");
            password = sc.nextLine();
            //Run through the list of patients and see if we can find any patient with the same ID as the one entered by user
            Patient matchedRecord = null;
            for (Patient patient : patients)
            {
                if (patient.getPatientID().equalsIgnoreCase(id))
                {
                    matchedRecord = patient;
                    break;
                }
            }
            //Check if such a record exist in our database
            if (matchedRecord == null)
            {
                System.out.println("Invalid ID. Please try again!");
            }
            //This part means there exist a record for the entered ID, now we check the password that is entered by the user
            try (BufferedReader reader = new BufferedReader(new FileReader(patientCredentialsDatabase)))
            {
                while ((line = reader.readLine()) != null)
                {
                    String[] columns = line.split(",");
                    if (columns[0].equalsIgnoreCase(id))
                    {
                        String storedPassword = columns[1]; //The password that we read from our database file
                        if (password.equals(storedPassword)) //Check if entered password = password in db
                        {
                            if (password.equals("password")) // Check if default password
                            {
                                System.out.println("You are required to change your password!");
                                do
                                {
                                    System.out.print("Enter new password: ");
                                    newPassword1 = sc.nextLine();
                                    System.out.print("Enter new password again: ");
                                    newPassword2 = sc.nextLine();
                                    //Verify password entered is the same
                                    if (!newPassword1.equals(newPassword2))
                                    {
                                        System.out.println("Passwords do not match, please try again!");
                                    }
                                } while (newPassword1.equals(newPassword2) == false);
                                updatePasswordInCSV(patientCredentialsDatabase, id.toUpperCase(), newPassword1);
                                System.out.println("Your new password has been set.");
                            }
                            loginSuccess = true;
                            break;
                        }
                        else
                        {
                            System.out.println("Wrong password, enter again.");
                        }
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            if (loginSuccess == false)
            {
                System.out.println("Invalid credentials or user ID not found.");
            }
            else
            {
                System.out.println("Welcome " + matchedRecord.getName() + "!");
                printPatientMenu();
            }
        }
        /*Pharmacist
        if ((id.charAt(0) == 'P' || id.charAt(0) == 'p') && (id.length() == 4))
        {
            printPharmacistMenu();;
        }
        else if (id.charAt(0) == 'D' || id.charAt(0) == 'd')
        {
            printDoctorMenu();
        }
        else if (id.charAt(0) == 'A' || id.charAt(0) == 'a')
        {
            printAdminMenu();
        }*/
    }
 
    public static void updatePasswordInCSV(String filePath, String patientID, String newPassword)
    {
        List<String> lines = new ArrayList<>();
        String line;
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            // Read each line from the CSV file
            while ((line = reader.readLine()) != null)
            {
                String[] columns = line.split(",");

                // Check if this line contains the patient ID we want to update
                if (columns[0].equals(String.valueOf(patientID)))
                {
                    // Update the password field
                    columns[1] = newPassword;
                    line = String.join(",", columns);
                    isUpdated = true;
                }

                // Add the line (updated or not) to the list
                lines.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (isUpdated == true)
        {
            // Write all lines back to the CSV file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath)))
            {
                for (String updatedLine : lines)
                {
                    writer.println(updatedLine);
                }
                System.out.println("Password updated successfully for PatientID: " + patientID);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("PatientID not found: " + patientID);
        }
    }

    public static void printPatientMenu()
    {
        System.out.println("=============================================");
        System.out.println("|                 Patient                   |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. View Medical Record                    |");
        System.out.println("| 2. Update Personal Information            |");
        System.out.println("| 3. View Available Appointment Slots       |");
        System.out.println("| 4. Schedule an Appointment                |");
        System.out.println("| 5. Reschedule an Appointment              |");
        System.out.println("| 6. Cancel an Appointment                  |");
        System.out.println("| 7. View Scheduled Appointments            |");
        System.out.println("| 8. View Past Appointment Outcome Records  |");
        System.out.println("| 9. Logout                                 |");
        System.out.println("=============================================");
    }

    public static void printDoctorMenu()
    {
        System.out.println("=============================================");
        System.out.println("|                  Doctor                   |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. View Patient Medical Records           |");
        System.out.println("| 2. Update Patient Medical Records         |");
        System.out.println("| 3. View Personal Schedule                 |");
        System.out.println("| 4. Set Availability for Appointments      |");
        System.out.println("| 5. Accept or Decline Appointment Request  |");
        System.out.println("| 6. View Upcoming Appointments             |");
        System.out.println("| 7. Record Outcome Records                 |");
        System.out.println("| 8. Logout                                 |");
        System.out.println("=============================================");
    }

    public static void printPharmacistMenu()
    {
        System.out.println("=============================================");
        System.out.println("|                Pharmacist                 |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. View Appointment Outcomcords           |");
        System.out.println("| 2. Update Prescription Status             |");
        System.out.println("| 3. View Medication Inventory              |");
        System.out.println("| 4. Submit Replenishment Request           |");
        System.out.println("| 5. Logout                                 |");
        System.out.println("=============================================");
    }

    public static void printAdminMenu()
    {
        System.out.println("=============================================");
        System.out.println("|              Administrator                |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. View and Manage Hospital Staff         |");
        System.out.println("| 2. View Appointments Details              |");
        System.out.println("| 3. View and Manage Medication Inventory   |");
        System.out.println("| 4. Approve Replenishment Requests         |");
        System.out.println("| 5. Logout                                 |");
        System.out.println("=============================================");
    }
}