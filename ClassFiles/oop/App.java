package oop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class App {
    public static void main(String[] args)
    {
        // TimeSlot[] timeSlots = new TimeSlot[] {
        //     new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), LocalTime.of(11, 0)),
        //     new TimeSlot(LocalDate.of(2024, 10, 20), LocalTime.of(11, 0), LocalTime.of(12, 0)),
        //     new TimeSlot(LocalDate.of(2024, 10, 21), LocalTime.of(9, 0), LocalTime.of(10, 0))
        // };
        Doctor doctor1 = new Doctor("Mike Adams", "D0001");
        Doctor doctor2 = new Doctor("Mikey Mike", "D0002");
        String[] s = new String[1]; //placeholder to create medicalHistory
        s[0] = "a";
        Hospital hospital = new Hospital();
        hospital.staff.add(doctor1);
        hospital.staff.add(doctor2);
        MedicalHistory medic = new MedicalHistory(s,s,s,s);
        Patient patient1 = new Patient("Mary Lamb", "P0001", LocalDate.of(2000, 1, 1), Gender.Female, "111", "ntu address", BloodType.ABMinus, medic, "hi@gmail.com", hospital);
        patient1.scheduleAppointment();
        patient1.viewAvailableAppointmentSlots();
        doctor1.viewPendingAppointments();
        doctor1.declineAppointmentRequest(patient1.getScheduledAppointments().get(0));
        doctor1.viewPendingAppointments();
        patient1.viewAvailableAppointmentSlots();
    }
}
