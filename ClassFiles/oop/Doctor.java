package oop;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Doctor in a hospital. The Doctor class extends the HospitalStaff class
 * and provides functionality for managing a doctor's schedule, viewing and updating
 * medical records of patients, and handling available time slots for appointments.
 * @author Kuang Yu Xuan, Kuang Yu Heng, Ryan Ching, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */
public class Doctor extends HospitalStaff{
    /**
     * The list of patients under the doctor's care.
     * <p>
     * This list contains all the patients currently assigned to the doctor. 
     * The doctor can access and manage this list for various purposes, including viewing medical records and updating treatment plans.
     */
    public ArrayList<Patient> patients;

    /**
     * The doctor's schedule containing confirmed appointments.
     * <p>
     * This list holds the confirmed appointments for the doctor, including all scheduled times and associated patient information.
     * It is used to track and manage all appointments that the doctor has agreed to attend.
     */
    private ArrayList<Appointment> schedule; //for confirmed appointments

    /**
     * The list of time slots that are currently available for appointments.
     * <p>
     * This list contains all the time slots that the doctor has marked as available for scheduling new appointments.
     * These time slots are used to determine when the doctor can accept new patients for consultation.
     */
    private ArrayList<TimeSlot> availableSlots;

    /**
     * The list of pending appointments that have not been confirmed yet.
     * <p>
     * This list contains appointments that have been requested but are still waiting for confirmation from the doctor.
     * It allows the doctor to review and accept or decline pending appointment requests.
     */
    private ArrayList<Appointment> pendingAppointments; //for pending appointments

    // call this "formatter" to display dates in the format "dd-MM-yyyy"
    /**
     * Formatter used to display dates in the format "dd-MM-yyyy".
     */
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructs a new Doctor object.
     * 
     * @param name the name of the doctor
     * @param doctorID the unique ID of the doctor
     * @param age the age of the doctor
     * @param gender the gender of the doctor
     */
    public Doctor(String name, String doctorID, int age, Gender gender)
    {
        super(name, doctorID, age, gender);
        this.availableSlots = generateDefaultTimeSlots();
        patients = new ArrayList<>();
        schedule = new ArrayList<>();
        pendingAppointments = new ArrayList<>();
        Hospital.staffs.add(this);
    }

    /**
     * Generates default time slots for the next two months, starting from the next day.
     * Time slots are created for weekdays from 9 AM to 5 PM.
     * 
     * @return a list of available time slots
     */
    public static ArrayList<TimeSlot> generateDefaultTimeSlots() //generates hourly time slots for the next two months (starting from the next day)
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

    /**
     * Returns the list of pending appointments for the doctor.
     * 
     * @return the list of pending appointments
     */
    public ArrayList<Appointment> getPendingAppointments() {
        return pendingAppointments;
    }
    
