package oop;
/**
 * The {@code Menu} class provides methods to print various menus for different user roles
 * in the Hospital Management System. The menus guide the users (patients, doctors, pharmacists, 
 * and administrators) through the available system options for performing tasks such as viewing 
 * records, scheduling appointments, and managing data.
 * @author Ryan Ching
 * @version 1.0
 * @since 2024-11-09
 */
public class Menu {
    /**
     * Prints the main menu with options for login, registration, and quitting the system.
     */
    public static void printMainMenu()
    {
        System.out.println("=========================================");
        System.out.println("|      Hospital Management System       |");
        System.out.println("=========================================");
        System.out.println("| 1. Login                              |");
        System.out.println("| 2. Register as new Patient            |");
        System.out.println("| 3. Quit                               |");
        System.out.println("=========================================");
        System.out.print("Choice: ");
    }
    /**
     * Prints the patient menu with options related to viewing medical records, managing appointments, 
     * updating personal information, and checking bills.
     */
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
        System.out.println("| 9. View Scheduled Appointment Status      |");
        System.out.println("| 10. Change Password                       |");
        System.out.println("| 11. Check Bills                           |");
        System.out.println("| 12. Logout                                |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
    }
    /**
     * Prints the doctor menu with options for managing patient records, viewing personal schedules,
     * setting appointment availability, and handling appointment requests.
     */
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
        System.out.println("| 8. Change Password                        |");
        System.out.println("| 9. Logout                                 |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
    }
    /**
     * Prints the pharmacist menu with options for managing prescription status, viewing medication inventory,
     * and submitting replenishment requests.
     */
    public static void printPharmacistMenu()
    {
        System.out.println("=============================================");
        System.out.println("|                Pharmacist                 |");
        System.out.println("=============================================");
        System.out.println("|        Select from the following          |");
        System.out.println("| 1. View Appointment Outcome Records       |");
        System.out.println("| 2. Update Prescription Status             |");
        System.out.println("| 3. View Medication Inventory              |");
        System.out.println("| 4. Submit Replenishment Request           |");
        System.out.println("| 5. Change Password                        |");
        System.out.println("| 6. Logout                                 |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
    }
    /**
     * Prints the administrator menu with options for managing hospital staff, appointments, medication inventory, 
     * and approving replenishment requests.
     */
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
        System.out.println("| 5. Change Password                        |");
        System.out.println("| 6. Logout                                 |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
    }
    
}
