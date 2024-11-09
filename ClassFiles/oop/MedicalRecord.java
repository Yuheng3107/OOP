package oop;
import java.time.LocalDate;

/**
 * The {@code MedicalRecord} class represents the medical record of a patient.
 * It contains personal and medical information such as the patient's ID, name, date of birth,
 * gender, blood type, medical history, and contact details.
 * This class provides methods to access and modify the patient's contact details and retrieve
 * their medical history.
 * 
 * <p>The {@code MedicalRecord} class is used to manage the patient's overall record in the system,
 * combining both personal details and health-related information.</p>
 * 
 * @author Kuang Yu Xuan, Kuang Yu Heng, Ryan Ching, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */
public class MedicalRecord {
    /**
     * The unique identifier for the patient.
     */
    private String patientID;

    /**
     * The name of the patient.
     */
    private String name;

    /**
     * The date of birth of the patient.
     */
    private LocalDate dateOfBirth;

    /**
     * The gender of the patient.
     */
    private Gender gender;

    /**
     * The phone number of the patient.
     */
    private String phoneNumber;
    //private String address; (uncommment when necessary)

    /**
     * The blood type of the patient.
     */
    private BloodType bloodType;

    /**
     * The medical history associated with the patient.
     */
    private MedicalHistory medicalHistory;

    /**
     * The email address of the patient.
     */
    private String email;

    /**
     * Constructs a new {@code MedicalRecord} object with the provided details.
     * 
     * @param patientID the unique identifier for the patient
     * @param name the name of the patient
     * @param dateOfBirth the date of birth of the patient
     * @param gender the gender of the patient
     * @param bloodType the blood type of the patient
     * @param medicalHistory the medical history of the patient
     * @param email the email address of the patient
     */
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

    /**
     * Retrieves the unique identifier for the patient.
     *
     * @return the patient ID
     */
    public String getPatientID()
    {
        return patientID;
    }

    /**
     * Retrieves the name of the patient.
     *
     * @return the patient's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the date of birth of the patient.
     *
     * @return the date of birth of the patient
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Retrieves the gender of the patient.
     *
     * @return the gender of the patient
     */
    public Gender getGender()
    {
        return gender;
    }
    
   /**
     * Retrieves the blood type of the patient.
     *
     * @return the blood type of the patient
     */
    public BloodType getBloodType()
    {
        return bloodType;
    }

    /**
     * Retrieves the medical history of the patient.
     *
     * @return the patient's medical history
     */
    public MedicalHistory getMedicalHistory()
    {
        return medicalHistory;
    }

    /**
     * Retrieves the phone number of the patient.
     *
     * @return the patient's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the email address of the patient.
     *
     * @return the patient's email address
     */
    public String getEmail()
    {
        return email;
    }  

    /**
     * Updates the email address of the patient.
     *
     * @param email the new email address to be set for the patient
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Updates the phone number of the patient.
     *
     * @param phone the new phone number to be set for the patient
     */
    public void setPhone(String phone)
    {
        this.phoneNumber = phone;
    }
}
