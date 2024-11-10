package oop;
import java.time.LocalDate;

/**
 * Represents an appointment made between a doctor and a patient.
 * An appointment includes details such as the date, time slot, doctor and patient IDs, status of the appointment,
 * and the outcome of the appointment after it has occurred.
 * 
 * The appointment status can be changed, and the appointment outcome can be set once the appointment has concluded.
 * @author Kuang Yu Xuan, Kuang Yu Heng, Ryan Ching, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */
public class Appointment {
    /** The date of the appointment */
    public LocalDate date;

    /** The time slot of the appointment */
    public TimeSlot timeSlot;

    /** The ID of the doctor assigned to the appointment */
    public String doctorId;

    /** The ID of the patient assigned to the appointment */
    public String patientId;

    /** The current status of the appointment (default is 'Pending') */
    public StatusOfAppointment status = StatusOfAppointment.Pending;

    /** The outcome of the appointment */
    public AppointmentOutcome appointmentOutcome;

    /**
     * Constructor for creating an Appointment object.
     * 
     * @param date The date of the appointment.
     * @param timeSlot The time slot for the appointment.
     * @param doctorID The ID of the doctor.
     * @param patientID The ID of the patient.
     */
    public Appointment(LocalDate date, TimeSlot timeSlot, String doctorID, String patientID)
    {
        this.date = date;
        this.timeSlot = timeSlot;
        this.doctorId = doctorID;
        this.patientId = patientID;
    }

    /**
     * Gets the date of the appointment.
     * 
     * @return The appointment date.
     */
    public LocalDate getAppointmentDate()
    {
        return date;
    }

    /**
     * Gets the time slot for the appointment.
     * 
     * @return The appointment time slot.
     */
    public TimeSlot getAppointmentTimeSlot()
    {
        return timeSlot;
    }

    /**
     * Gets the ID of the doctor assigned to the appointment.
     * 
     * @return The doctor ID.
     */
    public String getDocID()
    {
        return doctorId;
    }

    /**
     * Gets the ID of the patient assigned to the appointment.
     * 
     * @return The patient ID.
     */
    public String getPatientId()
    {
        return patientId;
    }

    /**
     * Gets the current status of the appointment.
     * 
     * @return The status of the appointment.
     */
    public StatusOfAppointment getAppointmentStatus()
    {
        return status;
    }

    /**
     * Sets the outcome of the appointment.
     * 
     * @param appointmentOutcome The outcome of the appointment.
     */
    public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome)
    {
       this.appointmentOutcome = appointmentOutcome;
       Patient patient = Hospital.getPatientFromPatientID(patientId);
       patient.appointmentOutcomes.add(appointmentOutcome);
    }
    
    /**
     * Sets the date of the appointment.
     * @param date The new date for the appointment.
     */
    public void setAppointmentDate(LocalDate date)
    {
        this.date = date;
    }

    /**
     * Sets the time slot for the appointment.
     * 
     * @param timeSlot The new time slot for the appointment.
     */
    public void setAppointmentTimeSlot(TimeSlot timeSlot)
    {
        this.timeSlot = timeSlot;
    }

    /**
     * Sets the ID of the doctor for the appointment.
     * @param doctorID The new doctor ID for the appointment.
     */
    public void setDocID(String doctorID)
    {
        this.doctorId = doctorID;
    }

    /**
     * Sets the status of the appointment.
     * 
     * @param status The new status for the appointment.
     */
    public void setAppointmentStatus(StatusOfAppointment status)
    {
        this.status = status;
    }
    // public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome)
    // {
    //     this.appointmentOutcome[0] = appointmentOutcome; //To be fixed again
    // }

    /**
     * Displays the details of the appointment.
     * This includes the doctor's name, appointment date, time slot, and appointment status.
     */
    public void viewAppointment()
    {
        System.out.println("Doctor: " + Hospital.getDoctorNameByStaffID(doctorId));
        System.out.println("Date: " + date);
        System.out.println("Start Time: " + timeSlot.start);
        System.out.println("End Time: " + timeSlot.end);
        System.out.println("Appointment Status: " + status);
    }
}