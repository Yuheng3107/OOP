package oop;
/**
 * The {@code StatusOfAppointment} enum represents the possible statuses of an appointment in the hospital.
 * 
 * <p>This enum can be used to track the progress of an appointment, from the initial request to the final meeting between 
 * the doctor and patient. It provides four possible states:</p>
 * 
 * <ul>
 *     <li>{@code Pending} - The appointment is waiting for confirmation or cancellation from the doctor.</li>
 *     <li>{@code Confirmed} - The doctor has accepted and confirmed the appointment.</li>
 *     <li>{@code Cancelled} - The doctor has rejected or cancelled the appointment.</li>
 *     <li>{@code Completed} - The doctor has met with the patient and the appointment has been concluded.</li>
 * </ul>
 * @author Kuang Yu Xuan, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */
public enum StatusOfAppointment{

    /**
     * The appointment is waiting for confirmation or cancellation from the doctor.
     */
    Pending, //waiting for doctor is confirm or cancel appointment

    /**
     * The doctor has accepted and confirmed the appointment.
     */
    Confirmed, //doctor accepted appointment

    /**
     * The doctor has rejected or cancelled the appointment.
     */
    Cancelled, //doctor rejected appointment

    /**
     * The doctor has met with the patient and the appointment has been completed.
     */
    Completed //doctor has met patient
}