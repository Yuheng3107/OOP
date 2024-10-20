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
    
    public void addStaffMember(String staffName, int age, String staffID, Gender gender, String role) {
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
    
    public void removeStaffMember(String staffName) {
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getName().equals(staffName)) {
                staff.remove(i);
                break;
            }
        }
    }

}
