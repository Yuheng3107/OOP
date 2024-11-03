package oop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import oop.AdministratorLogic.Administrator;

public class App {
    private static final String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
    private static final String staffCredentialsDatabase = "StaffCredentialsDatabase.csv";
    public static void main(String[] args)
    {
        TimeSlot[] timeSlots = new TimeSlot[] {
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), LocalTime.of(11, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(11, 0), LocalTime.of(12, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 21), LocalTime.of(9, 0), LocalTime.of(10, 0))
        };
        String[] s = new String[1]; //placeholder to create medicalHistory
        s[0] = "a";
        
        Hospital hospital = new Hospital();
        App.program();
    }

    public static void program()
    {
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

                Patient matchedPatient = Hospital.findPatientById(id);
                HospitalStaff matchedHospitalStaff = Hospital.findStaffById(id);

                if ((matchedPatient != null) && (matchedHospitalStaff == null)) //Found patient
                {
                    try (BufferedReader reader = new BufferedReader(new FileReader(patientCredentialsDatabase)))
                    {
                        loginSuccess = Hospital.processLoginPatient(reader, id, password, matchedPatient, sc);
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
                        loginSuccess = Hospital.processLoginHospitalStaff(reader, id, password, matchedHospitalStaff, sc);
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
                                matchedPatient.getMedicalRecord();
                                break;
                            case 2:
                                matchedPatient.updatePersonalInformation(matchedPatient.getPatientID());
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
                                matchedPatient.viewScheduledAppointments();
                                break;
                            case 8:
                                matchedPatient.viewAppointmentOutcomeRecords();
                                break;
                            case 9:
                                matchedPatient.viewScheduledAppointmentStatus();
                                break;
                            case 10:
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
                                Doctor doctor = Hospital.getDoctorObjectByStaffID(matchedHospitalStaff.getStaffID());
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
                                        doctor.acceptOrDeclineAppointmentRequest();
                                        break;
                                    case 6:
                                        doctor.viewUpcomingAppointments();
                                        break;
                                    case 7:
                                        doctor.recordAppointmentOutcome();
                                        break;
                                    case 8:
                                        System.out.println("Goodbye " + doctor.getName() + "!\n");
                                        userLogout = true;
                                        loginSuccess = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input! Please try again!");
                                        break;
                                }
                                break;
                            case "Pharmacist":
                                int pharChoice = 0;
                                Menu.printPharmacistMenu();
                                menuChoice = Integer.parseInt(sc.nextLine());
                                Pharmacist pharmacist = Hospital.getPharmacistObjectByStaffID(matchedHospitalStaff.getStaffID());
                                switch (menuChoice)
                                {
                                    case 1:
                                        System.out.println("View Appointment Outcomes Records");
                                        break;
                                    case 2:
                                        System.out.println("Update Prescription Status");
                                        break;
                                    case 3:
                                        pharmacist.viewMedicationInventory();
                                        break;
                                    case 4:
                                        pharmacist.submitReplenishRequest();
                                        //Hospital.displayReplenishmentRequest();
                                        break;
                                    case 5:
                                        System.out.println("Goodbye " + pharmacist.getName() + "!\n");
                                        userLogout = true;
                                        loginSuccess = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input! Please try again!");
                                        break;
                                }
                                break;
                            case "Staff Member":
                                int adminChoice = 0;
                                Menu.printAdminMenu();
                                menuChoice = Integer.parseInt(sc.nextLine());
                                Administrator administrator = Hospital.getAdministratorObjectByStaffID(matchedHospitalStaff.getStaffID());
                                switch (menuChoice)
                                {
                                    case 1:
                                        administrator.manageHospitalStaff();
                                        //Some errors
                                        break;
                                    case 2:
                                        System.out.println("View Appointments Details");
                                        //administrator.viewAppointmentDetails(null);
                                        break;
                                    case 3:
                                        System.out.println("View and Manage Medication Inventory");
                                        administrator.manageInventory();
                                        break;
                                    case 4:
                                        System.out.println("Approve Replenishment Requests");
                                        //@ Yu Heng this part I not very sure how you implemented this
                                        System.out.println("Enter name of the medicine to approve: ");
                                        String name = sc.next();
                                        System.out.println("Enter quantity of the medicine in replenishment request: ");
                                        int quantity = Integer.parseInt(sc.nextLine());
                                        administrator.approveReplenishmentRequest(name, quantity);
                                        break;
                                    case 5:
                                        System.out.println("Goodbye " + administrator.getName() + "!\n");
                                        userLogout = true;
                                        loginSuccess = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input! Please try again!");
                                        break;
                                }
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
}