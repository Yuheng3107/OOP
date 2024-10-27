package oop;

import java.util.ArrayList;


import oop.AdministratorLogic.Inventory;
import oop.AdministratorLogic.ReplenishmentRequest;
public class Hospital {
    public static ArrayList<HospitalStaff> staff =  new ArrayList<HospitalStaff>();
    public static Inventory inventory = new Inventory();
    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    public static ArrayList<ReplenishmentRequest> replenishmentRequests = new ArrayList<ReplenishmentRequest>();


    public static void addReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.add(request);
    }

    public static void removeReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.remove(request);
    }
    public static ArrayList<HospitalStaff> getStaff() {
        return staff;
    }

    public static int numberOfDoctors()
    {
        int count = 0;
        for (HospitalStaff member : staff)
        {
            if (member instanceof Doctor)
            {
                count++;
            }
        }
        return count;
    }

    public static void namesOfDoctors()
    {
        int i = 1;
        for (HospitalStaff member : staff)
        {
            if (member instanceof Doctor)
            {
                System.out.println(i + ") Dr. " + member.getName());
            }
            i++;
        }
    }

    public static Doctor getDoctorByIndex(int index) {
        int doctorCount = 0; // To keep track of the current doctor count
        for (HospitalStaff member : staff) {
            if (member instanceof Doctor) {
                if (doctorCount == index) {
                    return (Doctor) member; // Cast to Doctor and return when the index matches
                }
                doctorCount++; // Increment the doctor count
            }
        }
        // If no matching doctor is found, handle the invalid index
        System.out.println("Invalid index. Please choose a valid doctor.");
        return null;
    }

    public static String getDoctorNameByStaffID(String staffID)
    {
        for (HospitalStaff member : staff)
        {
            if (member instanceof Doctor && member.getStaffID().equals(staffID))
            {

                return member.getName();

            }
        }
        return null;
    }

    public static Doctor getDoctorObjectByStaffID(String staffID) {
        for (HospitalStaff member : staff) {
            if (member instanceof Doctor && member.getStaffID().equals(staffID)) {
                return (Doctor) member; // Cast to Doctor
            }
        }
        return null; // Return null if no matching doctor is found
    }
    
    
    public static void addStaffMember(String staffName, int age, String staffID, Gender gender, String role) {
        // check whether role is valid
        role = role.toLowerCase();
        if (role != "doctor" || role != "pharmacist") {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }

        // instantiate staff member
        HospitalStaff staffMember;
        if (role == "doctor") {
            staffMember = new Doctor(staffName, staffID, age, gender);

        } else if (role == "pharmacist") {
            staffMember = new Pharmacist(staffName, staffID, age, gender);
        } else {
            System.out.println("Invalid role. Please choose a valid role, either doctor or pharmacist.");
            return;
        }
        staff.add(staffMember);
    }
    
    public static void removeStaffMember(String staffName) {
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getName().equals(staffName)) {
                staff.remove(i);
                break;
            }
        }
    }

    public static String getPatientNameFromPatientID(String patientID)
    {
        for (Patient patient : patients)
        {
            if (patient.getPatientID().equals(patientID))
            {

                return patient.getName();

            }
        }
        return null;
    }


    public static Patient getPatientFromPatientID(String patientID)
    {
        for (Patient patient : patients)
        {
            if (patient.getPatientID().equals(patientID))
            {

                return patient;

            }
        }
        return null;
    }

}
