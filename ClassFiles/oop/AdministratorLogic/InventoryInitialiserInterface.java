package oop.AdministratorLogic;

import java.util.ArrayList;

import oop.MedicineStock;

public interface InventoryInitialiserInterface {
    public abstract ArrayList<MedicineStock> importInventory(String filename);
}