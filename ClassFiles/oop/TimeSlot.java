package oop;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The {@code TimeSlot} class represents a time interval within a specific date, defined by a start time and an end time.
 * It is used to manage and compare time slots, typically for scheduling purposes such as appointments, meetings, or events.
 * 
 * <p>The class provides methods to access the date, start time, and end time, as well as overrides for {@link #equals(Object)} 
 * and {@link #hashCode()} to allow comparing and storing instances of {@code TimeSlot} in collections.</p>
 * @author Kuang Yu Xuan, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */ 
public class TimeSlot {
    /**
     * The start time of the time slot.
     */
    public LocalTime start;

    /**
     * The end time of the time slot.
     */
    public LocalTime end;

    /**
     * The date associated with the time slot.
     */
    public LocalDate date;

    /**
     * Constructs a new {@code TimeSlot} with the specified date, start time, and end time.
     *
     * @param date The date of the time slot.
     * @param start The start time of the time slot.
     * @param end The end time of the time slot.
     */
    public TimeSlot(LocalDate date, LocalTime start, LocalTime end) 
    {
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the date of the time slot.
     *
     * @return The date of the time slot.
     */
    public LocalDate getDate() 
    {
        return date;
    }

    /**
     * Returns the start time of the time slot.
     *
     * @return The start time of the time slot.
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Returns the end time of the time slot.
     *
     * @return The end time of the time slot.
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Compares the specified object with this {@code TimeSlot} for equality. Two time slots are considered equal if 
     * their date, start time, and end time are the same.
     * 
     * @param o The object to be compared for equality with this time slot.
     * @return {@code true} if the specified object is equal to this time slot; {@code false} otherwise.
     */
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return date.equals(timeSlot.date) &&
               start.equals(timeSlot.start) &&
               end.equals(timeSlot.end);
    }

    /**
     * Returns a hash code value for this {@code TimeSlot}. The hash code is computed based on the date, start time, 
     * and end time of the time slot.
     *
     * @return A hash code value for this time slot.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date, start, end);
    }
}
