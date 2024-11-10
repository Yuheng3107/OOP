package oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code ImportMed} class provides methods to read the medicine stock from CSV files.
 * It creates and manages necessary data objects and ensures that credentials are
 * created for patients and staff if not already present.
 *
 * <p>This class is primarily designed for importing and initializing data from CSV files into the system and handles
 * the medicine data.
 * @author Ryan Ching
 * @version 1.0
 * @since 2024-11-09
 */
public class ImportMed {
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
}
