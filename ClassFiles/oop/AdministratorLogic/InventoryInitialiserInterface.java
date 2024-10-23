package oop.AdministratorLogic;

import java.io.IOException;
import java.util.ArrayList;

import oop.MedicineStock;

public interface InventoryInitialiserInterface {
    public abstract ArrayList<MedicineStock> importInventory(String filename) throws IOException;
}