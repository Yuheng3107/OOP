package oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import oop.UserLogic.Role;

public class Patient extends Role {
    private Hospital hospital; //patient needs to be under a certain hospital
    private MedicalRecord medicalRecord;
    private ArrayList<Appointment> scheduledAppointments; //includes all appointments of patient with status Pending or Accepted
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
        this.medicalRecord = new MedicalRecord(patientID, name, dateOfBirth, gender, bloodType, null, email);
        //this.hospital = hospital;
        scheduledAppointments = new ArrayList<>();
        appointmentOutcomes = new ArrayList<>();
        Hospital.patients.add(this);
    }

    public String getName()
    {
        return name;
    }

    public String getPatientID()
    {
        return patientID;
    }

    public void updatePersonalInformation(String userID) {
        Scanner sc = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        String line, filePath = "Patient_List.csv";
        boolean isUpdated = false;

        System.out.print("Please enter your new email address for " + userID + ": ");
        String newEmail = sc.nextLine();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns[0].equals(userID)) {
                    columns[5] = newEmail;
                    line = String.join(",", columns);
                    isUpdated = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
                System.out.println("Details updated successfully for ID: " + userID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error!");
        }
    }

    public void viewAvailableAppointmentSlots() //timeslots will be printed per hour
    {
        int year;
        int month;
        int day;
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Type the year to view available appointment slots:");
            year = sc.nextInt();
            System.out.println("Type the month to view available appointment slots:");
            month = sc.nextInt();
            System.out.println("Type the day to view available appointment slots:");
            day = sc.nextInt();
            LocalDate date = LocalDate.of(year, month, day);
            
    
            // ensure the choice is valid
            while (true)
            {
                System.out.println("Choose the doctor to view available appointment slots: (enter number)");
                Hospital.namesOfDoctors();
    
                int choice = sc.nextInt() - 1; // subtract 1 to match array index (as patient enters 1 for first doctor)
                if (choice >= 0 && choice < Hospital.numberOfDoctors()) 
                {
                    Doctor chosenDoctor = Hospital.getDoctorByIndex(choice); // get the selected doctor through choice
            
                    // Print available time slots for the selected doctor
                    TimeSlot[] availableSlots = chosenDoctor.getAvailability(date);
                    System.out.println("Available slots for Dr. " + chosenDoctor.getName() + " on " + date + ":");
                    for (TimeSlot slot : availableSlots)
                    {
                        System.out.println(slot.start + " to " + slot.end);
                    }
                    return;
                }
                else
                {
                    System.out.println("Invalid choice of doctor. Please try again.");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
            return;
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
        Doctor doctor = null;

        try
        {
            Scanner sc;
            while (true)
            {
                System.out.println("Which doctor do you want to schedule an appointment with? (enter index)");
                Hospital.namesOfDoctors();
                sc = new Scanner(System.in);
                System.out.print("Choice: ");
                int choiceOfDoctor = sc.nextInt();
                doctor = Hospital.getDoctorByIndex(choiceOfDoctor-1);
                if (doctor == null)
                {
                    continue;
                }
                else{
                    break;
                }
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
                Appointment appointment = new Appointment(timeSlot.date, timeSlot, doctor.getStaffID(), patientID);
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
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
            return;
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


    public void rescheduleAppointment()
    {
        int check = viewScheduledAppointments();
        if (check == -1)
        {
            return;
        }
        Scanner sc = new Scanner(System.in);
        
        try {
            int index;
            while (true)
            {
                System.out.println("Please choose the appointment to reschedule: (enter index)");
                index = sc.nextInt();
                if (index > scheduledAppointments.size())
                {
                    System.out.println("Invalid appointment number. Please try again.");
                    return;
                }
            }
            Appointment appointment = scheduledAppointments.get(index-1);
            Doctor doctor = Hospital.getDoctorObjectByStaffID(scheduledAppointments.get(index-1).doctorId);//remove from doctor's appointments
            if (appointment.status == StatusOfAppointment.Pending)
            {
                doctor.deletePendingAppointment(appointment);
            }
            else
            {
                doctor.deleteScheduleAppointment(appointment);
            }
            scheduledAppointments.remove(index-1); //remove from patient's appointments
            scheduleAppointment();
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
        }   


    }
    public void cancelAppointment()
    {
        int check = viewScheduledAppointments();
        if (check == -1)
        {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose the appointment to cancel: (enter index)");
        int index = sc.nextInt();
        if (index > scheduledAppointments.size())
        {
            System.out.println("Invalid appointment number. Please try again.");
            return;
        }
        Appointment appointment = scheduledAppointments.get(index-1);
        Doctor doctor = Hospital.getDoctorObjectByStaffID(scheduledAppointments.get(index-1).doctorId);//remove from doctor's appointments
        if (appointment.status == StatusOfAppointment.Pending)
        {
            doctor.deletePendingAppointment(appointment);
        }
        else
        {
            doctor.deleteScheduleAppointment(appointment);
        }
        scheduledAppointments.remove(index-1);
        System.out.println("Appointment cancelled successfully.");
    }

    public void viewScheduledAppointmentStatus()
    {
        int i = 1;
        if (scheduledAppointments.isEmpty())
        {
            System.out.println("No scheduled appointments.");
        }
        for (Appointment appointment : scheduledAppointments)
        {
            System.out.println("--- Appointment " + i + " ---");
            System.out.println("Doctor: " + Hospital.getDoctorNameByStaffID(appointment.doctorId));
            System.out.println("Date: " + String.valueOf(appointment.date));
            System.out.println("Start Time: " + String.valueOf(appointment.timeSlot.start));
            System.out.println("End Time: " + String.valueOf(appointment.timeSlot.end));
            i++;
        }
        System.out.println("Choose the appointment to view status: (enter index)");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("The status of this appointment is: "+scheduledAppointments.get(choice-1).status);
    }

    public void viewAppointmentOutcomeRecords()
    {
        for (AppointmentOutcome outcome : appointmentOutcomes)
        {
            outcome.printAppointmentOutcomeRecord();
        }
    }

    public int viewScheduledAppointments(){
        if (scheduledAppointments.isEmpty())
        {
            System.out.println("No scheduled appointments.");
            return -1;
        }
        System.out.println("Here are your scheduled appointments:");
        int i = 1;
        for (Appointment appointment : scheduledAppointments)
        {
            System.out.println("--- Appointment " + i + " ---");
            appointment.viewAppointment();
            i++;
        }
        return 0;
        
    }

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

        System.out.println("Contact Information:");
        System.out.println("Phone number: \t" + medicalRecord.getPhoneNumber());
        System.out.println("Email Address: \t" + medicalRecord.getEmail() + "\n");

        System.out.println("Past Diagnoses: ");

        System.out.println(Arrays.toString(medicalRecord.getMedicalHistory().getPastDiagnoses()) + "\n");

        System.out.println("Past Treatments: ");
        System.out.println(Arrays.toString(medicalRecord.getMedicalHistory().getPastTreatments()) + "\n");
    }
    
}
