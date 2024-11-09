package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import oop.AdministratorLogic.Administrator;
/**
 * The {@code ImportUsers} class provides methods to read various types of data (patients, hospital staff, medicine stock,
 * available time slots) from CSV files. It creates and manages necessary data objects and ensures that credentials are
 * created for patients and staff if not already present.
 *
 * <p>This class is primarily designed for importing and initializing data from CSV files into the system and handles
 * various types of data, such as patients, staff members, medicines, and available time slots.
 * @author Ryan Ching, Tan Zhe Kai
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
                System.out.println("DEBUG: Patient Credentials file created successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("DEBUG: Patient Credentials file already exists.");
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
                System.out.println("DEBUG: Staff Credentials file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("DEBUG: Staff Credentials file already exists.");
        }
        return staff;
    }
    /**
     * Reads medicine stock data from a specified CSV file and creates {@link MedicineStock} objects based on the
     * contents of the file.
     *
     * @param filePath the path to the CSV file containing medicine stock data
     * @return a list of {@link MedicineStock} objects representing the medicines and their stock information listed in the CSV file
     */
    public static ArrayList<MedicineStock> readMedicineFromCSV(String filePath) {
        ArrayList<MedicineStock> medStocks = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String name = values[0];
                int iniStock = Integer.parseInt(values[1]);
                int lowStockLevel = Integer.parseInt(values[2]);
                int price = Integer.parseInt(values[3]);

                MedicineStock medStock = new MedicineStock(name, iniStock, lowStockLevel, price);
                medStocks.add(medStock);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return medStocks;
    }
    /* 

    public static ArrayList<Appointment> readAppointmentsFromCSV(String filepath) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                LocalDate date = LocalDate.parse(values[0]);
                LocalTime start = LocalTime.parse(values[1]);
                LocalTime end = LocalTime.parse(values[2]);
                TimeSlot timeSlot = new TimeSlot(date, start, end);
                String doctorID = values[3];
                String patientID = values[4];
                String statusString = values[5];
                
                StatusOfAppointment status = StatusOfAppointment.Pending;
                    switch(statusString)
                    {
                        case "Pending":
                            status = StatusOfAppointment.Pending;
                            break;
                        case "Confirmed":
                            status = StatusOfAppointment.Confirmed;
                            break;
                        case "Cancelled":
                            status = StatusOfAppointment.Cancelled;
                            break;
                        case "Completed":
                            status = StatusOfAppointment.Completed;
                            break;
                    }

                Appointment appointment = new Appointment(date, timeSlot, doctorID, patientID, status);
                appointments.add(appointment);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return appointments;
    }
        */
    /**
     * Reads available time slot information from a specified CSV file, creates {@link AvailableTimeSlot} objects, and
     * returns them in a list.
     *
     * @param filepath the path to the CSV file containing available time slot data
     * @return a list of {@link AvailableTimeSlot} objects representing the available time slots listed in the CSV file
     */
    public static ArrayList<AvailableTimeSlot> readAvailableTSFromCSV(String filepath) {
        ArrayList<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String doctorID = values[0];
                LocalDate date = LocalDate.parse(values[1]);
                LocalTime start = LocalTime.parse(values[2]);
                LocalTime end = LocalTime.parse(values[3]);
                TimeSlot timeSlot = new TimeSlot(date, start, end);
                Boolean isAvail = Boolean.parseBoolean(values[4]);

                AvailableTimeSlot availableTimeSlot = new AvailableTimeSlot(doctorID, date, timeSlot, isAvail);
                availableTimeSlots.add(availableTimeSlot);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return availableTimeSlots;
    }

}