public interface SystemInitialisationInterface extends StaffInitialiserInterface, PatientInitialiserInterface, InventoryInitialiserInterface{
    public void initialise(String staffFilename, String patientFilename, Inventory inventoryFilename);
}