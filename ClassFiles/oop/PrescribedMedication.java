package oop;
public class PrescribedMedication {
    public String name;
    private int numberOfUnits;
    public StatusOfPrescribedMedication status;

    public PrescribedMedication(String name, int numberOfUnits)
    {
        this.name = name;
        this.numberOfUnits = numberOfUnits;
        this.status = StatusOfPrescribedMedication.Pending;
    }

    public int getNumberOfUnits()
    {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits)
    {
        this.numberOfUnits = numberOfUnits;
    }
}
