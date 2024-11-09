package oop.UserLogic;
import oop.Gender;
/**
 * Class representing a role within the system.
 * This class contains basic information about a role, including the role's name and gender.
 * It provides methods for managing role attributes such as name and gender, and retrieving the role's details.
 */
public class Role {
    /**
     * The name of the role (e.g., "Doctor", "Patient", "Nurse").
     */
    public String name;
    /**
     * The gender associated with the role.
     */
    public Gender gender;

    
    /**
     * Constructs a new Role with the specified name and gender.
     * 
     * @param name The name of the role.
     * @param gender The gender of the role.
     */
    public Role(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
    
    /**
     * Returns the name of the role.
     * 
     * @return The name of the role.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the gender of the role as a string representation.
     * 
     * @return The gender of the role as a string.
     */
    public String getGender() {
        return gender.toString();
    }

    /**
     * Sets the name of the role.
     * 
     * @param name The name to set for the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the gender of the role.
     * 
     * @param gender The gender to set for the role.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}