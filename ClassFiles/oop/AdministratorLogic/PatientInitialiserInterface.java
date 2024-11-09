package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.Patient;
/**
 * Interface to define the method for importing a list of patients from a file.
 * This interface is implemented by classes that provide functionality for 
 * initializing the patient data, typically by reading from a file.
 * @author: Kuang Yu Heng
 * @version: 1.0
 * @since: 2024-11-09
 */
public interface PatientInitialiserInterface {
   /**
     * Imports a list of patients from a specified file.
     * @param filename The name of the file containing the patient data to be imported.
     * @return An ArrayList of Patient objects representing the imported patient data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
   public abstract ArrayList<Patient> importPatients(String filename) throws IOException;
}