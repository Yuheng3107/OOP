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
        for (Patient patient : patients) {
            System.out.println(patient.getGender());
            System.out.println(patient.getName());
        }
        List<MedicineStock> medStocks = ImportUsers.readMedicineFromCSV(medFilePath);
        for (MedicineStock medStock : medStocks) {
            System.out.println(medStock.getName());
            System.out.println(medStock.getStock());
        }

        while (true)
        {
            printMenu();
            int choice = sc.nextInt();
            if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5)
            {
                System.out.println("Invalid input! Please enter again!\n");
                continue;
            }
            switch(choice)
            {
                case 1:
                    System.out.println("Patient");
                    break;
                case 2:
                    System.out.println("Doctor");
                    break;
                case 3:
                    System.out.println("Pharmacist");
                    break;
                case 4:
                    System.out.println("Administrator");
                    break;
                case 5:
                    System.out.println("Quit");
                    return;
            }
        }
    }

    public static void printMenu()
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
    }
}
