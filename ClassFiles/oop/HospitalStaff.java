package oop;

/**
 * The {@code HospitalStaff} class represents a staff member of a hospital. It extends the {@link Role} class
 * and provides common attributes and methods that can be shared by specific types of hospital staff.
 * 
 * <p>This abstract class includes fields for the staff member's age and staff ID, along with getter and setter methods
 * to manage these attributes. It also provides functionality to retrieve the staff's name and role, and includes static 
 * methods to display welcome and goodbye messages.</p>
 * 
 * <p>Concrete classes that extend {@code HospitalStaff} should define specific roles such as {@code Doctor} or {@code Pharmacist}.</p>
 * @author Kuang Yu Heng, Kuang Yu Xuan, Ryan Ching
 * @version 1.0
 * @since 2024-11-09
 */
public abstract class HospitalStaff extends Role
{
    /**
     * The age of the hospital staff member.
     */
    public int age;

    /**
     * The unique staff ID assigned to the hospital staff member.
     */
    public String staffID;
    
    /**
     * Constructs a new {@code HospitalStaff} with the specified name, staff ID, age, and gender.
     *
     * @param name The name of the hospital staff member.
     * @param staffID The unique staff ID of the hospital staff member.
     * @param age The age of the hospital staff member.
     * @param gender The gender of the hospital staff member.
     */
    public HospitalStaff(String name, String staffID, int age, Gender gender)
    {
        super(name, gender);
        this.age = age;
        this.staffID = staffID;
    }

    /**
     * Returns the age of the hospital staff member.
     *
     * @return The age of the staff member.
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Returns the staff ID of the hospital staff member.
     *
     * @return The unique staff ID of the staff member.
     */
    public String getID() {
        return staffID;
    }


    /**
     * Returns the role of the hospital staff member as a string.
     * This method checks the type of staff member and returns a corresponding role.
     * 
     * @return The role of the staff member (e.g., "Doctor", "Pharmacist", or "Staff Member").
     */
    public String getRole() {
        if (this instanceof Doctor) {
            return "Doctor";
        } else if (this instanceof Pharmacist) {
            return "Pharmacist";
        } else {
            return "Staff Member";
        }
    }

    /**
     * Sets the age of the hospital staff member.
     *
     * @param age The new age of the staff member.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Displays a welcome message for the given hospital staff member.
     * 
     * @param staff The staff member to welcome.
     */
    public static void welcomeMessage(HospitalStaff staff)
    {
        System.out.println("\nWelcome " + staff.getName() + "!");
    }

    /**
     * Displays a goodbye message for the given hospital staff member.
     * 
     * @param staff The staff member to say goodbye to.
     */
    public static void goodbyeMessage(HospitalStaff staff)
    {
        System.out.println("Goodbye " + staff.getName() + "!\n");
    }


    /**
     * Prints a description of the role for a general hospital staff member.
     */
    public void retrieveRoleDescription(){
        System.out.println("General hospital staff member.");
    }
}