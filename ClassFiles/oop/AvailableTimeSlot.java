package oop;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The {@code AvailableTimeSlot} class represents a time slot during which a doctor is available for appointments.
 * It stores details about the doctor, the date of availability, the specific time slot, and the availability status.
 * 
 * <p>This class is used to track and manage doctors' availability, including the start and end times of the available
 * time slots and whether the doctor is currently available for appointments during that time.</p>
 * 
 * @author Kuang Yu Heng
 * @version 1.0
 * @since 2024-11-09
 */
public class AvailableTimeSlot {

    /**
     * The {@code AvailableTimeSlot} class represents a time slot during which a doctor is available for appointments.
     * It stores details about the doctor, the date of availability, the specific time slot, and the availability status.
     * 
     * <p>This class is used to track and manage doctors' availability, including the start and end times of the available
     * time slots and whether the doctor is currently available for appointments during that time.</p>
     * 
     * @author Ryan Ching
     * @version 1.0
     * @since 2024-11-09
     */
    private String doctorId;

    /**
     * The date of the available time slot.
     */
    private LocalDate date;

    /**
     * The time slot details, including start and end times.
     */
    public TimeSlot timeSlot;

    /**
     * Indicates whether the doctor is available for appointments during this time slot.
     */
    private boolean isAvail;

    /**
     * Constructs an {@code AvailableTimeSlot} object with the provided doctor ID, date, time slot, and availability status.
     * 
     * @param docID the ID of the doctor for this available time slot
     * @param date the date of the available time slot
     * @param timeSlot the time slot details (start and end times)
     * @param isAvail the availability status of the doctor for this time slot
     */
    public AvailableTimeSlot(String docID, LocalDate date, TimeSlot timeSlot, boolean isAvail) {
        this.doctorId = docID;
        this.date = date;
        this.timeSlot = timeSlot;
        this.isAvail = isAvail;
    }

    /**
     * Returns the ID of the doctor associated with this available time slot.
     * 
     * @return the doctor's ID
     */
    public String getDocID() {
        return doctorId;
    }

    /**
     * Returns the date of the available time slot.
     * 
     * @return the date of availability
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the availability status of the doctor for this time slot.
     * 
     * @return {@code true} if the doctor is available, {@code false} otherwise
     */
    public boolean getIsAvail() {
        return isAvail;
    }

    /**
     * Returns the start time of the available time slot.
     * 
     * @return the start time of the time slot
     */
    public LocalTime getStart() {
        return timeSlot.getStart();
    }

    /**
     * Returns the end time of the available time slot.
     * 
     * @return the end time of the time slot
     */
    public LocalTime getEnd() {
        return timeSlot.getEnd();
    }

    /**
     * Returns the {@link TimeSlot} object representing the available time slot.
     * 
     * @return the time slot associated with the availability
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
