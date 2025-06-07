package oop.AdministratorLogic;

import oop.Gender;

/**
 * Interface for managing hospital staff within the system.
 * This interface provides methods for adding, updating, removing, and displaying staff members,
 * as well as managing hospital staff overall.
 * @author Kuang Yu Heng
 * @version 1.0
 * @since 2024-11-09
 */
public interface StaffManagementInterface {
    /**
     * Manages the hospital staff, typically used to handle general staff-related operations
     * such as adding, updating, and displaying staff members.
     */
    public void manageHospitalStaff();

    /**
     * Adds a new staff member to the hospital system.
     * 
     * @param staffName The name of the staff member.
     * @param age The age of the staff member.
     * @param staffID The unique ID of the staff member.
     * @param gender The gender of the staff member.
     * @param role The role or position of the staff member (e.g., Doctor, Nurse, etc.).
     */
    public void addStaffMember(String staffName, int age, String staffID, Gender gender, String role);

    /**
     * Updates information of an existing staff member.
     * 
     * @param staffName The name of the staff member to update.
     */
    public void updateStaffMember(String staffName);

    /**
     * Removes a staff member from the hospital system.
     * 
     * @param staffName The name of the staff member to be removed.
     */
    public void removeStaffMember(String staffName);

    /**
     * Displays the list of staff members based on the specified filter.
     * 
     * @param filter A filter to apply when displaying staff (e.g., by role or department).
     */
    public void displayStaff(String filter);
}
