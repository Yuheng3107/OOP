public interface StaffManagementInterface {
    public void manageHospitalStaff();
    public void addStaffMember(HospitalStaff staffMember);
    public void updateStaffMember(HospitalStaff staffMember);
    public void removeStaffMember(HospitalStaff staffMember);
    public void displayStaff(String filter);
}
