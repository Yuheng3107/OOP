package oop;

import oop.AdministratorLogic.ReplenishmentRequest;
import java.util.Scanner;

public class Pharmacist extends HospitalStaff
{
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
        Hospital.staffs.add(this);
    }

    public String getID()
    {
        return super.getID();
    }

    public void viewAppointmentOutcomeRecord()
    {
        Scanner sc = new Scanner(System.in);
        String patientID;
        Patient patient = null;

        while (patient == null)
        {
            System.out.print("Enter Patient's ID: ");
            patientID = sc.nextLine();
            patient = findPatientById(patientID);
            if (patient == null)
            {
                System.out.println("Invalid Patient ID, please enter again...");
            }
            else
            {
                break;
            }
        }
        if (patient.appointmentOutcomes.isEmpty())
        {
            System.out.println("No appointment outcomes.");
            return;
        }
        for (AppointmentOutcome outcome : patient.appointmentOutcomes)
        {
            outcome.printAppointmentOutcomeRecord();
        }

    }

    public static Patient findPatientById(String id)
    {
        for (Patient patient : Hospital.patients)
        {
            if (patient.getID().equalsIgnoreCase(id))
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
                System.out.println("System alert: " + med.getName() + " has reached the low stock level of " + med.getStock());
            }
        }
    }

    public void updatePrescriptionStatus()
    {
        Scanner sc = new Scanner(System.in);
        String patientID, response;
        Patient patient = null;

        while (patient == null)
        {
            System.out.print("Enter Patient's ID: ");
            patientID = sc.nextLine();
            patient = findPatientById(patientID);
            if (patient == null)
            {
                System.out.println("Invalid Patient ID, please enter again...");
            }
            else
            {
                break;
            }
        }
        if (patient.appointmentOutcomes.isEmpty())
        {
            System.out.println("No appointment outcomes.");
            return;
        }
        for (AppointmentOutcome outcome : patient.appointmentOutcomes)
        {
            for (PrescribedMedication medication : outcome.prescribedMedications) {
                System.out.println("Medication Name: " + medication.name);
                System.out.println("Medication Status: " + medication.status);
            }
        }
        System.out.print("Ready to dispense medication? Enter y/n: ");
        response = sc.nextLine();
        if (response.equalsIgnoreCase("y"))
        {
            for (AppointmentOutcome outcome : patient.appointmentOutcomes)
            {
                for (PrescribedMedication medication : outcome.prescribedMedications) {
                    medication.status = "Dispensed";
                    System.out.println("Medication Name: " + medication.name);
                    System.out.println("Medication Status: " + medication.status);
                }
            }
        }
        else
        {
            System.out.println("No changes made.");
        }
    }

    public void submitReplenishRequest()
    {
        Scanner sc = new Scanner(System.in);
        String medName;
        int medNewStock;
        Hospital.viewMedicineStock();
        System.out.print("\nEnter the name of the medicine to submit: ");
        medName = sc.nextLine();
        System.out.print("Enter quantity of the medicine in replenishment request: ");
        medNewStock = Integer.parseInt(sc.nextLine());
        ReplenishmentRequest request = new ReplenishmentRequest(medName, medNewStock);
        Hospital.replenishmentRequests.add(request);
    }
}
