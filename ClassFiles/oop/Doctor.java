package oop;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Doctor extends HospitalStaff{
    public ArrayList<Patient> patients;
    private ArrayList<Appointment> schedule; //for confirmed appointments
    private ArrayList<TimeSlot> availableSlots;
    private ArrayList<Appointment> pendingAppointments; //for pending appointments
    private String doctorID;

    public Doctor(String name, String doctorID, int age, Gender gender)
    {
        super(name, doctorID, age, gender);
        this.availableSlots = generateDefaultTimeSlots();
        this.doctorID = doctorID;
        patients = new ArrayList<>();
        schedule = new ArrayList<>();
        pendingAppointments = new ArrayList<>();
        Hospital.staffs.add(this);
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

    public String getID()
    {
        return doctorID;
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
   public void acceptAppointmentRequest()
   {
        viewPendingAppointments();
        System.out.println("Choose the appointment to accept: (enter index)");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice > pendingAppointments.size())
        {
            System.out.println("Invalid appointment number. Please try again.");
            return;
        }
        Appointment appointment = pendingAppointments.get(choice-1);
        appointment.status = StatusOfAppointment.Confirmed;
        schedule.add(appointment);
        pendingAppointments.remove(appointment);
        System.out.println("Appointment for " + appointment.date + " "+ appointment.timeSlot.start + " to " + appointment.timeSlot.end + " has been accepted.");
   }
   public void declineAppointmentRequest()
    {
        viewPendingAppointments();
        System.out.println("Choose the appointment to decline: (enter index)");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice > pendingAppointments.size())
        {
            System.out.println("Invalid appointment number. Please try again.");
            return;
        }
        Appointment appointment = pendingAppointments.get(choice-1);
        appointment.status = StatusOfAppointment.Canceled;
        //add timeslots back into doctor's available timeslots
        addTimeSlotToAvailableSlots(appointment);
        pendingAppointments.remove(appointment);
        System.out.println("Appointment for " + appointment.date + " "+ appointment.timeSlot.start + " to " + appointment.timeSlot.end + " has been declined.");
   }

   public void deleteAvailableSlots(TimeSlot timeSlot)
   {
        availableSlots.remove(timeSlot);
   }

   public void addPendingAppointment(Appointment appointment)
   {
        pendingAppointments.add(appointment);
   }

   public void deleteScheduleAppointment(Appointment appointment)
   {
        schedule.remove(appointment);
        addTimeSlotToAvailableSlots(appointment);

   }

   public void deletePendingAppointment(Appointment appointment)
   {
        pendingAppointments.remove(appointment);
        addTimeSlotToAvailableSlots(appointment);
   }

   public void addTimeSlotToAvailableSlots(Appointment appointment)
   {
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
   }

   public void viewPendingAppointments()
   {
        int i = 1;
        for (Appointment appointment : pendingAppointments)
        {
            System.out.println("---Appointment " + i + "---");
            System.out.println("Date: " + appointment.date);
            System.out.println("Start time: " + appointment.timeSlot.start);
            System.out.println("Start time: " + appointment.timeSlot.end);
            i++;
        }
   }
    public void viewUpcomingAppointments() //prints confirmed appointments
   {
        int i = 1;  
        System.out.println("Here are your upcoming appointments for the next 2 months:");  
        for (Appointment appointment : schedule)
        {
            System.out.println("--- Appointment " + i+ " ---");
            System.out.println("Date: " + appointment.date);
            System.out.println("Start Time: " + appointment.timeSlot.start);
            System.out.println("End Time: " + appointment.timeSlot.end);
            System.out.println("Patient Name: " + Hospital.getPatientNameFromPatientID(appointment.patientId));
            i++;
        }
   }
    public void recordAppointmentOutcome()
    {
        System.out.println("Please select the appointment to record the outcome (enter index)");
        viewUpcomingAppointments();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        Appointment appointment = schedule.get(choice-1); 
        appointment.status = StatusOfAppointment.Completed;//set status to completed
        System.out.println("What is the service given to the patient?");
        String service = sc.nextLine();
        // Collect prescribed medications
        List<PrescribedMedication> medications = new ArrayList<>();
        System.out.println("Please specify the medications to prescribe for the patient.");
        while (true) {
            System.out.print("Enter medication name (or 'done' to finish): ");
            String medicationName = sc.nextLine();
            if (medicationName.equalsIgnoreCase("done")) {
                break;
            }
            
            PrescribedMedication medication = new PrescribedMedication();
            medication.name = medicationName;
            medications.add(medication);
        }

        // Collect consultation notes
        System.out.println("Enter any consultation notes:");
        String consultationNotes = sc.nextLine();

        // Create an array of prescribed medications for the AppointmentOutcome
        PrescribedMedication[] prescribedMedications = medications.toArray(new PrescribedMedication[0]);
        AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.date,service,prescribedMedications,consultationNotes);
        appointment.setAppointmentOutcome(appointmentOutcome);
        System.out.println("Appointment outcome recorded successfully.");
    }
}
