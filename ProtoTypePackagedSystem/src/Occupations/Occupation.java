package Occupations;

/**
 * This class represents Occupations Written in the same schema as the csv file
 * This allows us to create a virtual representation of the data from our csv file in our
 * java Project
 * @author Ben Bastianelli Student I.D.: 23368071
 * @version 1.0
 */
public class Occupation {
    // Class Attributes
    private final String department;
    private String jobTitle;
    private int currentPoints;
    private double salary;
    private static int ascensionCount;

    // No-arg constructor to be called in the employees class when creating an employee instance
    public Occupation(String department, String jobTitle, int currentPoints, double salary){
        this.department = department; // Sets the department
        this.jobTitle = jobTitle; // Sets the job title
        this.currentPoints = currentPoints; // Sets the current points
        this.salary = salary; // Sets the salary
        ascensionCount = 0; // Sets the ascension count to 0
    }

    /**
     * Getter method for jobTitle:
     * @return jobTitle
     */
    public String getJobTitle(){
        return jobTitle;
    }

    /**
     * Getter method for current Points
     * returns the Current points for an employee object
     * @return currentPoints
     */
    public int getCurrentPoints(){
        return currentPoints;
    }

    /**
     * Getter method for departments
     * @return department
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Getter method for current Points
     * Returns the current points for an employee Object
     * @return salary
     */
    public double getSalary(){
        return salary;
    }

    /**
     * Getter method for ascension count
     * @return ascensionCount
     */
    public int getAscensionCount() {
        return ascensionCount;
    }

    /**
     * Setter method for current points (To be used in the ascension and upgrade methods)
     * @param newPoint
     */
    public void setCurrentPoints(int newPoint){
        currentPoints = newPoint;
    }

    /**
     * Sets the employees new salary based off the data for their occupation
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets the employees new job title if they Autoascend their current job
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sets the employees ascension count if they are being promoted
     * @param ascensionCount
     */
    public void setAscensionCount(int ascensionCount) {
        Occupation.ascensionCount = ascensionCount;
    }

    /**
     * To string method for the Occupation class that returns all occupation data
     * @return
     */
    public String toString()
    {
        return String.format("Occupation: %s\n" +
                              "Job Title: %s\n" +
                               "Point Value: %d \n" +
                                "Salary %.2f \n",department,getJobTitle(),getCurrentPoints(),getSalary());
    }

    /**
     * Formats the occupation into a string that can be written to a csv using CSV writer.
     * @return String.format("%s,%s,%d,%.2f",department,jobTitle,currentPoints,salary);
     */
    public String toCSV()
    {
        return String.format("%s,%s,%d,%.2f,",department,jobTitle,currentPoints,salary);
    }
}



