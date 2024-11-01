package oop;
import java.io.*;
import java.util.*;

import oop.AdministratorLogic.Administrator;

import java.time.*;

public class App {
    public static void main(String[] args)
    {
        TimeSlot[] timeSlots = new TimeSlot[] {
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), LocalTime.of(11, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(11, 0), LocalTime.of(12, 0)),
            new TimeSlot(LocalDate.of(2024, 10, 21), LocalTime.of(9, 0), LocalTime.of(10, 0))
        };

        
        // Doctor doctor1 = new Doctor("Mike Adams", "D0001", 40, Gender.Male);
        // Doctor doctor2 = new Doctor("Mikey Mike", "D0002", 22, Gender.Female);
        String[] s = new String[1]; //placeholder to create medicalHistory
        s[0] = "a";
        // Patient patient1 = new Patient("Mary Lamb", "P0001", LocalDate.of(2000, 1, 1), Gender.Female, BloodType.ABMinus, "hi@gmail.com");
        // //Test Case 3
        // patient1.viewAvailableAppointmentSlots();
        // //Test Case 4
        // patient1.scheduleAppointment();
        // patient1.viewScheduledAppointmentStatus();
        // doctor1.viewPendingAppointments();
        // patient1.viewAvailableAppointmentSlots();
        // doctor1.acceptAppointmentRequest();
        // patient1.viewScheduledAppointmentStatus();
        // //Test Case 5
        // patient1.rescheduleAppointment();
        // patient1.viewAvailableAppointmentSlots();
        // //Test Case 6
        // patient1.scheduleAppointment();
        // patient1.viewAvailableAppointmentSlots();
        // patient1.cancelAppointment();
        // patient1.viewAvailableAppointmentSlots();
        //Test Case 7
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // patient1.viewScheduledAppointments();
        // Test Case 8
        // patient1.viewAppointmentOutcomeRecords();
        

        //Test Case 14
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // patient1.scheduleAppointment();
        // doctor1.viewPendingAppointments();
        // doctor1.acceptAppointmentRequest();
        // doctor1.acceptAppointmentRequest();
        // doctor1.viewUpcomingAppointments();
        //Test Case 15
        //patient1.scheduleAppointment();
        //doctor1.acceptAppointmentRequest();
        //doctor1.recordAppointmentOutcome();
        //patient1.viewAppointmentOutcomeRecords();

        /*
        // testing for medicalrecord for patient and doc
        Hospital hospital = new Hospital();
        String[] s = new String[1];
        s[0] = "test";
        MedicalHistory medicH = new MedicalHistory(s,s,s,s);

        Patient patient1 = new Patient("kai", "P01", LocalDate.of(2000, 1, 1), Gender.Female, BloodType.ABMinus, medicH, "kai.com", hospital);
        //patient1.getMedicalRecord();

        Doctor doctor1 = new Doctor("John", "D01", 100, Gender.Male);
        doctor1.viewMedicalRecord(patient1);*/

        Hospital hospital = new Hospital();
    }
}