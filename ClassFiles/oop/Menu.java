package oop;
public class Menu {

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
        System.out.println("| 10. Logout                                |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
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
        System.out.print("Choice: ");
    }

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
        System.out.println("| 5. Logout                                 |");
        System.out.println("=============================================");
        System.out.print("Choice: ");
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
        System.out.print("Choice: ");
    }
    
}
