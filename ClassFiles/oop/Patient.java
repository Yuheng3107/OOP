package oop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import oop.UserLogic.Role;

public class Patient extends Role {
    private Hospital hospital; //patient needs to be under a certain hospital
    private MedicalRecord medicalRecord;
    private ArrayList<Appointment> scheduledAppointments;
    public ArrayList<AppointmentOutcome> appointmentOutcomes;
    public String name;
    public String patientID;

    //Temporarily remove MedHistory and Hospital to test code
    //public Patient(String name, String patientID, LocalDate dateOfBirth, Gender gender, BloodType bloodType, MedicalHistory medicalHistory, String email, Hospital hospital)
    public Patient(String name, String patientID, LocalDate dateOfBirth, Gender gender, BloodType bloodType, String email)
    {
        super(name, gender);
        this.name = name;
        this.patientID = patientID;
        //this.medicalRecord = new MedicalRecord(patientID, name, dateOfBirth, gender, bloodType, medicalHistory, email);
        //this.hospital = hospital;
        scheduledAppointments = new ArrayList<>();
        appointmentOutcomes = new ArrayList<>();
    }


    public String getName()
    {
        return name;
    }

    public void viewAvailableAppointmentSlots() //timeslots will be printed per hour
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the year to view available appointment slots:");
        int year = sc.nextInt();
        System.out.println("Type the month to view available appointment slots:");
        int month = sc.nextInt();
        System.out.println("Type the day to view available appointment slots:");
        int day = sc.nextInt();
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("Choose the doctor to view available appointment slots: (enter number)");
        hospital.namesOfDoctors();


        int choice = sc.nextInt() - 1; // subtract 1 to match array index (as patient enters 1 for first doctor)

        // ensure the choice is valid
        if (choice >= 0 && choice < hospital.numberOfDoctors()) 
        {
            Doctor chosenDoctor = hospital.getDoctorByIndex(choice); // get the selected doctor through choice
    
            // Print available time slots for the selected doctor
            TimeSlot[] availableSlots = chosenDoctor.getAvailability(date);
            System.out.println("Available slots for Dr. " + chosenDoctor.getName() + " on " + date + ":");
            for (TimeSlot slot : availableSlots)
            {
                System.out.println(slot.start + " to " + slot.end);
            }
        }
        else
        {
            System.out.println("Invalid choice.");
        }
    }

    public boolean checkForValidTimeSlot(Doctor doctor, TimeSlot timeSlot)
    {
        int count = 0;
        TimeSlot[] availableSlots = doctor.getAvailability(timeSlot.getDate());
        for (int i = 0; i < availableSlots.length; i++)
        {
            if (availableSlots[i].start == timeSlot.start)
            {
                while (timeSlot.end.isAfter(availableSlots[i].end) || timeSlot.end.equals(availableSlots[i].end))
                {
                    count++;
                    i++;
                }
                break;
            }
        }
        Duration duration = Duration.between(timeSlot.start, timeSlot.end);
        if (count == duration.toHours()) //can schedule appointment
        {
            return true;
        }
        else
        {
            System.out.println("Doctor is not available. Please select another timeslot.");
        }
        return false;
    }
    public void scheduleAppointment()
    {
        System.out.println("Which doctor do you want to schedule an appointment with? (type the number)");
        hospital.namesOfDoctors();
        Scanner sc = new Scanner(System.in);
        int choiceOfDoctor = sc.nextInt();
        Doctor doctor = hospital.getDoctorByIndex(choiceOfDoctor-1);
        if (doctor == null)
        {
            return;
        }
        System.out.println("Type the year to book appointment:");
        int year = sc.nextInt();
        System.out.println("Type the month to book appointment:");
        int month = sc.nextInt();
        System.out.println("Type the day to book appointment:");
        int day = sc.nextInt();
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("Type the start time of the appointment (24 hour clock)");
        int start = sc.nextInt();
        System.out.println("Type the end time of the appointment (24 hour clock)");
        int end = sc.nextInt();
        TimeSlot timeSlot = new TimeSlot(date, LocalTime.of(start,0), LocalTime.of(end,0));
        
        
        //check if time slot is available
        if (checkForValidTimeSlot(doctor, timeSlot) == true)
        {
            //schedule appointment
            Appointment appointment = new Appointment(timeSlot.date, timeSlot, doctor.doctorID, patientID);
            addAppointmentToScheduledAppointments(appointment);
            //remove timeslots from doctor's array of available timeslots
            LocalTime tempStart = timeSlot.start;
            while (!tempStart.isAfter(timeSlot.end.minusHours(1))) {
                TimeSlot tempSlot = new TimeSlot(timeSlot.date, tempStart, tempStart.plusHours(1));
                doctor.deleteAvailableSlots(tempSlot);
                tempStart = tempStart.plusHours(1);
            }
            doctor.addPendingAppointment(appointment);
            
        }
    }

    public void addAppointmentToScheduledAppointments(Appointment appointment)
    {
        scheduledAppointments.add(appointment);
        System.out.println("Appointment is booked successfully.");
        
    }


    public ArrayList <Appointment> getScheduledAppointments()
    {
        return scheduledAppointments;
    }
    // public void rescheduleAppointment(Appointment appointment, TimeSlot timesSlot)
    // {

    // }
    // public void cancelAppointments(Appointment appointment)
    // {

    // }
    // public void viewScheduledAppointmentStatus(Appointment appointment)
    // {

    // }
    // public void viewAppointmentOutcomeRecords()
    // {
        
    // }

    // Patient medical record management
    public void getMedicalRecord() {
        String blood = "";
        switch(medicalRecord.getBloodType().toString())
        {
            case "ABMinus":
                blood = "AB-";
                break;
            case "ABPlus":
                blood = "AB+";
                break;
            case "APlus":
                blood = "A+";
                break;
            case "AMinus":
                blood = "A-";
                break;
            case "BPlus":
                blood = "B+";
                break;
            case "BMinus":
                blood = "B-";
                break;
            case "OPLus":
                blood = "O+";
                break;
            case "OMinus":
                blood = "O-";
                break;
        }


        System.out.println("Personal Information:");
        System.out.println("Patient ID: \t" + medicalRecord.getPatientID());
        System.out.println("Name: \t\t" + medicalRecord.getName());
        System.out.println("Date of Birth: \t" + medicalRecord.getDateOfBirth());
        System.out.println("Gender: \t" + medicalRecord.getGender());
        System.out.println("Blood Type: \t" + blood + "\n");
        //System.out.println("Blood Type: \t" + medicalRecord.getBloodType() + "\n");

        System.out.println("Contact Information:");
        System.out.println("Phone number: \t" + medicalRecord.getPhoneNumber());
        System.out.println("Email Address: \t" + medicalRecord.getEmail() + "\n");

        System.out.println("Past Diagnoses: ");

        System.out.println(Arrays.toString(medicalRecord.getMedicalHistory().getPastDiagnoses()) + "\n");

        System.out.println("Past Treatments: ");
        System.out.println(Arrays.toString(medicalRecord.getMedicalHistory().getPastTreatments()) + "\n");
    }
    
}
