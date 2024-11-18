import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        //Ask the HR officer what department they wish to add the employee to
        System.out.println("What department is the employee being allocated to:");

        //Implement method to list all department options and returns user choice as string
        CSVHandler departments = null;
        try {
            departments = new CSVHandler("resources\\departments.csv");
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
     * Initialises our Occupation Object for our Occupation subClass
     * Reads data from our CSV to return a unique list of Choices
     * Returns the Choice they made and assigns it to the specified class Variable
     * in Occupation types
     * @return Occupation Object
     */
    private Occupation initOccupation(String department){
        try {
            //Create a csvHandler for the given departments csv file
            CSVHandler chosenDepartment = new CSVHandler("resources\\"+department+".csv");

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

            //Construct a Occupation subclass by getting the data at value of our department
            //And calling our create occupation method

            Occupation occupation = new Occupation(department,jobTitle,pointValueParsed,salary);
            return occupation;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
