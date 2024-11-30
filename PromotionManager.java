package Occupations;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;
import UserType.Employees;
import UserType.UserTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionManager implements FormatManager {
    private static final int MAX_ASCENSION_COUNT = 3;
    private final CSVWriter employeeWriter;
    private final Map<Integer, Integer> ascensionCount;
    private final Employees employeeData;

    // Constructor that initialises the promotion manager Object.
    public PromotionManager(CSVWriter employeeWriter) {
        this.employeeData = new Employees();
        this.employeeWriter = employeeWriter;
        this.ascensionCount = new HashMap<>();
    }

    /**
     * Performs yearly upgrade for all employees
     * @throws IOException if there's an issue reading or writing files
     */
    public void performYearlyUpgrade() throws IOException {
        for (UserTypes employee : employeeData.getListOfEmployees()) {

            if (canBePromoted(employee.getEmployee_ID())) {
                UserTypes promotedEmployee = promoteEmployee(employee);
                employeeData.remove(employee);
                employeeData.addEmployee(promotedEmployee);
            } else {
                employeeData.addEmployee(updateEmployeePoints(employee));
            }
        }
        ArrayList<String> toOverWrite = new ArrayList<>();
        for(UserTypes user : employeeData.getListOfEmployees()){
            toOverWrite.add(user.toCSV());
        }
        employeeWriter.OverWriteData(toOverWrite);
    }

    /**
     * Checks if an employee can be promoted based on ascension count
     * @param employeeId the unique identifier of the employee
     * @return true if the employee can be promoted, false otherwise
     */
    private boolean canBePromoted(int employeeId) {
        return ascensionCount.getOrDefault(employeeId, 0) >= MAX_ASCENSION_COUNT;
    }

    /**
     * Promotes an employee to the next job title
     *
     * @param employeeData current employee data
     * @return updated employee data after promotion
     * @throws IOException if there's an issue reading files
     */
    public UserTypes promoteEmployee(UserTypes employeeData) throws IOException {
        
        // Use a specialized PromotionService or Department-specific strategy
        CSVReader departmentReader = new CSVReader("src/Occupations/resources/" + employeeData.getDepartment() + ".csv");
        List<String> jobTitles = departmentReader.readColFromCSV(1);
        
        String currentJobTitle = employeeData.getJobTitle();
        String newJobTitle = findNextJobTitle(jobTitles, currentJobTitle);
        
        if (newJobTitle != null) {
            String tuple = String.format("%s,%s,%s,", employeeData.getDepartment(), newJobTitle, "1");
            String newSalary = departmentReader.tupleFind(tuple, 3);
            String salary = FormatManager.stringFixFormat(newSalary);
            
            employeeData.setJobTitle(newJobTitle);
            employeeData.setPoints(1);
            employeeData.setSalary(Double.parseDouble(salary));
            
            // Update ascension count
            ascensionCount.merge(employeeData.getEmployee_ID(), 1, Integer::sum);
        }
        
        return employeeData;
    }

    /**
     * Finds the next job title in the hierarchy
     * @param jobTitles list of job titles
     * @param currentJobTitle current job title
     * @return next job title or null if at the top
     */
    private String findNextJobTitle(List<String> jobTitles, String currentJobTitle) {
        int currentIndex = jobTitles.indexOf(currentJobTitle);
        return (currentIndex > 0) ? jobTitles.get(currentIndex - 1) : null;
    }

    /**
     * Updates employee points if not at the maximum
     *
     * @param employeeData current employee data
     * @return updated employee data
     * @throws IOException if there's an issue reading files
     */
    private UserTypes updateEmployeePoints(UserTypes employeeData) throws IOException {
        String department = employeeData.getDepartment();
        String jobTitle = employeeData.getJobTitle();
        int currentPoints = employeeData.getPoints();
        
        CSVReader departmentReader = new CSVReader("src/Occupations/resources/" + department + ".csv");
        List<String> pointRange = departmentReader.sortByKey(jobTitle, 2);
        int maxPoints = pointRange.size();
        
        if (currentPoints != maxPoints) {
            int newPoints = currentPoints+ 1;
            employeeData.setPoints(newPoints);
        }
        String tuplePattern = String.format("%s,%s,%d,",department,jobTitle,employeeData.getPoints());
        String salary = departmentReader.tupleFind(tuplePattern,3);
        String salaryF = FormatManager.stringFixFormat(salary);
        double newSalary = Double.parseDouble(salaryF);
        employeeData.setSalary(newSalary);
        
        return employeeData;
    }

    /**
     * Manually promotes an employee
     * @param employeeId unique identifier of the employee
     * @throws IOException if there's an issue reading or writing files
     */
    public void manuallyPromoteEmployee(int employeeId) throws IOException {
        int count = 0;
        for (UserTypes user : employeeData.getListOfEmployees()) {

            if (user.getEmployee_ID() == employeeId) {
                UserTypes promotedEmployee = promoteEmployee(user);
                employeeData.getListOfEmployees().set(count,promotedEmployee);
                break;
            }
            count++;
        }
        ArrayList<String> empData = new ArrayList<>();
        for(UserTypes user: employeeData.getListOfEmployees()){
            empData.add(user.toCSV());
        }

      employeeWriter.OverWriteData(empData);
    }
}
