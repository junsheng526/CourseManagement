/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Programme;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgrammeManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine_28();
        System.out.println("PROGRAMME MANAGEMENT MENU");
        printLine_28();
        System.out.println("1. Add Programme");
        System.out.println("2. Remove Programme");
        System.out.println("3. Search Programme");
        System.out.println("4. Update Programme");
        System.out.println("5. View Programme Details");
        System.out.println("6. Add Tutorial Group");
        System.out.println("7. Remove Tutorial Group");
        System.out.println("8. List All Tutorial Groups");
        System.out.println("9. Generate Reports");
        System.out.println("0. Quit");
        printLine_28();
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 9) {
                System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }

        scanner.nextLine(); // Consume the newline left in the buffer
        System.out.println(); // Add a blank line after input

        return choice;
    }

    public void printLine_28() {
        System.out.println("----------------------------");
    }
    
    public void printLine_40() {
        System.out.println("----------------------------------------");
    }

    public void listAllProgrammes(String outputStr) {
        printLine_40();
        System.out.printf(" %-20s %-50s%n", "Programme Code", "Programme Name");
        printLine_40();
        System.out.print(outputStr);
        printLine_40();
    }

    public Programme inputProgrammeDetails() {
        System.out.print("Enter programme code: ");
        String programmeCode = scanner.nextLine();
        System.out.print("Enter programme name: ");
        String programmeName = scanner.nextLine();
        return new Programme(programmeCode, programmeName);
    }

    public String inputProgrammeCode() {
        System.out.print("Enter programme code: ");
        String code = scanner.nextLine();
        return code;
    }

    public String inputTutorialGroupCode() {
        System.out.print("Enter tutorial group code: ");
        String code = scanner.nextLine();
        return code;
    }

    public void displayProgrammeDetails(Programme programme) {
        System.out.println("Programme Details");
        System.out.println("Programme code: " + programme.getCode());
        System.out.println("Programme name: " + programme.getName());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
