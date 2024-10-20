package oop.AdministratorLogic;

public interface SystemInitialisationInterface extends StaffInitialiserInterface, PatientInitialiserInterface, InventoryInitialiserInterface{
    public void initialise(String staffFilename, String patientFilename, String inventoryFilename);
}