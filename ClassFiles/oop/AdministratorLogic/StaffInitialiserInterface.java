package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.HospitalStaff;

public interface StaffInitialiserInterface {
    public abstract ArrayList<HospitalStaff> importStaff(String filename);
}