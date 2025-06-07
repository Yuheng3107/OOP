package oop.AdministratorLogic;

/**
 * The AppointmentManagementInterface provides the contract for managing and viewing appointment details.
 * Implementing classes should provide the logic for viewing the details of appointments.
 * 
 * <p>Any class that implements this interface must provide an implementation for the 
 * {@link #viewAppointmentDetails()} method to display or retrieve the details of appointments.</p>
 * @author Kuang Yu Heng
 * @version 1.0
 * @since 2024-11-09
 */
public interface AppointmentManagementInterface {
    /**
     * View the details of an appointment.
     * This method shall be implemented to provide functionality to display or retrieve appointment details.
     */
    public void viewAppointmentDetails();
}