package oop;

import java.sql.Time;
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

public class Doctor extends HospitalStaff{
    public ArrayList<Patient> patients;
    private ArrayList<Appointment> schedule; //for confirmed appointments
    private ArrayList<TimeSlot> availableSlots;
    private ArrayList<Appointment> pendingAppointments; //for pending appointments
    private String doctorID;

    // call this "formatter" to display dates in the format "dd-MM-yyyy"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Doctor(String name, String doctorID, int age, Gender gender)
    {
        super(name, doctorID, age, gender);
        this.availableSlots = new ArrayList<>();
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
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice < 1 || choice > patients.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        Patient selectedPatient = patients.get(choice-1);
        System.out.println("< " + selectedPatient.getName() + "'s Medical Record >\n");
        selectedPatient.viewMedicalRecord();
    }
    /* 
    public void updateMedicalRecord(String diagnosis, String prescription, String treatmentPlan)
    {
        // update selected patients medical record
    }
    */
    public void viewPersonalSchedule()
    {
        // request doctor to input date, outputs the schedule for the day
        LocalDate date = getDateInput();

        // header
        System.out.println(String.format("\n<--- %s's schedule on %s --->", getName(), date.format(formatter)));
        
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

        System.out.println("\n<--- Available Timeslots: --->");
        boolean hasAvailableSlots = false;
        for (TimeSlot slot : availableSlots) {
            if (slot.getDate().equals(date)) {
                hasAvailableSlots = true;
                System.out.println(slot.getStart() + " to " + slot.getEnd());
            }
        }
        if (!hasAvailableSlots) {
            System.out.println("No available timeslots for this date.");
        }
    }
    
    public void setAvailability()
    {   
        // add timeslot in the format (date, start, end)    
        // check if timeslot exists 
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

    // option 1 of setAvailability() - add available slots
    public void addAvailableSlots() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Adding available timeslot(s)...");

        TimeSlot timeSlotInput = getTimeSlotInput();
        LocalDate date = timeSlotInput.getDate();
        LocalTime startTime = timeSlotInput.getStart();
        LocalTime endTime = timeSlotInput.getEnd();

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
            else if (!availableSlots.contains(timeSlot)) {
                availableSlots.add(timeSlot);
            } 
            else {
                System.out.println(String.format("Timeslot on %s %s to %s exists.", date.format(formatter), slotStart, slotEnd));
            }
            slotStart = slotEnd; // move to the next hour
        }

        System.out.println("Added new available timeslot(s) successfully.");

        viewAvailibility();
    }

    // option 2 of setAvailability - remove available slots
    
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
                    System.out.println("Cannot remove timeslot " + currentStart + " to " + nextHour + String.format("on %s as it is booked.", date.format(formatter)));
                }
                else {
                    System.out.println("Cannot remove timeslot " + currentStart + " to " + nextHour + String.format(" on %s as it does not exist.", date.format(formatter)));
                }
            }
            currentStart = nextHour; // move to next hour
        }
    }

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

    public LocalDate getDateInput() {
        Scanner sc = new Scanner(System.in);
        LocalDate date;

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
        while (true) {
            System.out.print("Enter the day (1 to 31): ");
            inputDay = sc.nextInt();
            try {
                date = LocalDate.of(inputYear, inputMonth, inputDay);
                if (date.isAfter(LocalDate.now())) {    // check if the date is after today
                    break;
                } else {
                    System.out.println("Invalid date. The date must be after today.");
                }
            } catch (Exception e) {
                System.out.println("Invalid day. Please enter a valid day.");
            }
        }

        return date;
    }

    public void viewAvailibility() {

        // sort the timeslots by date and time
        Collections.sort(availableSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot slot1, TimeSlot slot2) {
                int dateComparison = slot1.getDate().compareTo(slot2.getDate());
                if (dateComparison != 0) {
                    return dateComparison; // if dates are different, sort by date
                }
                return slot1.start.compareTo(slot2.start); // if dates are the same, sort by time
            }
        });

        // display the updated list of available timeslots
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots.");
            return;
        }
        System.out.println("\n" + getName() + " Available Timeslots");
        for (TimeSlot slot : availableSlots) {
            System.out.println("Date: " + slot.getDate().format(formatter) + ", Start: " + slot.start + ", End: " + slot.end);
        }
    }
    
    public TimeSlot[] getAvailability(LocalDate date) {
        List<TimeSlot> slotsForDay = new ArrayList<>();
        for (TimeSlot slot : availableSlots) {
            if (slot.getDate().equals(date)) {
                slotsForDay.add(slot);
            }
        }
        return slotsForDay.toArray(new TimeSlot[0]);  // Convert list to array
    }
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
   public void declineAppointmentRequest(int appointmentNumber)
    {
        if (pendingAppointments.isEmpty())
        {
            System.out.println("No pending appointments.");
        }

        Appointment appointment = pendingAppointments.get(appointmentNumber-1);
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
    public void viewUpcomingAppointments() //prints confirmed appointments
   {
        int i = 1;  
        if (schedule.isEmpty())
        {
            System.out.println("No scheduled appointments.");
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
    public void recordAppointmentOutcome()
    {
        try{
            Scanner sc;
            int choice;
            while (true)
            {
                System.out.println("Please select the appointment to record the outcome (enter index)");
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
        catch (Exception e)
        {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

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
                System.out.println("Please choose the appointment to accept/decline. (enter index)");
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
                System.out.println("Do you want to accept or decline this appointment? (enter '1' to accept or enter '2' to decline)");
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
        Collections.sort(patients, Comparator.comparing(Patient::getPatientID));
    }
}
