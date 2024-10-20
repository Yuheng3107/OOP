package oop.AdministratorLogic;

import oop.Appointment;
import oop.Hospital;
import oop.HospitalStaff;
import oop.MedicineStock;
import oop.Patient;
import oop.Pharmacist;
import oop.MedicineStock;
import oop.AppointmentOutcome;
import oop.BloodType;
import oop.Doctor;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import oop.Gender;
import oop.AdministratorLogic.TextDB;

public class Administrator implements StaffManagementInterface, AppointmentManagementInterface, InventoryManagementInterface, SystemInitialisationInterface {
    public Hospital hospital;


    
    @Override
    public void viewAppointmentDetails(Appointment appointment)
    {
        // prints out appointment details of the appointment
        // prints patient ID, doctor ID, appointment status
        // date and time of appointment
        // appointment outcome record
        System.out.println("Appointment details: ");
        System.out.println("Patient ID: " + appointment.patientId);
        System.out.println("Doctor ID: " + appointment.doctorId);
        System.out.println("Appointment status: " + appointment.status);
        System.out.println("Date: " + appointment.date);
        System.out.println("Start time: " + appointment.timeSlot.start);
        System.out.println("End time: " + appointment.timeSlot.end);
        // print appointment outcomes
        for (AppointmentOutcome outcome : appointment.appointmentOutcome) {
            // TODO implement logic to get appointment outcomes
        }
    }


    public void manageHospitalStaff()
    {
        // choose whether to add, update, remove or delete staff member
        System.out.println("Choose an option: ");
        System.out.println("1. Add staff member");
        System.out.println("2. Update staff member");
        System.out.println("3. Remove staff member");
        System.out.println("4. Display staff members");
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();

        switch (opt) {
            case 1:
                // add staff member
                System.out.println("Enter staff name: ");
                String staffName = sc.next();

                System.out.println("Enter staff age: ");
                int age = sc.nextInt();

                System.out.println("Enter staff ID: ");
                String staffID = sc.next();

                System.out.println("Enter staff role: ");
                String role = sc.next();

                System.out.println("Enter staff gender: ");
                try {
                    String genderString = sc.next();
                    Gender gender = Gender.valueOf(genderString);
                    addStaffMember(staffName, age, staffID, gender, role);
                }

                catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender. Please enter M or F.");
                }   

                break;
            case 2:
                // update staff member
                updateStaffMember(null);

                break;
            case 3:
                // remove staff member
                System.out.println("Enter name of staff member to remove: ");
                String name = sc.next();
                removeStaffMember(name);            
                break;
            case 4:
                // display staff members, choose filter
                System.out.println("Enter filter: ");
                String filter = sc.next();
                displayStaff(filter);
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }

