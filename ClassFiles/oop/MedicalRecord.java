package oop;
import java.time.LocalDate;

public class MedicalRecord {
    private String patientID;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private BloodType bloodType;
    private MedicalHistory medicalHistory;
    private String email;

    public MedicalRecord(String patientID, String name, LocalDate dateOfBirth, Gender gender, String phoneNumber, String address, BloodType bloodType, MedicalHistory medicalHistory, String email)
    {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
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
