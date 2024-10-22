package oop;
public class MedicalHistory {
    public String[] pastMedications;
    public String[] allergies;
    public String[] pastDiagnoses;
    public String[] pastTreatments;

    public MedicalHistory(String[] pastMedications, String[] allergies, String[] pastDiagnoses, String[] pastTreatments)
    {
        this.pastMedications = pastMedications;
        this.allergies = allergies;
        this.pastDiagnoses = pastDiagnoses;
        this.pastTreatments = pastTreatments;
    }

    public String[] getPastDiagnoses() {
        return pastDiagnoses;
    }

    public String[] getPastTreatments() {
        return pastTreatments;
    }
}
