package oop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args)
    {
        // TimeSlot[] timeSlots = new TimeSlot[] {
        //     new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), LocalTime.of(11, 0)),
        //     new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(11, 0), LocalTime.of(12, 0)),
        //     new TimeSlot(LocalDate.of(2024, 10, 21), LocalTime.of(9, 0), LocalTime.of(10, 0))
        // };

        /*
        Doctor doctor1 = new Doctor("Mike Adams", "D0001");
        Doctor doctor2 = new Doctor("Mikey Mike", "D0002");
        String[] s = new String[1]; //placeholder to create medicalHistory
        s[0] = "a";
        Hospital hospital = new Hospital();
        hospital.staff.add(doctor1);
        hospital.staff.add(doctor2);
        MedicalHistory medic = new MedicalHistory(s,s,s,s);
        Patient patient1 = new Patient("Mary Lamb", "P0001", LocalDate.of(2000, 1, 1), Gender.Female, "111", "ntu address", BloodType.ABMinus, medic, "hi@gmail.com", hospital);
        patient1.scheduleAppointment();
        patient1.viewAvailableAppointmentSlots();
        doctor1.viewPendingAppointments();
        doctor1.declineAppointmentRequest(patient1.getScheduledAppointments().get(0));
        doctor1.viewPendingAppointments();
        patient1.viewAvailableAppointmentSlots();
        */

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
        Menu();
    }

    public static void Menu()
    {
        Scanner sc = new Scanner(System.in);

        String patientFilePath = "D:\\GitHub\\OOP\\Patient_List.csv"; //Need to change this path for anyone that wants to run this w.r.t. your local copy
        String medFilePath = "D:\\GitHub\\OOP\\Medicine_List.csv"; //Need to change this path for anyone that wants to run this w.r.t. your local copy

        List<Patient> patients = ImportUsers.readPatientsFromCSV(patientFilePath);
        //for (Patient patient : patients) {
        //    System.out.println(patient.getGender());
        //    System.out.println(patient.getName());
        //}
        List<MedicineStock> medStocks = ImportUsers.readMedicineFromCSV(medFilePath);
        //for (MedicineStock medStock : medStocks) {
        //    System.out.println(medStock.getName());
        //    System.out.println(medStock.getStock());
        //}
        
        String id = "";
        String password = "";
        System.out.print("Enter your id: ");
        id = sc.nextLine();
        System.out.print("Enter your password: ");
        password = sc.nextLine();
        System.out.println("Your id is " + id + ", password is " + password);

        //Patient
        if ((id.charAt(0) == 'P' || id.charAt(0) == 'p') && (id.length() == 5))
        {
            for (Patient patient : patients)
            {
                if (patient.getPatientID().equalsIgnoreCase(id))
                {
                    System.out.println("Welcome " + patient.getName() + "!");
                    printPatientMenu();
                }
            }
        }
        //Pharmacist
        else if ((id.charAt(0) == 'P' || id.charAt(0) == 'p') && (id.length() == 4))
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
        }
    }

    //Might not be needed anymore
    /*public static void printMainMenu()
    {
        System.out.println("=============================================");
        System.out.println("|        Hospital Management System         |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. Patient                                |");
        System.out.println("| 2. Doctor                                 |");
        System.out.println("| 3. Pharmacist                             |");
        System.out.println("| 4. Administrator                          |");
        System.out.println("| 5. Quit                                   |");
        System.out.println("=============================================");
    }*/

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