    /**
     * Allows the doctor to view the medical record of a patient.
     * The doctor selects a patient from the list and views their medical history.
     */
    public void viewPatientMedicalRecord()
    {
        // system outputs list of patients under their care
        // doctor selects patient to view their medical record
        getPatients();
        
        if (patients.isEmpty()) {
            System.out.println("No patients to view medical record.");
            return;
        }

        // display list of patients to select from
        int i = 1;
        System.out.println("Select a patient to view their medical record: ");
        for (Patient patient : patients) {
            System.out.println(String.format("[%d] %s", i, patient.getName()));
            i++;
        }

        try {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
    
            if (choice < 1 || choice > patients.size()) {
                System.out.println("Invalid selection. Returning to main menu.");
                return;
            }
    
            Patient selectedPatient = patients.get(choice-1);
            System.out.println("< " + selectedPatient.getName() + "'s Medical Record >\n");
            selectedPatient.viewMedicalRecord();
        }
        catch (Exception e) {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }
    
    /**
     * Allows the doctor to update the medical record of a patient.
     * The doctor can add a new diagnosis or treatment plan.
     */
    public void updateMedicalRecord()
    {
        // method to update the patient list
        List<Patient> allPatients = getAllPatients();

        if (allPatients.isEmpty()) {
            System.out.println("No patients to update medical record.");
            return;
        }

        // display list of patients to select from
        int i = 1;
        System.out.println("Select a patient to update their medical record: ");
        for (Patient patient : allPatients) {
            System.out.println(String.format("[%d] %s", i, patient.getName()));
            i++;
        }

        try {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if (choice < 1 || choice > allPatients.size()) {
                System.out.println("Invalid selection. Returning to main menu.");
                return;
            }

            Patient selectedPatient = allPatients.get(choice-1);
            System.out.println("Would you like to add new [1] diagnosis or [2] treatment plan?");
            
            choice = sc.nextInt();
            sc.nextLine(); // consume the leftover newline 

            if (choice < 1 || choice > 2) {
                System.out.println("Invalid selection. Returning to main menu.");
                return;
            }
            else if (choice == 1) {
                System.out.println("Enter new diagnosis: ");
                String inputDiagnosis = sc.nextLine();

                if (inputDiagnosis == null || inputDiagnosis.isEmpty()) {
                    System.out.println("Invalid input. Returning to main menu.");
                    return;
                }
                else {
                    ArrayList<String> patientDiagnoses = selectedPatient.getMedicalRecord().getMedicalHistory().getPastDiagnoses();
                    patientDiagnoses.add(inputDiagnosis);
                    System.out.println("Updated patient's past diagnoses.");
                }
            }
            else if (choice == 2) {
                System.out.println("Enter new treatment plan: ");
                String inputTreatment = sc.nextLine();

                if (inputTreatment == null || inputTreatment.isEmpty()) {
                    System.out.println("Invalid input. Returning to main menu.");
                    return;
                } else {
                    ArrayList<String> patientTreatments = selectedPatient.getMedicalRecord().getMedicalHistory().getPastTreatments();
                    patientTreatments.add(inputTreatment);
                    System.out.println("Updated patient's past treatment plans");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    /**
     * Allows the doctor to view their schedule for a specific day.
     * The doctor is prompted to input a date, and the system outputs the schedule for that day.
     */
    public void viewPersonalSchedule()
    {
        // request doctor to input date, outputs the schedule for the day
        LocalDate date = getDateInput();

        // header
        System.out.println(String.format("\n<--- %s's schedule on %s --->", super.getName(), date.format(formatter)));
        
        System.out.println("\n<--- Upcoming Appointments --->");
        boolean hasAppointments = false;
        int i = 1;
        
        for (Appointment appointment : schedule) {
            if (appointment.getAppointmentDate().equals(date)) {
                hasAppointments = true;
                System.out.println("--- Appointment " + i + " ---");
                System.out.println("Start Time: " + appointment.timeSlot.start);
                System.out.println("End Time: " + appointment.timeSlot.end);
                System.out.println("Patient Name: " + Hospital.getPatientNameFromPatientID(appointment.patientId));
                i++;            
            }
        }
        if (!hasAppointments) {
            System.out.println("No appointments scheduled for this date.");
        }

        System.out.println("\n<--- Available Timeslots --->");
        TimeSlot[] timeSlotsToDisplay = getAvailability(date);
        List<TimeSlot> sortedSlots = new ArrayList<>(List.of(timeSlotsToDisplay));

        Collections.sort(sortedSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot slot1, TimeSlot slot2) {
                return slot1.start.compareTo(slot2.start); // sort by time
            }
        });

        boolean hasAvailableSlots = false;
        for (TimeSlot slot : sortedSlots) {
            if (slot.getDate().equals(date)) {
                hasAvailableSlots = true;
                System.out.println(slot.getStart() + " to " + slot.getEnd());
            }
        }
        if (!hasAvailableSlots) {
            System.out.println("No available timeslots for this date.");
        }
    }
    
    /**
     * Allows the doctor to set their availability for appointments.
     * The doctor can add or remove available time slots.
     */
    public void setAvailability()
    {   
        // add timeslot in the format (date, start, end)    
        // check if timeslot exists 

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Select an option:");
            System.out.println("[1] Add available timeslot(s)");
            System.out.println("[2] Remove available timeslot(s)");
            System.out.println("[3] Cancel");
            int choice = sc.nextInt();
            if (choice == 1) {
                addAvailableSlots();
            }
            
            else if (choice == 2) {
                removeAvailableSlots();
            }
            else if (choice == 3) {
                return;
            }
            else {
                System.out.println("Invalid option. Returning to main menu.");
                return;
            }
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    // option 1 of setAvailability() - add available slots
    /**
     * Adds available timeslots to the schedule.
     * This method prompts the user to enter a time slot, checks if each hourly interval 
     * from the start time to the end time is available, and then adds the valid ones 
     * to the available slots list. It also checks if the timeslot is already booked 
     * or pending in the schedule and prevents duplicates.
     */
    public void addAvailableSlots() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Adding available timeslot(s)...");

        TimeSlot timeSlotInput = getTimeSlotInput();
        LocalDate date = timeSlotInput.getDate();
        LocalTime startTime = timeSlotInput.getStart();
        LocalTime endTime = timeSlotInput.getEnd();
        List<TimeSlot> slotsToAdd = new ArrayList<>();

        if (date == null || startTime == null || endTime == null) {
            return;
        }

        // add each hourly interval to availableSlots
        LocalTime slotStart = startTime;
        while (slotStart.isBefore(endTime)) {
            LocalTime slotEnd = slotStart.plusHours(1);
            TimeSlot timeSlot = new TimeSlot(date, slotStart, slotEnd);

            // check if the timeslot exists in the schedule/pendingAppointments (booked appointment)
            boolean isBooked = schedule.stream().anyMatch(appointment ->
                appointment.getAppointmentDate().equals(date) && 
                appointment.getAppointmentTimeSlot().equals(timeSlot)
            );

            boolean isPending = pendingAppointments.stream().anyMatch(appointment ->
                appointment.getAppointmentDate().equals(date) && 
                appointment.getAppointmentTimeSlot().equals(timeSlot)
            );

            if (isBooked || isPending) {
                System.out.println(String.format("Cannot add timeslot on %s %s to %s as it is already booked.", date.format(formatter), slotStart, slotEnd));
            }
            // if timeslot is not booked, check if in availableSlots, else add it
            else if (availableSlots.contains(timeSlot)) {
                System.out.println(String.format("Timeslot on %s %s to %s exists.", date.format(formatter), slotStart, slotEnd));
            } 
            else {
                slotsToAdd.add(timeSlot);
            }
            slotStart = slotEnd; // move to the next hour
        }

        if (slotsToAdd.isEmpty()) {
            System.out.println("No timeslots to add.");
            return;
        }
        else {
            for (TimeSlot timeSlot : slotsToAdd) {
                availableSlots.add(timeSlot);
            }
            System.out.println("Added new available timeslot(s) successfully.");
        }

        viewAvailability(date);
    }

    // option 2 of setAvailability - remove available slots
    /**
     * Removes available timeslots from the schedule.
     * This method prompts the user to enter a time slot, checks if each hourly interval 
     * from the start time to the end time exists in the available slots list, and removes 
     * the valid ones. It also checks if any of the timeslots are booked or pending, 
     * and prevents removal in those cases.
     */ 
    
    public void removeAvailableSlots() {
        
        // remove timeslot (check if availableSlots is empty)
        // cannot remove timeslot if appointment is booked for that timeslot

        if (availableSlots.isEmpty()) {
            System.out.println("No available slots to remove.");
            return;
        }

        TimeSlot timeSlotInput = getTimeSlotInput();
        LocalDate date = timeSlotInput.getDate();
        LocalTime startTime = timeSlotInput.getStart();
        LocalTime endTime = timeSlotInput.getEnd();

        if (date == null || startTime == null || endTime == null) {
            return;
        }

        LocalTime currentStart = startTime;
        while (currentStart.isBefore(endTime)) {
            LocalTime nextHour = currentStart.plusHours(1);
            TimeSlot slotToRemove = new TimeSlot(date, currentStart, nextHour);

            // if slot is in availableSlots, remove
            if (availableSlots.contains(slotToRemove)) {
                availableSlots.remove(slotToRemove);
                System.out.println("Removed available timeslot " + currentStart + " to " + nextHour + 
                String.format(" on %s.", date.format(formatter)));
            }
            // if not, check if it is booked or else if does not exist in availableSlots
            else {
                boolean isBooked = schedule.stream().anyMatch(appointment ->
                    appointment.getAppointmentDate().equals(date) && 
                    appointment.getAppointmentTimeSlot().equals(slotToRemove)
                );

                boolean isPending = pendingAppointments.stream().anyMatch(appointment->
                    appointment.getAppointmentDate().equals(date) && 
                    appointment.getAppointmentTimeSlot().equals(slotToRemove)
                );

                if (isBooked || isPending) {
                    System.out.println("Cannot remove timeslot " + currentStart + " to " + nextHour + String.format(" on %s as it is booked.", date.format(formatter)));
                }
                else {
                    System.out.println("Cannot remove timeslot " + currentStart + " to " + nextHour + String.format(" on %s as it does not exist.", date.format(formatter)));
                }
            }
            currentStart = nextHour; // move to next hour

        }
    }

    /**
     * Prompts the user to input a time slot consisting of a date, start time, 
     * and end time. The method performs input validation for each field to ensure 
     * that the entered values are within a valid range (e.g., the date is in the future, 
     * the start and end times are within business hours).
     * 
     * @return A {@link TimeSlot} object with the user-provided date, start, and end time.
     */
    public TimeSlot getTimeSlotInput() {

        Scanner sc = new Scanner(System.in);

        int inputYear;
        while (true) {
            System.out.print("Enter the year (e.g. 2024): ");
            inputYear = sc.nextInt();
            if (inputYear >= 2024) {
                break;
            }
            System.out.println("Invalid year. Please enter a year from 2024 onwards.");
        }

        int inputMonth;
        while (true) {
            System.out.print("Enter the month (1 to 12): ");
            inputMonth = sc.nextInt();
            if (inputMonth >= 1 && inputMonth <= 12) {
                break;
            }
            System.out.println("Invalid month. Please enter a number between 1 to 12.");
        }

        int inputDay;
        LocalDate date;
        while (true) {
            System.out.print("Enter the day (1 to 31): ");
            inputDay = sc.nextInt();
            try {
                date = LocalDate.of(inputYear, inputMonth, inputDay);
                if (date.isAfter(LocalDate.now())) {    // check if the date is after today
                    break;
                } else {
                    System.out.println("Invalid date. The date must be after today.");
                    return new TimeSlot(null,null,null);
                }
            } catch (Exception e) {
                System.out.println("Invalid day. Please enter a valid day.");
            }
        }

        int inputStartTime, startHour, startMinutes;
        LocalTime startTime;
        while(true) {
            System.out.print("Enter the start time in 24hr format (e.g. 0900): ");
            inputStartTime = sc.nextInt();
            startHour = inputStartTime/100;
            startMinutes = inputStartTime%100;
            if (startHour >= 9 && startHour < 17 && startMinutes >= 0 && startMinutes < 60) {
                startTime = LocalTime.of(startHour,startMinutes);
                break;
            }
            System.out.println("Invalid start time. The start time must be between 0900 and 1700.");
        }

        int inputEndTime, endHour, endMinutes;
        LocalTime endTime;
        while (true) {
            System.out.print("Enter the end time in 24hr format (e.g. 1700): ");
            inputEndTime = sc.nextInt();
            endHour = inputEndTime/100;
            endMinutes = inputEndTime%100;

            try {
                endTime = LocalTime.of(endHour,endMinutes);

                if (endMinutes != 0) {
                    System.out.println("Invalid end time. The end time must be on the hour (e.g. 1300, 1400).");
                } else if (endHour <= startHour) {
                    System.out.println("Invalid end time. The end time must be after the start time.");
                } else if (endHour < 9 || endHour > 17) {
                    System.out.println("Invalid end time. The end time must be between 0900 and 1700.");
                } else if (Duration.between(startTime, endTime).toHours() < 1) {
                    System.out.println("Invalid end time. The end time must be at least one hour after the start time.");
                } else {
                    break; // All checks passed, exit the loop
                }
            } catch (Exception e) {
                System.out.println("Invalid end time. Please enter a valid time in 24-hour format (e.g., 1300, 1400).");
            }
        }
        
        return new TimeSlot(date, startTime, endTime);
    }

    /**
     * Prompts the user to input a date, ensuring the date is valid and in the future.
     * The user is asked to input the year, month, and day, with validation for each.
     * The method returns a valid {@link LocalDate} object if the input is correct.
     * 
     * @return A valid {@link LocalDate} object representing the user-provided date.
     */
    public LocalDate getDateInput() {
        Scanner sc = new Scanner(System.in);
        LocalDate date;

        int inputYear;
        while (true) {
            System.out.print("Enter the year (e.g. 2024): ");
            if (sc.hasNextInt()) {
                inputYear = sc.nextInt();
                if (inputYear >= 2024) {
                    break;
                }
                System.out.println("Invalid year. Please enter a year from 2024 onwards.");
            }
            else {
                System.out.println("Invalid input. Please enter a valid year.");
                sc.next();
            }

        }

        int inputMonth;
        while (true) {
            System.out.print("Enter the month (1 to 12): ");
            if (sc.hasNextInt()) {
                inputMonth = sc.nextInt();
                if (inputMonth >= 1 && inputMonth <= 12) {
                    break;
                }
                System.out.println("Invalid month. Please enter a number between 1 to 12.");
            }
            else {
                System.out.println("Invalid input. Please enter a valid month.");
                sc.next();
            }

        }

        int inputDay;
        while (true) {
            System.out.print("Enter the day (1 to 31): ");
            if (sc.hasNextInt()) {
                inputDay = sc.nextInt();
                try {
                    date = LocalDate.of(inputYear, inputMonth, inputDay);
                    if (date.isAfter(LocalDate.now())) {    // check if the date is after today
                        return date;
                    } else {
                        System.out.println("Invalid date. The date must be after today.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid day. Please enter a valid day.");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a valid day.");
                sc.next();            
            }
        }
    }

    /**
     * Displays the available time slots for a specific date.
     * This method retrieves the available time slots for the given date and 
     * sorts them by the start time. If no slots are available, it notifies the user.
     * 
     * @param date The date for which the available slots are to be viewed.
     */
    public void viewAvailability(LocalDate date) {

        TimeSlot[] timeSlotsToDisplay = getAvailability(date);
        List<TimeSlot> sortedSlots = new ArrayList<>(List.of(timeSlotsToDisplay));

        Collections.sort(sortedSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot slot1, TimeSlot slot2) {
                return slot1.start.compareTo(slot2.start); // sort by time
            }
        });

        // display the updated list of available timeslots
        if (sortedSlots.isEmpty()) {
            System.out.println("No available slots on " + date.format(formatter) + ".");
            return;
        }
        System.out.println("\n" + super.getName() + "'s Available slots on " + date.format(formatter));
        for (TimeSlot slot : sortedSlots) {
            System.out.println(slot.start + " to " + slot.end);
        }
    }

    /**
     * Retrieves the available time slots for a specific date and doctor.
     * This method checks the available time slots for the given doctor on the 
     * specified date and returns them as an array of {@link TimeSlot} objects.
     * 
     * @param date The date for which the availability is requested.
     * @return An array of {@link TimeSlot} objects representing the available slots.
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
    
    /**
     * Accepts an appointment request by changing the status to "Confirmed" and moves it from the 
     * pending appointments list to the confirmed schedule.
     *
     * @param appointmentNumber The appointment number (index in pendingAppointments list) to accept.
     */
    public void acceptAppointmentRequest(int appointmentNumber)
    {
        if (pendingAppointments.isEmpty())
        {
            System.out.println("No pending appointments.");
            return;
        }
        
        Appointment appointment = pendingAppointments.get(appointmentNumber-1);
        appointment.status = StatusOfAppointment.Confirmed;
        schedule.add(appointment);
        pendingAppointments.remove(appointment);
        Hospital.appointments.add(appointment);
        System.out.println("Appointment for " + appointment.date + " "+ appointment.timeSlot.start + " to " + appointment.timeSlot.end + " has been accepted.");  
   }

   /**
     * Declines an appointment request by changing the status to "Cancelled", removes it from the 
     * pending appointments list, and adds its time slot back to the available slots.
     *
     * @param appointmentNumber The appointment number (index in pendingAppointments list) to decline.
     */
   public void declineAppointmentRequest(int appointmentNumber)
    {
        if (pendingAppointments.isEmpty())
        {
            System.out.println("No pending appointments.");
        }

        Appointment appointment = pendingAppointments.get(appointmentNumber-1);
        appointment.status = StatusOfAppointment.Cancelled;
        //add timeslots back into doctor's available timeslots
        addTimeSlotToAvailableSlots(appointment);
        pendingAppointments.remove(appointment);
        System.out.println("Appointment for " + appointment.date + " "+ appointment.timeSlot.start + " to " + appointment.timeSlot.end + " has been declined.");
   }

   /**
     * Removes the given time slot from the available time slots.
     *
     * @param timeSlot The time slot to be deleted from the available slots.
     */
   public void deleteAvailableSlots(TimeSlot timeSlot)
   {
        availableSlots.remove(timeSlot);
   }

   /**
     * Adds an appointment to the pending appointments list.
     *
     * @param appointment The appointment to add to the pending appointments.
     */
   public void addPendingAppointment(Appointment appointment)
   {
        pendingAppointments.add(appointment);
   }

   /**
     * Removes an appointment from the confirmed schedule and adds its time slot back to available slots.
     *
     * @param appointment The appointment to remove from the schedule.
     */
   public void deleteScheduleAppointment(Appointment appointment)
   {
        schedule.remove(appointment);
        addTimeSlotToAvailableSlots(appointment);

   }

   /**
     * Removes an appointment from the pending appointments list and adds its time slot back to available slots.
     *
     * @param appointment The appointment to remove from the pending appointments.
     */
   public void deletePendingAppointment(Appointment appointment)
   {
        pendingAppointments.remove(appointment);
        addTimeSlotToAvailableSlots(appointment);
   }

   /**
     * Adds the time slot of the given appointment back to the list of available time slots and sorts the available slots.
     *
     * @param appointment The appointment whose time slots are to be added back to the available slots.
     */
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

   /**
     * Displays the list of pending appointments with their details (e.g., date, start time, and end time).
     */
   public void viewPendingAppointments()
   {
        int i = 1;
        if (pendingAppointments.isEmpty())
        {
            return;
        }
        for (Appointment appointment : pendingAppointments)
        {
            System.out.println("---Appointment " + i + "---");
            System.out.println("Date: " + appointment.date);
            System.out.println("Start time: " + appointment.timeSlot.start);
            System.out.println("End time: " + appointment.timeSlot.end);
            i++;
        }
   }

   /**
     * Displays the upcoming confirmed appointments (for the next 2 months) with their details.
     */
    public void viewUpcomingAppointments() //prints confirmed appointments
   {
        int i = 1;  
        if (schedule.isEmpty())
        {
            System.out.println("No scheduled appointments.");
            return;
        }
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

   /**
     * Records the outcome of an appointment, including the service provided, medications prescribed, and consultation notes.
     * It also updates the appointment status to "Completed".
     */
    public void recordAppointmentOutcome()
    {
        
        if (schedule.isEmpty())
        {
            System.out.println("No scheduled appointments to record appoinment outcome.");
            return;
        }
        try{
            Scanner sc;
            int choice;
            while (true)
            {
                System.out.println("Please select the appointment to record the outcome (enter index):");
                viewUpcomingAppointments();
                sc = new Scanner(System.in);
                choice = sc.nextInt();
                sc.nextLine();
                if (choice <= 0 || choice > schedule.size())
                {
                    System.out.println("Invalid appointment number. Please try again.");
                }
                else{
                    break;
                }
            }
            Appointment appointment = schedule.get(choice-1); 
            appointment.status = StatusOfAppointment.Completed;//set status to completed
            System.out.println("What is the service given to the patient?");
            String service = sc.nextLine();
            // Collect prescribed medications
            List<PrescribedMedication> medications = new ArrayList<>();
            System.out.println("Please specify the medications to prescribe for the patient.");
            while (true)
            {
                System.out.print("Enter medication name (or 'done' to finish): ");
                String medicationName = sc.nextLine();
                if (medicationName.equalsIgnoreCase("done"))
                {
                    break;
                }
                int numberOfUnits;
                boolean validUnits = false;
                while (!validUnits)
                {
                    System.out.print("Enter the number of units of " + medicationName + " to give to the patient (or -1 to return): ");
                    numberOfUnits = Integer.parseInt(sc.nextLine());
                    if (numberOfUnits == -1)
                    {
                        break;
                    }
                    if (numberOfUnits > 0)
                    {
                        boolean inStock = false;
                        for (MedicineStock med : Hospital.inventory)
                        {
                            if ((med.getName().equalsIgnoreCase(medicationName)) &&(numberOfUnits <= (med.getStock()-med.getRollingStock())))
                            {
                                inStock = true;
                                med.setRollingStock(med.getRollingStock() + numberOfUnits);
                                break;
                            }
                        }
                        if (inStock)
                        {
                            validUnits = true;
                            // Create and add the prescribed medication after successful validation
                            PrescribedMedication medication = new PrescribedMedication(medicationName, numberOfUnits);
                            medications.add(medication);
                        }
                        else
                        {
                            System.out.println("Error! Current inventory does not sufficient amount to dispense for the new patient. Please inform the pharmacist to submit a replenishment request or enter a lower value!");
                        }
                    }
                    else
                    {
                        System.out.println("Invalid input! Please try again!");
                    }
                }
            }

            // Collect consultation notes
            System.out.print("Enter any consultation notes: ");
            String consultationNotes = sc.nextLine();

            // Create an array of prescribed medications for the AppointmentOutcome
            PrescribedMedication[] prescribedMedications = medications.toArray(new PrescribedMedication[0]);
            AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.date,service,prescribedMedications,consultationNotes);
            appointment.setAppointmentOutcome(appointmentOutcome);
            System.out.println("Appointment outcome recorded successfully.");
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    /**
     * Allows the doctor to accept or decline a pending appointment request. It displays the list of pending appointments and
     * prompts the user for their choice.
     */
    public void acceptOrDeclineAppointmentRequest()
    {        
        if (pendingAppointments.isEmpty())
        {
            System.out.println("No pending appointments.");
            return;
        }
        try{
            viewPendingAppointments();
            Scanner sc = new Scanner(System.in);
            int docChoice;
            while (true)
            {
                System.out.print("Please choose the appointment to accept/decline. (enter index): ");
                docChoice = Integer.parseInt(sc.nextLine());
                if (docChoice <= 0 || docChoice > pendingAppointments.size())
                {
                    System.out.println("Invalid appointment number. Please try again.");
                }
                else
                {
                    break;
                }
            }
            while (true)
            {
                System.out.print("Do you want to accept or decline this appointment? (enter '1' to accept or enter '2' to decline): ");
                int acceptOrDecline = Integer.parseInt(sc.nextLine());
                if (acceptOrDecline == 1)
                {
                    acceptAppointmentRequest(docChoice);
                    break;
                }
                else if (acceptOrDecline == 2)
                {
                    declineAppointmentRequest(docChoice);
                    break;
                }
                else
                {
                    System.out.println("Invalid input! Please try again.");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    /**
     * Retrieves all patients associated with the doctor's confirmed and pending appointments, removing duplicates and 
     * sorting them by patient ID.
     */
    public void getPatients() {
        
        // clear the patient's list to avoid duplicated patient data
        patients.clear();

        for (Appointment appointment : schedule) {
            Patient patient = Hospital.getPatientFromPatientID(appointment.getPatientId());
            if (patient != null && !patients.contains(patient)) {
                patients.add(patient);
            }
        }

        for (Appointment appointment : pendingAppointments) {
            Patient patient = Hospital.getPatientFromPatientID(appointment.getPatientId());
            if (patient != null && !patients.contains(patient)) {
                patients.add(patient);
            }
        }

        // sort the patients list array by patientID
        Collections.sort(patients, Comparator.comparing(Patient::getID));
    }

    /**
     * Returns a list of all patients in the hospital's database.
     *
     * @return A list of all patients.
     */
    public ArrayList<Patient> getAllPatients() {
        return new ArrayList<>(Hospital.patients);
    }

    /**
     * Checks for pending appointment requests and displays an alert if any are present.
     */
    public void checkPendingAppointmentsAlert() {
        if (!getPendingAppointments().isEmpty()) {

            int countPendingAppointments = getPendingAppointments().size();

            if (countPendingAppointments == 1) {
                System.out.println("System alert: You have 1 appointment request pending.");
            }
            else {
                System.out.println(String.format("System alert: You have %d appointment requests pending.", countPendingAppointments));
            }
        }
    }
}
