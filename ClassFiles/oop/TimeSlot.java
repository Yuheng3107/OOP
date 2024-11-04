package oop;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TimeSlot {
    public LocalTime start;
    public LocalTime end;
    public LocalDate date;

    public TimeSlot(LocalDate date, LocalTime start, LocalTime end) 
    {
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public LocalDate getDate() 
    {
        return date;
    }
    public LocalTime getStart() {
        return start;
    }
    public LocalTime getEnd() {
        return end;
    }
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return date.equals(timeSlot.date) &&
               start.equals(timeSlot.start) &&
               end.equals(timeSlot.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start, end);
    }
}
