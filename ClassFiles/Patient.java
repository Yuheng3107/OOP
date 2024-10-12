public class Patient extends HospitalStaff {
    private MedicalRecord medicalRecord;
    private Appointment[] scheduledAppointments;
    public AppointmentOutcome[] appointmentOutcomes;

    public TimeSlot[] viewAvailableAppointmentSlots()
    {

    }
    public void scheduledAppointment(Patient patient, Doctor doctor, Date date, TimeSlot timesSlot)
    {

    }
    public void rescheduleAppointment(Appointment appointment, TimeSlot timesSlot)
    {

    }
    public void cancelAppointments(Appointment appointment)
    {

    }
    public void viewScheduledAppointmentStatus(Appointment appointment)
    {

    }
    public void viewAppointmentOutcomeRecords()
    {
        
    }
    public Appointment[] getScheduledAppointments()
    {
        return Appointment[0]; //Filler to escape error in IDE
    }
    public MedicalRecord getMedicalRecord()
    {
        return MedicalRecord; //Filler to escape error in IDE
    }
    public void logout()
    {
        System.out.println("Bye bye");
    }
}
