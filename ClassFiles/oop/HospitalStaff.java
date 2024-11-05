package oop;
import oop.UserLogic.Role;

public abstract class HospitalStaff extends Role
{
    public int age;
    public String staffID;
    
    public HospitalStaff(String name, String staffID, int age, Gender gender)
    {
        super(name, gender);
        this.age = age;
        this.staffID = staffID;
    }

    public int getAge() {
        return age;
    }
        
    public String getID() {
        return staffID;
    }

    public String getName()
    {
        return name;
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

    public void setAge(int age) {
        this.age = age;
    }

    public static void welcomeMessage(HospitalStaff staff)
    {
        System.out.println("\nWelcome " + staff.getName() + "!");
    }
    public static void goodbyeMessage(HospitalStaff staff)
    {
        System.out.println("Goodbye " + staff.getName() + "!\n");
    }
}