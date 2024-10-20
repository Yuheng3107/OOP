package oop;

import java.util.ArrayList;

import oop.AdministratorLogic.Inventory;
public class Hospital {
    public ArrayList<HospitalStaff> staff;
    public ArrayList<Inventory> inventory;
    public ArrayList<Appointment> appointments;

    public Hospital()
    {
        staff = new ArrayList<>();
    }

    public ArrayList<HospitalStaff> getStaff() {
        return staff;
    }

    public int numberOfDoctors()
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

    public void namesOfDoctors()
    {
        int i = 1;
        for (HospitalStaff member : staff)
        {
            if (member instanceof Doctor)
            {
                System.out.println(i + ". Dr. " + member.getName());
            }
            i++;
        }
    }
    public Doctor getDoctorByIndex(int index) {
        int doctorCount = 0;  // To keep track of the current doctor count
        for (HospitalStaff member : staff) {
            if (member instanceof Doctor) {
                if (doctorCount == index) {
                    return (Doctor) member;  // Cast to Doctor and return when the index matches
                }
                doctorCount++;  // Increment the doctor count
            }
        }
        // If no matching doctor is found, handle the invalid index
        System.out.println("Invalid index. Please choose a valid doctor.");
        return null;
    }
}
