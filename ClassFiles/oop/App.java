package oop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import oop.AdministratorLogic.Administrator;
import oop.AdministratorLogic.ReplenishmentRequest;

public class App {
    //Private variables to store the database file name/path
    private static final String patientCredentialsDatabase = "PatientCredentialsDatabase.csv";
    private static final String staffCredentialsDatabase = "StaffCredentialsDatabase.csv";
    //Main program
    public static void main(String[] args)
    {        
        Hospital hospital = new Hospital(); //Create and instantiate hospital
        App.program(); //Run the application
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
                System.out.println("=========================================");
                System.out.println("| Welcome to Hospital Management System |");
                System.out.println("=========================================");
                System.out.print("Enter your id (or -1 to quit): ");
                id = sc.nextLine();
                if (id.equals("-1"))
                {
                    systemLogout = true;
                    break;
                }
                System.out.print("Enter your password: ");
                password = sc.nextLine();

                //Based on user input, we try to find if a patient or hospital staff exist
                Patient matchedPatient = Hospital.findPatientById(id);
                HospitalStaff matchedHospitalStaff = Hospital.findStaffById(id);

                //Found patient
                if ((matchedPatient != null) && (matchedHospitalStaff == null))
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
                //Found staff
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
                //Neither patient nor staff exist with the input ID, hence prompt error
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
                else if (matchedPatient != null) //Authenticated as patient
                {
                    Patient.welcomeMessage(matchedPatient);
                    loginSuccess = true;
                    while (userLogout == false)
                    {
                        Menu.printPatientMenu();
                        menuChoice = Integer.parseInt(sc.nextLine());
                        switch (menuChoice) {
                            case 1:
                                matchedPatient.viewMedicalRecord();
                                break;
                            case 2:
                                matchedPatient.updatePersonalInformation(matchedPatient.getID());
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
                                Password.updatePassword(matchedPatient.getID(), "Patient", patientCredentialsDatabase);
                                break;
                            case 11:
                                matchedPatient.checkBills();
                                break;
                            case 12:
                                Patient.goodbyeMessage(matchedPatient);
                                userLogout = true;
                                loginSuccess = false;
                                break;
                            default:
                                System.out.println("Invalid input! Please try again!");
                                break;
                        }
                    }
                }
                else if (matchedHospitalStaff != null) //Authenticated as staff member, depending on type
                {
                    HospitalStaff.welcomeMessage(matchedHospitalStaff);
                    loginSuccess = true;
                    while (userLogout == false)
                    {
                        switch (matchedHospitalStaff.getRole())
                        {
                            case "Doctor":
                                Doctor doctor = Hospital.getDoctorObjectByStaffID(matchedHospitalStaff.getID());
                                if (doctor != null) {
                                    doctor.checkPendingAppointmentsAlert();
                                    Menu.printDoctorMenu();
                                    menuChoice = Integer.parseInt(sc.nextLine());
                                    switch (menuChoice)
                                    {
                                        case 1:
                                            doctor.viewPatientMedicalRecord();
                                            break;
                                        case 2:
                                            doctor.updateMedicalRecord();
                                            break;
                                        case 3:
                                            doctor.viewPersonalSchedule();
                                            break;
                                        case 4:
                                            doctor.setAvailability();
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
                                            Password.updatePassword(matchedHospitalStaff.getID(), "Doctor", staffCredentialsDatabase);
                                            break;
                                        case 9:
                                            HospitalStaff.goodbyeMessage(matchedHospitalStaff);
                                            userLogout = true;
                                            loginSuccess = false;
                                            break;
                                        default:
                                            System.out.println("Invalid input! Please try again!");
                                            break;
                                    }
                                }
                                break;
                            case "Pharmacist":
                                Hospital.checkStockAlert();
                                Menu.printPharmacistMenu();
                                menuChoice = Integer.parseInt(sc.nextLine());
                                Pharmacist pharmacist = Hospital.getPharmacistObjectByStaffID(matchedHospitalStaff.getID());
                                switch (menuChoice)
                                {
                                    case 1:
                                        pharmacist.viewAppointmentOutcomeRecord();
                                        break;
                                    case 2:
                                        pharmacist.updatePrescriptionStatus();
                                        break;
                                    case 3:
                                        Hospital.viewMedicineStock();
                                        break;
                                    case 4:
                                        pharmacist.submitReplenishRequest();
                                        break;
                                    case 5:
                                        Password.updatePassword(matchedHospitalStaff.getID(), "Pharmacist", staffCredentialsDatabase);
                                        break;
                                    case 6:
                                        HospitalStaff.goodbyeMessage(matchedHospitalStaff);
                                        userLogout = true;
                                        loginSuccess = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input! Please try again!");
                                        break;
                                }
                                break;
                            case "Staff Member":
                                Hospital.checkStockAlert();
                                Menu.printAdminMenu();
                                menuChoice = Integer.parseInt(sc.nextLine());
                                Administrator administrator = Hospital.getAdministratorObjectByStaffID(matchedHospitalStaff.getID());
                                switch (menuChoice)
                                {
                                    case 1:
                                        administrator.manageHospitalStaff();
                                        //Some errors
                                        break;
                                    case 2:
                                        System.out.println("View Appointments Details");
                                        administrator.viewAppointmentDetails();
                                        break;
                                    case 3:
                                        System.out.println("View and Manage Medication Inventory");
                                        administrator.manageInventory();
                                        break;
                                    case 4:
                                        System.out.println("Approve Replenishment Requests:");
                                        System.out.println("Printing all the replenishment requests: ");
                                        for (int i = 0; i < Hospital.replenishmentRequests.size(); i++) {
                                            ReplenishmentRequest request = Hospital.replenishmentRequests.get(i);
                                            System.out.println("Request " + (i + 1) + ": ");
                                            request.printInfo();
                                        }
                                        System.out.println("Choose the index of the request to approve: ");
                                        int index = Integer.parseInt(sc.nextLine());
                                        administrator.approveReplenishmentRequest(index-1);
                                        break;
                                    case 5:
                                        Password.updatePassword(matchedHospitalStaff.getID(), "Staff Member", staffCredentialsDatabase);
                                        break;
                                    case 6:
                                        HospitalStaff.goodbyeMessage(matchedHospitalStaff);
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