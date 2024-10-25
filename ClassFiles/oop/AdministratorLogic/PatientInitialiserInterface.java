package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.Patient;

public interface PatientInitialiserInterface {
   public abstract ArrayList<Patient> importPatients(String filename) throws IOException;
}