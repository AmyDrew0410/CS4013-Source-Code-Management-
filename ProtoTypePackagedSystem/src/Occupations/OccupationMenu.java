package Occupations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import CSVHandler.*;

/**
 * Handles initialising an occupation object each time one is instantiated in our system
 * @author Ben Bastianelli StudentI.D. 23368071
 * @Version : 1.0
 */
public class OccupationMenu {
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
            departments = new CSVReader("Occupations\\resources\\departments.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Object> departmentsToChoose = departments.readFromCSV(0);
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
            CSVReader chosenDepartment = new CSVReader("Occupations\\resources\\"+department+".csv");

            //List all jobTitle Choices
            ArrayList<Object> jobTitles = chosenDepartment.readFromCSV(1);

            //Make the user choose a job title
            String jobTitle = (String) choice(jobTitles);

            //From this use the SortByKey method to obtain a point value
            ArrayList<Object> pointRange = chosenDepartment.sortByKey(jobTitle,2);
            String pointValue = (String) choice(pointRange);
            int pointValueParsed = Integer.parseInt(pointValue);

            //Create a string that represents the current tuple of our csv file
            String tuple = String.format("%s,%s,%s,",department,jobTitle,pointValue);

            //Finally use our findTuple method to set a salary.
            String tempSalary = chosenDepartment.tupleFind(tuple,3);

            //Our salary will be returned in the for "[EuroSign] 120,000" so we need to fix the format before
            //We parse it
            tempSalary = stringFixFormat(tempSalary);
            double salary = Double.parseDouble(tempSalary);

            //Remove all whitespace in our department name so it fits the Enum data-types
            department = department.replaceAll(" ", "");

            //Construct a Occupations.Occupation subclass by getting the data at value of our department
            //And calling our create occupation method

            Occupation occupation = new Occupation(department,jobTitle,pointValueParsed,salary);
            return occupation;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to ascend the employee if the ascension count is at three when the conditional is run
     * in the yearlyUpgrade method.
     * @param occupation
     * @returns updated occupation
     */
    public void ascend(Occupation occupation) throws IOException {
        // Reset ascension count
        occupation.setAscensionCount(0);

        // Get the CSV handler for the department
        CSVReader chosenDepartment = new CSVReader("Occupations.resources\\" + occupation.getDepartment() + ".csv");

        // Get all unique job titles in order (using column 1 for job titles)
        ArrayList<Object> jobTitles = chosenDepartment.readFromCSV(1);

        // Find current job title's index
        int currentIndex = jobTitles.indexOf(occupation.getJobTitle());

        if (currentIndex == -1) {
            throw new IllegalStateException("Current job title not found in department listings");
        }

        if (currentIndex == 0) {
            throw new IllegalStateException("Already at highest job title - cannot ascend further");
        }

        // Get the next higher job title (previous in the list since higher positions are listed first)
        String newJobTitle = (String) jobTitles.get(currentIndex - 1);

        // Update the occupation
        occupation.setJobTitle(newJobTitle);
        occupation.setCurrentPoints(1);  // Reset to entry level for new position

        // Update salary for new position
        String tuple = String.format("%s,%s,%d,", occupation.getDepartment(), newJobTitle, occupation.getCurrentPoints());
        double salary = Double.parseDouble(stringFixFormat(chosenDepartment.tupleFind(tuple,3)));
        occupation.setSalary(salary);
    }

    /**
     * This method implements the point upgrade system for the Occupations.Occupation class
     * which allows for the upgrade of a users point in the salary scale
     * @param occupation
     * @throws IOException
     */
    public void yearlyUpgrade(Occupation occupation) throws IOException {
        // Check if ready for ascension
        if (occupation.getAscensionCount() >= 3) {
            try {
                ascend(occupation);
                return;
            } catch (IllegalStateException e) {
                // If cannot ascend (already at highest position), continue with normal point upgrade
                System.out.println("Notice: " + e.getMessage() + ". Continuing with regular upgrade.");
            }
        }

        CSVReader chosenDepartment = new CSVReader("Occupations.resources\\" + occupation.getDepartment() + ".csv");
        ArrayList<Object> pointRange = chosenDepartment.sortByKey(occupation.getJobTitle(), 2);

        if (pointRange.isEmpty()) {
            throw new IllegalStateException("No point ranges found for job title: " + occupation.getJobTitle());
        }

        int maxPoints = pointRange.size();

        if (occupation.getCurrentPoints() < maxPoints) {
            // Normal point upgrade
            occupation.setCurrentPoints(occupation.getCurrentPoints() + 1);
            String tuple = String.format("%s,%s,%d,", occupation.getDepartment(), occupation.getJobTitle(), occupation.getCurrentPoints());
            double salary = Double.parseDouble(stringFixFormat(chosenDepartment.tupleFind(tuple,3)));
            occupation.setSalary(salary);
        } else {
            // At max points, increment ascension counter
            occupation.setAscensionCount(occupation.getAscensionCount() + 1);
        }
    }

    private String stringFixFormat(String target){
        target = target.replace("€", "");
        target = target.replaceAll("\"","");
        target = target.replace(",","");
        return target;
    }

    public Object choice(ArrayList<Object> occupationTypes) {
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
