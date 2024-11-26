package Occupations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import FileHandler.*;

/**
 * Handles initialising an occupation object each time one is instantiated in our system
 * @author Ben Bastianelli StudentI.D. 23368071
 * @Version : 1.0
 */
public class OccupationMenu implements FormatManager
{
    private Scanner in;
    public OccupationMenu(){
        in = new Scanner(System.in);
    }

    public Occupation run(){
        //Ask the UserType.HR officer what department they wish to add the employee to
        System.out.println("What department is the employee being allocated to:");

        //Implement method to list all department options and returns user choice as string
        CSVReader departments = null;
        try {
            departments = new CSVReader("src/Occupations/resources/departments.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> departmentsToChoose = departments.readColFromCSV(0);
        String department = (String) choice(departmentsToChoose);

        //If our string equals a department, call the initOccupation method
        Occupation toReturn = initOccupation(department);
        return toReturn;
    }

    /**
     * Initialises our Occupations.Occupation Object for our Occupations.Occupation subClass
     * Reads data from our CSV to return a unique list of Choices
     * Returns the Choice they made and assigns it to the specified class Variable
     * in Occupations.Occupation types
     * @return Occupations.Occupation Object
     */
    private Occupation initOccupation(String department){
        try {
            //Create a csvHandler for the given departments csv file
            CSVReader chosenDepartment = new CSVReader("src/Occupations/resources/"+department+".csv");

            //List all jobTitle Choices
            ArrayList<String> jobTitles = chosenDepartment.readColFromCSV(1);

            //Make the user choose a job title
            String jobTitle = (String) choice(jobTitles);

            //From this use the SortByKey method to obtain a point value
            ArrayList<String> pointRange = chosenDepartment.sortByKey(jobTitle,2);
            String pointValue = (String) choice(pointRange);
            int pointValueParsed = Integer.parseInt(pointValue);

            //Create a string that represents the current tuple of our csv file
            String tuple = String.format("%s,%s,%s,",department,jobTitle,pointValue);

            //Finally use our findTuple method to set a salary.
            String tempSalary = chosenDepartment.tupleFind(tuple,3);

            //Our salary will be returned in the for "[EuroSign] 120,000" so we need to fix the format before
            //We parse it
            tempSalary = FormatManager.stringFixFormat(tempSalary);
            double salary = Double.parseDouble(tempSalary);

            Occupation occupation = new Occupation(department,jobTitle,pointValueParsed,salary);
            return occupation;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object choice(ArrayList<String> occupationTypes) {
        char index = 'A';
        for (Object occupation : occupationTypes) {
            System.out.printf("%s) %s \n", index, occupation);
            index++;
        }
        System.out.print("Your Choice: ");
        String userChoice = in.nextLine().trim().toUpperCase();

        // Check if input is a single letter within the expected range
        if (userChoice.length() == 1) {
            int n = userChoice.charAt(0) - 'A';
            if (n >= 0 && n < occupationTypes.size()) {
                return occupationTypes.get(n);
            }
        }
        System.out.println("Invalid choice. Please select a valid option.");
        return null;
    }

}
