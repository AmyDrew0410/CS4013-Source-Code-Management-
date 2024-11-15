import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents Occupations Written in the same schema as the csv file
 * This allows us to create a virtual representation of the data from our csv file in our
 * java Project
 * @author Ben Bastianelli Student I.D.: 23368071
 * @version 1.0
 */
public class Occupation {
    private final String department;
    private String jobTitle;
    private int currentPoints;
    private double salary;



    //No-arg constructor to be called in the employees class when creating an employee instance
    public Occupation(String department, String jobTitle, int currentPoints, double salary){
        this.department = department;
        this.jobTitle = jobTitle;
        this.currentPoints = currentPoints;
        this.salary = salary;
    }

    /**
     * Getter method for jobTitle:
     * Returns JobTitle
     * @return
     */
    public String getJobTitle(){
        return jobTitle;
    }

    /**
     * Getter method for current Points
     * returns the Current points for an employee object
     * @return
     */
    public int getCurrentPoints(){
        return currentPoints;
    }

    /**
     * Getter method for current Points
     * Returns the current points for an employee Object
     * @return
     */
    public double getSalary(){
        return salary;
    }

    public String toString(){
        return String.format("Occupation: %s\n" +
                              "Job Title: %s\n" +
                               "Point Value: %d \n" +
                                "Salary %.2f \n",department,getJobTitle(),getCurrentPoints(),getSalary());
    }




}



