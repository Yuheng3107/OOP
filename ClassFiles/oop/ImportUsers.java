package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import oop.AdministratorLogic.Administrator;
/**
 * The {@code ImportUsers} class provides methods to read patients and from CSV files.
 * It creates and manages necessary data objects and ensures that credentials are
 * created for patients and staff if not already present.
 *
 * <p>This class is primarily designed for importing and initializing data from CSV files into the system and handles
 * various types of data, patients and staff members.
 * @author Ryan Ching
 * @version 1.0
 * @since 2024-11-09
 */
public class ImportUsers {
    /**
     * Reads patient information from a specified CSV file, creates {@link Patient} objects, and generates a
     * credentials file with default passwords if it does not already exist.
     *
     * @param filePath the path to the CSV file containing patient data
     * @return a list of {@link Patient} objects representing the patients listed in the CSV file
     */
    public static ArrayList<Patient> readPatientsFromCSV(String filePath)
    {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            // Read each line in the CSV
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String patientId = values[0];
                String name = values[1];
                LocalDate dateOfBirth = LocalDate.parse(values[2]);
                Gender gender = values[3].equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
                String blood = values[4];
                String email = values[5];

                BloodType bloodType = BloodType.APlus; //Need to initialize with some value first, else VS not happy :(
                switch(blood)
                {
                    case "A+":
                        bloodType = BloodType.APlus;
                        break;
                    case "A-":
                        bloodType = BloodType.AMinus;
                        break;
                    case "B+":
                        bloodType = BloodType.BPlus;
                        break;
                    case "B-":
                        bloodType = BloodType.BMinus;
                        break;
                    case "O+":
                        bloodType = BloodType.OPlus;
                        break;
                    case "O-":
                        bloodType = BloodType.OMinus;
                        break;
                    case "AB+":
                        bloodType = BloodType.ABPlus;
                        break;
                    case "AB-":
                        bloodType = BloodType.ABMinus;
                        break;
                }
                Patient patient = new Patient(name, patientId, dateOfBirth, gender, bloodType, email);
                patients.add(patient);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        File databaseFile = new File("PatientCredentialsDatabase.csv");
        if (!databaseFile.exists())
        {
            //Write to database the ID and the default password
            try (PrintWriter writer = new PrintWriter(new FileWriter("PatientCredentialsDatabase.csv")))
            {
                writer.println("ID,Password");
                // Write patient data
                for (Patient patient : patients)
                {
                    String patientID = patient.getID();
                    String defaultPassword = Password.hashPassword("password");
                    writer.println(patientID + "," + defaultPassword);
                }
                //System.out.println("DEBUG: Patient Credentials file created successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            //System.out.println("DEBUG: Patient Credentials file already exists.");
        }
        return patients;
    }
    /**
     * Reads hospital staff information from a specified CSV file, creates {@link HospitalStaff} objects, and generates a
     * credentials file with default passwords if it does not already exist.
     *
     * @param filePath the path to the CSV file containing hospital staff data
     * @return a list of {@link HospitalStaff} objects representing the hospital staff listed in the CSV file
     */
    public static ArrayList<HospitalStaff> readStaffFromCSV(String filePath)
    {
        ArrayList<HospitalStaff> staff = new ArrayList<HospitalStaff>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String staffId = values[0];
                String name = values[1];
                String role = values[2];
                Gender gender = values[3].equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
                int age = Integer.parseInt(values[4]);

                // Instantiate the appropriate subclass based on the role
                HospitalStaff hospitalStaff = null;
                switch (role) {
                    case "Doctor":
                        hospitalStaff = new Doctor(name, staffId, age, gender);
                        break;
                    case "Pharmacist":
                        hospitalStaff = new Pharmacist(name, staffId, age, gender);
                        break;
                    case "Administrator":
                        hospitalStaff = new Administrator(name, staffId, age, gender);
                        break;
                    default:
                        System.out.println("Unknown role: " + role);
                }

                if (hospitalStaff != null) {
                    staff.add(hospitalStaff);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File databaseFile = new File("StaffCredentialsDatabase.csv");
        if (!databaseFile.exists()) {
            // Write the ID and a default password to the credentials database
            try (PrintWriter writer = new PrintWriter(new FileWriter(databaseFile))) {
                writer.println("ID,Password");

                // Write staff credentials
                for (HospitalStaff hospitalStaff : staff) {
                    String staffID = hospitalStaff.getID();
                    String defaultPassword = Password.hashPassword("password");
                    writer.println(staffID + "," + defaultPassword);
                }
                //System.out.println("DEBUG: Staff Credentials file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //System.out.println("DEBUG: Staff Credentials file already exists.");
        }
        return staff;
    }
}