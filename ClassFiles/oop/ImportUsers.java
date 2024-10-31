package oop;

import java.io.*;
import java.util.*;

import oop.AdministratorLogic.Administrator;

import java.time.*;
import java.nio.*;

public class ImportUsers {
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

                BloodType bloodType = BloodType.APLus; //Need to initialize with some value first, else VS not happy :(
                switch(blood)
                {
                    case "A+":
                        bloodType = BloodType.APLus;
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
                    String patientID = patient.getPatientID();
                    String defaultPassword = "password";
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
                        hospitalStaff = new Administrator(name, staffId, gender, age);
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
                    String staffID = hospitalStaff.getStaffID();
                    String defaultPassword = "password";
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

    /*
    public static List<Doctor> readDoctorFromCSV(String filePath)
    {
        List<Doctor> doctors = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            // Read each line in the CSV
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String docId = values[0];
                String name = values[1];
                Gender gender = values[3].equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
                int age = Integer.parseInt(values[4]);

                Doctor doctor = new Doctor(name, docId, age, gender);
                doctors.add(doctor);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        File databaseFile = new File("DoctorCredentialsDatabase.csv");
        if (!databaseFile.exists())
        {
            //Write to database the ID and the default password
            try (PrintWriter writer = new PrintWriter(new FileWriter("DoctorCredentialsDatabase.csv")))
            {
                writer.println("ID,Password");
                // Write patient data
                for (Doctor doctor : doctors)
                {
                    String docID = doctor.getID();
                    String defaultPassword = "password";
                    writer.println(docID + "," + defaultPassword);
                }
                System.out.println("Doctor Credentials file created successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Doctor Credentials file already exists.");
        }
        return doctors;
    }

    /*public static List<Pharmacist> readPharmacistFromCSV(String filePath)
    {
        List<Pharmacist> pharmacists = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            // Read each line in the CSV
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String docId = values[0];
                String name = values[1];
                Gender gender = values[3].equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
                int age = Integer.parseInt(values[4]);

                Pharmacist pharmacist = new Pharmacist(name, docId, age, gender);
                pharmacists.add(pharmacist);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        File databaseFile = new File("PharmacistCredentialsDatabase.csv");
        if (!databaseFile.exists())
        {
            //Write to database the ID and the default password
            try (PrintWriter writer = new PrintWriter(new FileWriter("PharmacistCredentialsDatabase.csv")))
            {
                writer.println("ID,Password");
                // Write patient data
                for (Pharmacist pharmacist : pharmacists)
                {
                    String docID = pharmacist.getID();
                    String defaultPassword = "password";
                    writer.println(docID + "," + defaultPassword);
                }
                System.out.println("Pharmacist Credentials file created successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Pharmacist Credentials file already exists.");
        }
        return pharmacists;
    }

    public static List<Administrator> readAdministratorFromCSV(String filePath)
    {
        List<Administrator> administrators = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            // Read each line in the CSV
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                String adminId = values[0];
                String name = values[1];
                Gender gender = values[3].equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
                int age = Integer.parseInt(values[4]);

                Administrator administrator = new Administrator(name, adminId, gender, age);
                administrators.add(administrator);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        File databaseFile = new File("AdministratorCredentialsDatabase.csv");
        if (!databaseFile.exists())
        {
            //Write to database the ID and the default password
            try (PrintWriter writer = new PrintWriter(new FileWriter("AdministratorCredentialsDatabase.csv")))
            {
                writer.println("ID,Password");
                // Write patient data
                for (Administrator administrator : administrators)
                {
                    String adminID = administrator.getAdminID();
                    String defaultPassword = "password";
                    writer.println(adminID + "," + defaultPassword);
                }
                System.out.println("Administrator Credentials file created successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Administrator Credentials file already exists.");
        }
        return administrators;
    }

    public static List<MedicineStock> readMedicineFromCSV(String filePath) {
        List<MedicineStock> medStocks = new ArrayList<>();
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

                MedicineStock medStock = new MedicineStock(name, iniStock, lowStockLevel);
                medStocks.add(medStock);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return medStocks;
    }

    public static void splitStaffList()
    {
        String inputFile = "Staff_List.csv";
        String doctorFile = "Doctors.csv";
        String pharmacistFile = "Pharmacists.csv";
        String administratorFile = "Administrators.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            FileWriter doctorWriter = new FileWriter(doctorFile);
            FileWriter pharmacistWriter = new FileWriter(pharmacistFile);
            FileWriter administratorWriter = new FileWriter(administratorFile);

            // Write headers to each output file
            String header = br.readLine();
            doctorWriter.write(header + "\n");
            pharmacistWriter.write(header + "\n");
            administratorWriter.write(header + "\n");

            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                String role = columns[2];
                switch (role) {
                    case "Doctor":
                        doctorWriter.write(line + "\n");
                        break;
                    case "Pharmacist":
                        pharmacistWriter.write(line + "\n");
                        break;
                    case "Administrator":
                        administratorWriter.write(line + "\n");
                        break;
                    default:
                        System.out.println("Unknown role: " + role);
                }
            }
            doctorWriter.close();
            pharmacistWriter.close();
            administratorWriter.close();

            System.out.println("Staff_List splitted and created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}