package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.HospitalStaff;

/**
 * Interface for importing staff data into the hospital system.
 * This interface defines a method for reading staff information from a specified
 * file and returning it as a list of `HospitalStaff` objects.
 * @author: Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public interface StaffInitialiserInterface {
    /**
     * Imports staff information from the specified file.
     * 
     * @param filename The path to the file containing staff data.
     * @return An `ArrayList` of `HospitalStaff` objects containing the imported staff information.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public abstract ArrayList<HospitalStaff> importStaff(String filename) throws IOException;
}