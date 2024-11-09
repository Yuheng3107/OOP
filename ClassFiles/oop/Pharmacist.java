package oop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.AdministratorLogic.ReplenishmentRequest;

/**
 * The {@code Pharmacist} class represents a pharmacist in the hospital system. 
 * This class provides functionalities specific to a pharmacist's role, 
 * including viewing appointment outcomes, updating prescription status, 
 * and managing medication stock levels.
 * @author Ryan Ching
 * @version 1.0
 * @since 2024-11-09
 * 
 * Inherits from {@link HospitalStaff}.
 */
public class Pharmacist extends HospitalStaff
{   /** Stores the file path to read the medicine */
    private String medFilePath = "Medicine_List.csv";
    /**
     * Constructs a {@code Pharmacist} instance with the specified details.
     * Adds the pharmacist to the hospital's staff list.
     * 
     * @param name    the name of the pharmacist
     * @param staffID the unique ID of the pharmacist
     * @param age     the age of the pharmacist
     * @param gender  the gender of the pharmacist
     */
    public Pharmacist(String name, String staffID, int age, Gender gender) {
        super(name, staffID, age, gender);
        Hospital.staffs.add(this);
    }
    /**
     * Prompts the user to enter a patient ID and displays the patient's 
     * appointment outcome records.
     * 
     * If no appointment outcomes are available, a message is displayed.
     */
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
    /**
     * Finds a patient by their unique ID and return it
     * 
     * @param id the unique ID of the patient
     * @return the {@code Patient} object if found, {@code null} otherwise
     */
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

    /**
     * Allows the pharmacist to update the prescription status of a patient's medication.
     * The method prompts the pharmacist for a patient ID and displays the prescribed
     * medications that have not yet been dispensed. If confirmed, the status of the
     * medications is updated to dispensed, and the stock is adjusted in the system.
     * Else, the system will indicate no changes were made.
     */
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
        boolean toBeDispensed = false;
        for (AppointmentOutcome outcome : patient.appointmentOutcomes)
        {
            for (PrescribedMedication medication : outcome.prescribedMedications)
            {
                if (medication.status != StatusOfPrescribedMedication.Dispensed)
                {
                    System.out.println("Medication Name: " + medication.name);
                    System.out.println("Medication Quantity: " + medication.getNumberOfUnits());
                    System.out.println("Medication Status: " + medication.status);
                    System.out.println();
                    toBeDispensed = true;
                }
            }
        }
        if (toBeDispensed == true)
        {
            System.out.print("Ready to dispense medication? Enter y/n: ");
            response = sc.nextLine();
            if (response.equalsIgnoreCase("y"))
            {
                for (AppointmentOutcome outcome : patient.appointmentOutcomes)
                {
                    for (PrescribedMedication medication : outcome.prescribedMedications)
                    {
                        if (medication.status != StatusOfPrescribedMedication.Dispensed)
                        {
                            medication.status = StatusOfPrescribedMedication.Dispensed;
                            System.out.println("Medication Name: " + medication.name);
                            System.out.println("Medication Quantity: " + medication.getNumberOfUnits());
                            System.out.println("Medication Status: " + medication.status);
                            System.out.println();
                            for (MedicineStock med : Hospital.inventory)
                            {
                                if (med.getName().equalsIgnoreCase(medication.name))
                                {
                                    med.setStock(med.getStock() - medication.getNumberOfUnits());
                                    updateMedStockCSV(med.getName(), med.getStock());
                                    med.setRollingStock(med.getRollingStock() - medication.getNumberOfUnits());
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                System.out.println("No changes made.");
            }
        }
        else
        {
            System.out.println("No pending medications to be dispensed for patient");
        }
    }
    /**
     * Submits a request to replenish the stock of a specified medicine. The pharmacist
     * enters the medicine name and the requested quantity, and this information is 
     * recorded in the hospital's list of replenishment requests which will be accessible
     * and approvedby the administrator.
     */
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
    /**
     * Updates the stock of a specified medicine in the CSV file that maintains 
     * medicine records to ensure persistent data storage and accurate values
     * 
     * @param name     the name of the medicine to update
     * @param newStock the new stock quantity to set for the medicine
     */
    public void updateMedStockCSV(String name, int newStock)
    {
        List<String[]> data = new ArrayList<>();
        boolean found = false;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(medFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
                    fields[1] = String.valueOf(newStock); // Update the stock
                    found = true;
                }
                data.add(fields);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Medicine name not found in the CSV file.");
            return;
        }

        // Write the updated data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(medFilePath))) {
            for (String[] fields : data) {
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
