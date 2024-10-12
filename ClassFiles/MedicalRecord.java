public class MedicalRecord {
    private String patientID;
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private BloodType bloodType;
    private MedicalHistory medicalHistory;
    private String email;

    public String getPatientID()
    {
        return patientID;
    }
    public String getName()
    {
        return name;
    }
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }
    public Gender getGender()
    {
        return gender;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getAddress()
    {
        return address;
    }
    public BloodType getBloodType()
    {
        return bloodType;
    }
    public MedicalHistory getMedicalHistory()
    {
        return medicalHistory;
    }
    public String getEmail()
    {
        return email;
    }

    public void setPhoneNumber(String number)
    {
        phoneNumber = number;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
}