        sc.close();

    }
    public void addStaffMember(String staffName, int age, String staffID, Gender gender, String role)
    {

        // add staff member to hospital
        hospital.addStaffMember(staffName, age, staffID, gender, role);

    }
    public void updateStaffMember(String staffName)
    {
        // not sure what to update, gender???
        
    }
    public void removeStaffMember(String staffName)
    {
        hospital.removeStaffMember(staffName);
    }
    public void displayStaff(String filter)
    {
        ArrayList<HospitalStaff> staffMembers = hospital.getStaff();
        switch (filter) {
            
            case "role":
                // sort by role
                staffMembers.sort((s1, s2) -> s1.getRole().compareTo(s2.getRole()));
                break;

            case "age":
                // sort by age
                staffMembers.sort((s1, s2) -> s1.getAge() - s2.getAge());
                break;

            case "gender":
                // sort by gender
                staffMembers.sort((s1, s2) -> s1.getGender().compareTo(s2.getGender()));
                break;
        }

        for (HospitalStaff member : staffMembers) {
            // print out staff information
            System.out.println("Name of Staff: " + member.getName());
            System.out.println("Age: " + member.getAge());
            System.out.println("ID: " + member.getStaffID());
            System.out.println("Role: " + member.getRole());
            System.out.println("Gender: " + member.getGender());
        }
    }
    public void viewInventory()
    {

    }
    public void approveReplenishmentRequest(MedicineStock medicine, int amount)
    {

    }
    public void addMedicineStock(MedicineStock stock)
    {

    }
    public void updateMedicineStock(MedicineStock stock)
    {
        
    }
    public void deleteMedicineStock(MedicineStock stock)
    {
        return;
    }
    
    @Override
    public void updateLowStockLevel(String name, int newLevel) {

    }

    public static final String SEPARATOR = ",";

    // an example of reading
	public  ArrayList<MedicineStock> importInventory(String filename) {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<MedicineStock> alr = new ArrayList<MedicineStock>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter ","
//Medicine Name,Initial Stock,Low Stock Level Alert
			String medicineName = star.nextToken().trim(); // first token
			String stock = star.nextToken().trim(); // second token
			String lowStockLevel = star.nextToken().trim(); // third token
			
			MedicineStock medicine = new MedicineStock(medicineName, Integer.parseInt(stock), Integer.parseInt(lowStockLevel));

			// add to Professors list
			alr.add(medicine);
		}
		return alr;
	}
	
	// an example of reading
	public ArrayList<Patient> importPatients(String filename, Hospital hospital) {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Patient> alr = new ArrayList<Patient>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter ","
//Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information
			String patientID = star.nextToken().trim(); 
			String name = star.nextToken().trim(); 
			String dob = star.nextToken().trim(); 
			String gender = star.nextToken().trim(); 
			String bloodType = star.nextToken().trim(); 
			String email = star.nextToken().trim();

			LocalDate dateOfBirth = LocalDate.parse(dob);
			
			// need to process information into parsable format

			// String name, String patientID, LocalDate dateOfBirth, Gender gender, String address, BloodType bloodType, MedicalHistory medicalHistory, String email, Hospital hospital
			Patient patient = new Patient(name, patientID, dateOfBirth, Gender.valueOf(gender),
					BloodType.valueOf(bloodType), null, email, hospital);

			// add to patients list
			alr.add(patient);
		}
		return alr;
	}
	
	// an example of reading
	public ArrayList<HospitalStaff> importStaff(String filename)  {
		// read String from text file
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<HospitalStaff> alr = new ArrayList<HospitalStaff>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter ","
//Staff ID,Name,Role,Gender,Age
			String staffID = star.nextToken().trim(); // first token
			String name = star.nextToken().trim(); // second token
			String role = star.nextToken().trim().toLowerCase(); // third token
			String gender = star.nextToken().trim(); // fourth token
			String age = star.nextToken().trim(); // fifth token
			
			HospitalStaff staff;
			if (role == "doctor") {
				staff = new Doctor(name, staffID, Integer.parseInt(age), Gender.valueOf(gender));
			}
			else if (role == "pharmacist") {
				staff = new Pharmacist(name, staffID, Integer.parseInt(age), Gender.valueOf(gender));
			}
			else {
				System.out.println("Invalid role: " + role);
				return null;
			}


			// add to staff list
			alr.add(staff);
		}
		return alr;
	}


  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }
    public void initialise(String staffFilename, String patientFilename, String inventoryFilename)
    {
        // create the hospital
        Hospital hospital = new Hospital();

        
        try {
            // initialise staff
            ArrayList<HospitalStaff> staff = importStaff(staffFilename);
            hospital.staff = staff;
            // initialise patients
            ArrayList<Patient> patients = importPatients(patientFilename, hospital);
            hospital.patients = patients;
            // initialise inventory
            ArrayList<MedicineStock> medicineStock = importInventory(inventoryFilename);
            Inventory inventory = new Inventory();
            inventory.medicine = medicineStock;
            hospital.inventory = inventory;
        }

        catch (Exception e) {
            e.printStackTrace();
            // handle error
        }

    }
}
