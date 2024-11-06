package oop;
import java.time.LocalDate;

public class AppointmentOutcome {
    public LocalDate date;
    public String service;
    public PrescribedMedication[] prescribedMedications;
    public String consultationNotes;

    public AppointmentOutcome(LocalDate date, String service, PrescribedMedication[] prescribedMedications, String consultationNotes)
    {
        this.date = date;
        this.service = service;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    public void printAppointmentOutcomeRecord() {
        System.out.println("Date: " + date);
        System.out.println("Service: " + service);
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.println("Medication Name: " + medication.name);
            System.out.println("Medication Quantity: " + medication.numberOfUnits);
            System.out.println("Medication Status: " + medication.status);
        }
        System.out.println("Consultation Notes: " + consultationNotes);
    }
    // public String consultationNotes()
    // {
    //     return "Test"; //Filler
    // }
}
