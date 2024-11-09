package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.MedicineStock;
/**
 * Interface to define the method for importing inventory data from a file.
 * This interface is implemented by classes that are responsible for initializing 
 * the inventory from external data sources, in our case, a CSV database file.
 * @author: Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public interface InventoryInitialiserInterface {
    /**
     * Imports inventory data from a specified file and returns a list of medicine stock objects.
     * @param filename The name of the file from which the inventory data is to be imported.
     * @return An ArrayList of MedicineStock objects containing the imported inventory data.
     * @throws IOException If there is an issue reading the file or processing its contents.
     */
    public abstract ArrayList<MedicineStock> importInventory(String filename) throws IOException;
}