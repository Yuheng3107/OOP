package oop;
public class PrescribedMedication {
    public String name;
    public int numberOfUnits;
    public String status;

    public PrescribedMedication(String name, int numberOfUnits)
    {
        this.name = name;
        this.numberOfUnits = numberOfUnits;
        this.status = "Pending";
    }
}
