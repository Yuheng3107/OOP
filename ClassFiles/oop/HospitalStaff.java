package oop;
import oop.UserLogic.Role;

public abstract class HospitalStaff extends Role {
    
    public int age;
    public String staffID;
    
    public HospitalStaff(String name, String staffID, int age, Gender gender) {
        super(name, gender);
        this.age = age;
        this.staffID = staffID;
    }

    public int getAge() {
        return age;
    }
        
    public String getStaffID() {
        return staffID;
    }

    public String getRole() {
        if (this instanceof Doctor) {
            return "Doctor";
        } else if (this instanceof Pharmacist) {
            return "Pharmacist";
        } else {
            return "Staff Member";
        }
    }


}
