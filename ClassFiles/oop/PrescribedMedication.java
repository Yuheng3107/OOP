package oop;
/**
 * The {@code PrescribedMedication} class represents a prescribed medication for a patient.
 * It includes details such as the medication's name, the number of units prescribed, and its current status.
 * 
 * <p>This class tracks the status of the prescribed medication, which can be either {@code Pending} or {@code Dispensed}, 
 * through the {@link StatusOfPrescribedMedication} enum. It also allows for updating the number of units prescribed.</p>
 * @author Kuang Yu Xuan
 * @version 1.0
 * @since 2024-11-09
 */
public class PrescribedMedication {
    /**
     * The name of the prescribed medication.
     */
    public String name;

    /**
     * The number of units prescribed for the medication.
     */
    private int numberOfUnits;

    /**
     * The status of the prescribed medication, indicating whether it is pending or dispensed.
     */
    public StatusOfPrescribedMedication status;

    /**
     * Constructs a new {@code PrescribedMedication} with the specified name and number of units.
     * The initial status of the medication is set to {@code Pending}.
     *
     * @param name The name of the prescribed medication.
     * @param numberOfUnits The number of units prescribed for the medication.
     */
    public PrescribedMedication(String name, int numberOfUnits)
    {
        this.name = name;
        this.numberOfUnits = numberOfUnits;
        this.status = StatusOfPrescribedMedication.Pending;
    }

    /**
     * Returns the number of units of the prescribed medication.
     *
     * @return The number of units prescribed.
     */
    public int getNumberOfUnits()
    {
        return numberOfUnits;
    }

    /**
     * Sets the number of units of the prescribed medication.
     *
     * @param numberOfUnits The new number of units to be prescribed.
     */
    public void setNumberOfUnits(int numberOfUnits)
    {
        this.numberOfUnits = numberOfUnits;
    }
}
