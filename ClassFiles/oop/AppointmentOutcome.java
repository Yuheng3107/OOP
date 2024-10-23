package oop;
import java.time.LocalDate;

public class AppointmentOutcome {
    public LocalDate date;
    public String service;
    public PrescribedMedication[] prescribedMedications;

    public void printAppointmentOutcomeRecord() {
        System.out.println("Date: " + date);
        System.out.println("Service: " + service);
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.println("Medication: " + medication.name);
            System.out.println("Status: " + medication.status);
        }
    }
    // public String consultationNotes()
    // {
    //     return "Test"; //Filler
    // }
}
