package oop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ImportUsers {

    public static List<Patient> readPatientsFromCSV(String filePath) {
        List<Patient> patients = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            // Read each line in the CSV
            while ((line = br.readLine()) != null) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public static List<MedicineStock> readMedicineFromCSV(String filePath) {
        List<MedicineStock> medStocks = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Throwaway variable to skip reading 1st line in CSV

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int iniStock = Integer.parseInt(values[1]);
                int lowStockLevel = Integer.parseInt(values[2]);

                MedicineStock medStock = new MedicineStock(name, iniStock, lowStockLevel);
                medStocks.add(medStock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return medStocks;
    }
}
