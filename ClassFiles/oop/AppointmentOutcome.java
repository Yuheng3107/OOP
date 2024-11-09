package oop;
import java.time.LocalDate;

/**
 * The {@code AppointmentOutcome} class represents the outcome of a patient's appointment.
 * It stores details about the date of the appointment, the type of service provided,
 * the prescribed medications, and the consultation notes recorded during the appointment.
 * 
 * <p>This class is used to capture and print the results of a patient's visit, including
 * any treatments and prescriptions made by the doctor, as well as the overall service provided.</p>
 * 
 * @author Kuang Yu Xuan, Kuang Yu Heng
 * @version 1.0
 * @since 2024-11-09
 */
public class AppointmentOutcome {

    /**
     * The date of the appointment outcome.
     */
    public LocalDate date;

    /**
     * The service provided during the appointment.
     */
    public String service;

    /**
     * The list of prescribed medications during the appointment.
     */
    public PrescribedMedication[] prescribedMedications;

    /**
     * Notes taken during the consultation of the appointment.
     */
    public String consultationNotes;

    /**
     * Constructs an {@code AppointmentOutcome} object with the provided appointment details.
     * 
     * @param date the date of the appointment outcome
     * @param service the type of service provided during the appointment
     * @param prescribedMedications the list of prescribed medications during the appointment
     * @param consultationNotes the consultation notes recorded during the appointment
     */
    public AppointmentOutcome(LocalDate date, String service, PrescribedMedication[] prescribedMedications, String consultationNotes)
    {
        this.date = date;
        this.service = service;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    /**
     * Prints the details of the appointment outcome, including the date, service, prescribed medications,
     * and consultation notes.
     */
    public void printAppointmentOutcomeRecord() {
        System.out.println("Date: " + date);
        System.out.println("Service: " + service);
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.println("Medication Name: " + medication.name);
            System.out.println("Medication Quantity: " + medication.getNumberOfUnits());
            System.out.println("Medication Status: " + medication.status);
        }
        System.out.println("Consultation Notes: " + consultationNotes);
    }
}
