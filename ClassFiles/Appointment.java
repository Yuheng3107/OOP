public class Appointment {
    public Date date;
    public TimeSlot timeSlot;
    public String doctorId;
    public String patientId;
    public String status;
    public AppointmentOutcome[] appointmentOutcome;

    public Appointment(Date date, TimeSlot timeSlot, Doctor doctor, Patient patient)
    {
        this.date = date;
        this.timeSlot = timeSlot;
        doctorId = doctor;
        patientId = patient;
    }
    public Date getAppointmentDate()
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
    public String getAppointmentStatus()
    {
        return status;
    }
    public ApppointmentOutcome getAppointmentOutcome()
    {
        return appointmentOutcome; //Array
    }
    
    public void setAppointmentDate(Date Date)
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
    public void setAppointmentStatus(String status)
    {
        this.status = status;
    }
    public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome)
    {
        this.appointmentOutcome = appointmentOutcome;
    }
}
