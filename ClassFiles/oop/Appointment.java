package oop;
import java.time.LocalDate;

public class Appointment {
    public LocalDate date;
    public TimeSlot timeSlot;
    public String doctorId;
    public String patientId;
    public StatusOfAppointment status = StatusOfAppointment.Pending;
    public AppointmentOutcome[] appointmentOutcome;

    public Appointment(LocalDate date, TimeSlot timeSlot, String doctorID, String patientID)
    {
        this.date = date;
        this.timeSlot = timeSlot;
        doctorId = doctorID;
        patientId = patientID;
    }
    public LocalDate getAppointmentDate()
    {
        return date;
    }
    public TimeSlot getAppointmentTimeSlot()
    {
        return timeSlot;
    }
    public String getDocID()
    {
        return doctorId;
    }
    public String patientId()
    {
        return patientId;
    }
    public StatusOfAppointment getAppointmentStatus()
    {
        return status;
    }
    //public ApppointmentOutcome getAppointmentOutcome()
    //{
    //    return appointmentOutcome; //Array
    //}
    
    public void setAppointmentDate(LocalDate Date)
    {
        this.date = Date;
    }
    public void setAppointmentTimeSlot(TimeSlot timeSlot)
    {
        this.timeSlot = timeSlot;
    }
    public void setDocID(String doctorId)
    {
        this.doctorId = doctorId;
    }
    public void setAppointmentStatus(StatusOfAppointment status)
    {
        this.status = status;
    }
    public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome)
    {
        this.appointmentOutcome[0] = appointmentOutcome; //To be fixed again
    }

}
