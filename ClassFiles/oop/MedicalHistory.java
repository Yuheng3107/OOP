package oop;

import java.util.ArrayList;

public class MedicalHistory {
    private ArrayList<String> pastMedications;
    private ArrayList<String> allergies;
    private ArrayList<String> pastDiagnoses;
    private ArrayList<String> pastTreatments;

    public MedicalHistory()
    {
        this.pastMedications = new ArrayList<>();
        this.allergies = new ArrayList<>(); // do we need pastMedications & allergies?
        this.pastDiagnoses = new ArrayList<>();
        this.pastTreatments = new ArrayList<>();
    }

    public ArrayList<String> getPastDiagnoses() {
        return pastDiagnoses;
    }

    public ArrayList<String> getPastTreatments() {
        return pastTreatments;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public ArrayList<String> getPastMedications() {
        return pastMedications;
    }
}
