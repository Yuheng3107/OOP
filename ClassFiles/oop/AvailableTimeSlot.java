package oop;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvailableTimeSlot {
    public String doctorId;
    public LocalDate date;
    public TimeSlot timeSlot;
    public boolean isAvail;

    public AvailableTimeSlot(String docID, LocalDate date, TimeSlot timeSlot, boolean isAvail) {
        this.doctorId = docID;
        this.date = date;
        this.timeSlot = timeSlot;
        this.isAvail = isAvail;
    }

    public String getDocID() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getIsAvail() {
        return isAvail;
    }

    public LocalTime getStart() {
        return timeSlot.getStart();
    }

    public LocalTime getEnd() {
        return timeSlot.getEnd();
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
