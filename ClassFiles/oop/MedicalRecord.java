package oop;
import java.time.LocalDate;

public class MedicalRecord {
    private String patientID;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    //private String address; (uncommment when necessary)
    private BloodType bloodType;
    private MedicalHistory medicalHistory;
    private String email;

    public MedicalRecord(String patientID, String name, LocalDate dateOfBirth, Gender gender, BloodType bloodType, MedicalHistory medicalHistory, String email)
    {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.email = email;
    }

    public String getPatientID()
    {
        return patientID;
    }
    public String getName()
    {
        return name;
    }
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }
    public Gender getGender()
    {
        return gender;
    }
    
   
    public BloodType getBloodType()
    {
        return bloodType;
    }
    public MedicalHistory getMedicalHistory()
    {
        return medicalHistory;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }  
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone(String phone)
    {
        this.phoneNumber = phone;
    }
}
