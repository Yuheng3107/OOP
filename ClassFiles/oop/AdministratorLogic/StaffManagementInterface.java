package oop.AdministratorLogic;

import oop.HospitalStaff;
import oop.Gender;

public interface StaffManagementInterface {
    public void manageHospitalStaff();
    public void addStaffMember(String staffName, int age, String staffID, Gender gender);
    public void updateStaffMember(String staffName);
    public void removeStaffMember(String staffName);
    public void displayStaff(String filter);
}
