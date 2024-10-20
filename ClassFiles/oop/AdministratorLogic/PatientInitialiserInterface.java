package oop.AdministratorLogic;

import java.util.ArrayList;

import oop.Hospital;
import oop.Patient;

public interface PatientInitialiserInterface {
   public abstract ArrayList<Patient> importPatients(String filename, Hospital hospital);
}