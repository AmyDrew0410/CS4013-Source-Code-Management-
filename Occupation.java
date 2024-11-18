import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    private int ascensionCount;


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getAscensionCount() {
        return ascensionCount;
    }

    public void setAscensionCount(int ascensionCount) {
        this.ascensionCount = ascensionCount;
    }

    //No-arg constructor to be called in the employees class when creating an employee instance
    public Occupation(String department, String jobTitle, int currentPoints, double salary){
        this.department = department;
        this.jobTitle = jobTitle;
        this.currentPoints = currentPoints;
        this.salary = salary;
        ascensionCount = 0;
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
     * Getter method for departments
     */
    public String getDepartment()
    {
        return department;
    }

    private void setCurrentPoints(int newPoint){
        currentPoints = newPoint;
    }

    /**
     * Getter method for current Points
     * Returns the current points for an employee Object
     * @return
     */
    public double getSalary(){
        return salary;
    }

    private void setSalary(CSVHandler chosenDepartment, String tuple) throws IOException {
        String tempSalary = chosenDepartment.tupleFind(tuple,3);

        //Our salary will be returned in the for "[EuroSign] 120,000" so we need to fix the format before
        //We parse it
        tempSalary = stringFixFormat(tempSalary);
        this.salary = Double.parseDouble(tempSalary);
    }

    public String toString()
    {
        return String.format("Occupation: %s\n" +
                              "Job Title: %s\n" +
                               "Point Value: %d \n" +
                                "Salary %.2f \n",department,getJobTitle(),getCurrentPoints(),getSalary());
    }

    /**
     * Method to increment the points of an Employee with an occupation
     *
     * @param occupation
     * @returns updated occupation
     */
    public void ascend(Occupation occupation) throws IOException {
        // Reset ascension count
        setAscensionCount(0);

        // Get the CSV handler for the department
        CSVHandler chosenDepartment = new CSVHandler("resources\\" + occupation.getDepartment() + ".csv");

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
        setSalary(chosenDepartment, tuple);
    }

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

        CSVHandler chosenDepartment = new CSVHandler("resources\\" + occupation.getDepartment() + ".csv");
        ArrayList<Object> pointRange = chosenDepartment.sortByKey(occupation.getJobTitle(), 2);

        if (pointRange.isEmpty()) {
            throw new IllegalStateException("No point ranges found for job title: " + occupation.getJobTitle());
        }

        int maxPoints = pointRange.size();

        if (occupation.getCurrentPoints() < maxPoints) {
            // Normal point upgrade
            occupation.setCurrentPoints(occupation.getCurrentPoints() + 1);
            String tuple = String.format("%s,%s,%d,", occupation.getDepartment(), occupation.getJobTitle(), occupation.getCurrentPoints());
            occupation.setSalary(chosenDepartment, tuple);
        } else {
            // At max points, increment ascension counter
            setAscensionCount(getAscensionCount() + 1);
        }
    }

    private String stringFixFormat(String target){
        target = target.replace("€", "");
        target = target.replaceAll("\"","");
        target = target.replace(",","");
        return target;
    }



}



