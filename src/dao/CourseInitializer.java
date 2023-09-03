/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Programme;

/**
 *
 * @author NITRO 5
 */
public class CourseInitializer {

    public ListInterface<Programme> initializeProgrammes() {
        ListInterface<Programme> programmeList = new ArrayList<>();
        programmeList.add(new Programme("P1001", "Computer Science"));
        programmeList.add(new Programme("P1002", "Engineering"));
        programmeList.add(new Programme("P1003", "Business Administration"));
        // Add more programmes here
        
        return programmeList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProgrammes() method
        CourseInitializer p = new CourseInitializer();
        ListInterface<Programme> programmeList = p.initializeProgrammes();
        System.out.println("\nProgrammes:\n" + programmeList);
    }
}
