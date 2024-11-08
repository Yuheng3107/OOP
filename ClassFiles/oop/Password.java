package oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Password {
    //Call this to update and change the default assigned password
    public static void updateDefaultPassword(String id, Scanner sc, String role, String filePath)
    {
        String newPassword1, newPassword2;
        System.out.println("You are required to change your password!");
        do {
            System.out.print("Enter new password: ");
            newPassword1 = sc.nextLine();
            System.out.print("Enter new password again: ");
            newPassword2 = sc.nextLine();
            if (!newPassword1.equals(newPassword2)) {
                System.out.println("Passwords do not match, please try again!");
            }
        } while (!newPassword1.equals(newPassword2));
        updatePasswordInCSV(filePath, id.toUpperCase(), newPassword2);        
        System.out.println("Your new password has been set.");
    }
    //Calls this to request user to enter new password
    public static void updatePassword(String id, String role, String filePath)
    {
        Scanner sc = new Scanner(System.in);
        String newPassword1, newPassword2;
        do {
            System.out.print("Enter new password: ");
            newPassword1 = sc.nextLine();
            System.out.print("Enter new password again: ");
            newPassword2 = sc.nextLine();
            if (!newPassword1.equals(newPassword2)) {
                System.out.println("Passwords do not match, please try again!");
            }
        } while (!newPassword1.equals(newPassword2));
        updatePasswordInCSV(filePath, id.toUpperCase(), newPassword2);
        System.out.println("Your new password has been set.");
    }
    //Most functions call this function to update the newly input password into the CSV. This is the only function that takes the password input and hash it and store into the CSV
    public static void updatePasswordInCSV(String filePath, String patientID, String newPassword)
    {
        List<String> lines = new ArrayList<>();
        String line, hashedPassword = hashPassword(newPassword);
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns[0].equals(patientID)) {
                    columns[1] = hashedPassword;
                    line = String.join(",", columns);
                    isUpdated = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
                System.out.println("Password updated successfully for ID: " + patientID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ID not found: " + patientID);
        }
    }
    //Hashing algorithm itself
    public static String hashPassword(String password)
    {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Convert the password to bytes and hash it
            byte[] hashedBytes = digest.digest(password.getBytes());
            
            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 algorithm not found.");
        }
    }
}
