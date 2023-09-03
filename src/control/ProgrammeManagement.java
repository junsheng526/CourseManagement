/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.ProgrammeManagementUI;
import entity.*;
import dao.CourseDAO;
import utility.MessageUI;

public class ProgrammeManagement {

    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ProgrammeManagementUI programmeUI = new ProgrammeManagementUI();
    private CourseDAO programmeDAO = new CourseDAO();

    public ProgrammeManagement() {
        programmeList = programmeDAO.retrieveFromFile();
    }

    public void runProgrammeManagement() {
        int choice = 0;
        do {
            choice = programmeUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addNewProgramme();
                    programmeUI.listAllProgrammes(getAllProgrammes());
                    programmeUI.displayMessage("New programme added.");
                    break;
                case 2:
                    programmeUI.displayMessage("Currently no this action");
                    break;
                case 3:
                    findProgramme();
                    break;
                case 4:
                    amendProgrammeDetails();
                    break;
                case 5:
                    programmeUI.listAllProgrammes(getAllProgrammes());
                    break;
                case 6:
                    addTutorialGroup();
                    break;
                case 7:
                    removeTutorialGroup();
                    break;
                case 8:
                    generateReports();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewProgramme() {
        Programme newProgramme = programmeUI.inputProgrammeDetails();
        programmeList.add(newProgramme);
        programmeDAO.saveToFile(programmeList);
    }

//    public void listAllProgrammes() {
//        String outputStr = getAllProgrammes();
//        programmeUI.displayMessage(outputStr);
//    }
    public void findProgramme() {
        String programmeCode = programmeUI.inputProgrammeCode();
        Programme programme = findProgrammeByCode(programmeCode);
        if (programme != null) {
            programmeUI.displayProgrammeDetails(programme);
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void amendProgrammeDetails() {
        String programmeCode = programmeUI.inputProgrammeCode();
        int programmePosition = findGivenPosition(programmeCode);
        if (programmePosition != -1) {
            programmeUI.displayMessage("### Please enter new details ###");
            Programme newProgrammeDetails = programmeUI.inputProgrammeDetails();
            programmeList.replace(programmePosition, newProgrammeDetails);
            programmeUI.displayMessage("Programme details amended.");
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void listAllTutorialGroups() {
        programmeUI.displayMessage("Currently no this action");
    }

    public void addTutorialGroup() {
        programmeUI.displayMessage("Currently no this action");
    }

    public void removeTutorialGroup() {
        programmeUI.displayMessage("Currently no this action");
    }

    public void generateReports() {
        // Implement generating relevant reports
        // This functionality will depend on the reports you need to generate
        programmeUI.displayMessage("Currently no this action");
    }

    private int findGivenPosition(String programmeCode) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            if (programme.getCode().equals(programmeCode)) {
                return i;
            }
        }
        return -1;
    }

    private Programme findProgrammeByCode(String programmeCode) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            if (programme.getCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }

    public String getAllProgrammes() {
        String outputStr = "";
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            outputStr += programmeList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public static void main(String[] args) {
        ProgrammeManagement programmeManagement = new ProgrammeManagement();
        programmeManagement.runProgrammeManagement();
    }
}
