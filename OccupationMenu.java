package Occupations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import FileHandler.*;

/**
 * Handles initialising an occupation object each time one is instantiated in our system
 * @author Ben Bastianelli StudentI.D. 23368071
 * @Version : 1.0
 */
public class OccupationMenu implements FormatManager
{
    private final Scanner in; // Generic scanner for the class
    public OccupationMenu(){
        in = new Scanner(System.in);
    } // Instantiates our scanner to read User input

    /**
     * A run method that gets the department of the new employees desired occupation and initialises an Occupation variable
     * Based off of further User input
     * @return Occupation object
     */
    public Occupation run(){
        //Ask the UserType.HR officer what department they wish to add the employee to
        System.out.println("What department is the employee being allocated to:");

        //Implement method to list all department options and returns user choice as string
        CSVReader departments;
        departments = new CSVReader("src/Occupations/resources/departments.csv");
        ArrayList<String> departmentsToChoose = departments.readColFromCSV(0);
        String department = choice(departmentsToChoose);

        //If our string equals a department, call the initOccupation method
        return initOccupation(department);
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
            String jobTitle = choice(jobTitles);

            //From this use the SortByKey method to obtain a point value
            ArrayList<String> pointRange = chosenDepartment.sortByKey(jobTitle,2);
            String pointValue =  choice(pointRange);
            int pointValueParsed = Integer.parseInt(pointValue);

            //Create a string that represents the current tuple of our csv file
            String tuple = String.format("%s,%s,%s,",department,jobTitle,pointValue);

            //Finally use our findTuple method to set a salary.
            String tempSalary = chosenDepartment.tupleFind(tuple,3);

            //Our salary will be returned in the for "[EuroSign] 120,000" so we need to fix the format before
            //We parse it
            tempSalary = FormatManager.stringFixFormat(tempSalary);
            double salary = Double.parseDouble(tempSalary);

            return new Occupation(department,jobTitle,pointValueParsed,salary); // Returns the instantiated Occupation object
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A choice method that gets the users choice from the array list of string passed through
     * @param occupationTypes The specified data needed to instantiate the occupation
     * @return occupationTypes.get(n) which is a string that represents the users choice
     */
    public String choice(ArrayList<String> occupationTypes)
    {
        char index = 'A'; // The index of the choices for the User
        for (String occupation : occupationTypes) // For each occupation String in the arrayList of type string
        {
            System.out.printf("%s) %s \n", index, occupation); // Print out the data like the follow
            // "A) Option 1"
            index++; // Increment Index by 1
        }
        // Read the user input, trim any white space and set it to uppercase
        System.out.print("Your Choice: ");
        String userChoice = in.nextLine().trim().toUpperCase();

        // Check if input is a single letter within the expected range
        if (userChoice.length() == 1)
        {
            int n = userChoice.charAt(0) - 'A'; // Gets the index by subtracting the ASCII value of A from the choice ASCII
            if (n >= 0 && n < occupationTypes.size())  // If the index is 0 or more and if it is within the range of the size of occupationTypes.size()
            {
                return occupationTypes.get(n); // Return the value stored there
            }
        }
        System.out.println("Invalid choice. Please select a valid option."); // Tell the user they picked an invalid option
        return null;
    }

}
