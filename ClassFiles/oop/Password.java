package oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Password {
    public static void updateDefaultPassword(String id, Scanner sc, String role, String filePath) {
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
        updatePasswordInCSV(filePath, id.toUpperCase(), newPassword1);        
        System.out.println("Your new password has been set.");
    }

    public static void updatePassword(String id, String role, String filePath) {
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
        updatePasswordInCSV(filePath, id.toUpperCase(), newPassword1);
        System.out.println("Your new password has been set.");
    }

    public static void updatePasswordInCSV(String filePath, String patientID, String newPassword) {
        List<String> lines = new ArrayList<>();
        String line;
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns[0].equals(patientID)) {
                    columns[1] = newPassword;
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
}
