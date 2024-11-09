package oop;

import java.util.ArrayList;
/**
 * The {@code MedicalHistory} class holds information about a patient's past medical history.
 * This includes past medications, allergies, diagnoses, and treatments.
 * It allows for the management and retrieval of these details, which are crucial for providing
 * accurate and safe medical care.
 * 
 * <p>This class provides lists to store the past medications, allergies, diagnoses, and treatments
 * that a patient has encountered in their medical history.</p>
 * 
 * @author Kuang Yu Xuan, Tan Zhe Kai
 * @version 1.0
 * @since 2024-11-09
 */
public class MedicalHistory {
    /**
     * A list that stores the names of medications the patient has previously taken.
     */
    private ArrayList<String> pastMedications;
    
    /**
     * A list that stores the allergies the patient has.
     */
    private ArrayList<String> allergies;

    /**
     * A list that stores the past diagnoses the patient has received.
     */
    private ArrayList<String> pastDiagnoses;

    /**
     * A list that stores the treatments the patient has previously undergone.
     */
    private ArrayList<String> pastTreatments;

    /**
     * Constructs a new {@code MedicalHistory} object with empty lists for medications, allergies, diagnoses, and treatments.
     */
    public MedicalHistory()
    {
        this.pastMedications = new ArrayList<>();
        this.allergies = new ArrayList<>(); // do we need pastMedications & allergies?
        this.pastDiagnoses = new ArrayList<>();
        this.pastTreatments = new ArrayList<>();
    }

    /**
     * Retrieves the list of past diagnoses for the patient.
     *
     * @return a list of past diagnoses
     */
    public ArrayList<String> getPastDiagnoses() {
        return pastDiagnoses;
    }

    /**
     * Retrieves the list of past treatments the patient has undergone.
     *
     * @return a list of past treatments
     */
    public ArrayList<String> getPastTreatments() {
        return pastTreatments;
    }

    /**
     * Retrieves the list of allergies the patient has.
     *
     * @return a list of allergies
     */
    public ArrayList<String> getAllergies() {
        return allergies;
    }

    /**
     * Retrieves the list of medications the patient has previously taken.
     *
     * @return a list of past medications
     */
    public ArrayList<String> getPastMedications() {
        return pastMedications;
    }
}
