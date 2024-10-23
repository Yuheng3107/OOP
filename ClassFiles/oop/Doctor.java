package oop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Doctor extends HospitalStaff{
    public String name;
    public String doctorID;
    public int age;
    public Gender gender;
    public ArrayList<Patient> patients;
    public ArrayList<Appointment> schedule;
    private ArrayList<TimeSlot> availableSlots;
    private ArrayList<Appointment> pendingAppointments;


    public Doctor(String name, String doctorID, int age, Gender gender)
    {
        super(name, doctorID, age, gender);
        this.availableSlots = generateDefaultTimeSlots();
        patients = new ArrayList<>();
        schedule = new ArrayList<>();
        pendingAppointments = new ArrayList<>();
    }

    public ArrayList<TimeSlot> generateDefaultTimeSlots() //generates hourly time slots for the next two months (starting from the next day)
    {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plus(2, ChronoUnit.MONTHS);  // Generate slots for 2 months ahead
        // Iterate over each date from today to the end date
        for (LocalDate date = today.plusDays(1); date.isBefore(endDate); date = date.plusDays(1)) {
            // Only generate slots for weekdays
                // Create 1-hour slots from 9 AM to 5 PM (17 is exclusive, so it stops at 5 PM)
            for (int hour = 9; hour < 17; hour++) {
                LocalTime startTime = LocalTime.of(hour, 0);
                LocalTime endTime = startTime.plusHours(1);  // 1-hour slot
                TimeSlot slot = new TimeSlot(date, startTime, endTime);
                slots.add(slot);  // Add each time slot to the list
            }
        }
        // Convert the List to an array and return it
        return slots;
    }

    public String getName()
    {
        return name;
    }
    
    public void viewMedicalRecord(Patient patient)
    {
        patient.getMedicalRecord();
    }
    /*
    public void updateMedicalRecord(String diagnosis, String prescription, String treatmentPlan)
    {

    }
    public void viewPersonalSchedule()
    {

    }
    public void setAvailability(TimeSlot timesSlot)
    {

    }
    */
    public TimeSlot[] getAvailability(LocalDate date) {
        List<TimeSlot> slotsForDay = new ArrayList<>();
        for (TimeSlot slot : availableSlots) {
            if (slot.getDate().equals(date)) {
                slotsForDay.add(slot);
            }
        }
        return slotsForDay.toArray(new TimeSlot[0]);  // Convert list to array
    }
   public void acceptAppointmentRequest(Appointment appointment)
   {
        appointment.status = StatusOfAppointment.Accepted;
        schedule.add(appointment);
        pendingAppointments.remove(appointment);
   }
   public void declineAppointmentRequest(Appointment appointment)
    {
        appointment.status = StatusOfAppointment.Rejected;
        //add timeslots back into doctor's available timeslots
        for (LocalTime i = appointment.timeSlot.start; i.isBefore(appointment.timeSlot.end); i = i.plusHours(1)) {
            TimeSlot timeSlot = new TimeSlot(appointment.date, i, i.plusHours(1));  // Use 'i' for the start time
            availableSlots.add(timeSlot);
        }
        Collections.sort(availableSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot t1, TimeSlot t2) {
                return t1.start.compareTo(t2.start); // Compare the start times
            }
        });
        pendingAppointments.remove(appointment);
   }

   public void deleteAvailableSlots(TimeSlot timeSlot)
   {
        availableSlots.remove(timeSlot);
   }

   public void addPendingAppointment(Appointment appointment)
   {
        pendingAppointments.add(appointment);
   }

   public void viewPendingAppointments()
   {
        for (Appointment appointment : pendingAppointments)
        {
            System.out.println("Date: " + appointment.date);
            System.out.println("Start time: " + appointment.timeSlot.start);
            System.out.println("Start time: " + appointment.timeSlot.end);
        }
   }
    //public void viewUpcomingAppointments()
   // {

   // }
    //public void recordAppointmentOutcome(LocalDate date, String serviceType)
    //{
        
    //}
}
