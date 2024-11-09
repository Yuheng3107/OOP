package oop;

/**
 * The {@code StatusOfPrescribedMedication} enum represents the possible statuses of a prescribed medication of a patient.
 * 
 * <p>This enum can be used to track whether a prescribed medication is still pending or has been dispensed to a patient.</p>
 * 
 * <p>The two possible values are:</p>
 * <ul>
 *     <li>{@code Pending} - Indicates that the medication has been prescribed but not yet dispensed.</li>
 *     <li>{@code Dispensed} - Indicates that the medication has been dispensed to the patient.</li>
 * </ul>
 * @author Kuang Yu Xuan
 * @version 1.0
 * @since 2024-11-09
 */ 
public enum StatusOfPrescribedMedication{
    /**
     * Indicates that the prescribed medication is pending and has not yet been dispensed.
     */
    Pending,

    /**
     * Indicates that the prescribed medication has been dispensed to the patient.
     */
    Dispensed
}
