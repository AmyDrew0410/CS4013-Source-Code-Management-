package Occupations;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionManager {
    private static final int MAX_ASCENSION_COUNT = 3;
    private final CSVWriter employeeWriter;
    private final Map<String, Integer> ascensionCount;
    private final ArrayList<String> employeeData;

    public PromotionManager(ArrayList<String> employeeData, CSVWriter employeeWriter) {
        this.employeeData = employeeData;
        this.employeeWriter = employeeWriter;
        this.ascensionCount = new HashMap<>();
    }

    /**
     * Performs yearly upgrade for all employees
     * @throws IOException if there's an issue reading or writing files
     */
    public void performYearlyUpgrade() throws IOException {

        ArrayList<String> updatedEmployeeData = new ArrayList<>();

        for (String employee : employeeData) {
            String[] employeeParts = employee.split(",");
            String employeeId = employeeParts[2];

            if (canBePromoted(employeeId)) {
                String promotedEmployee = promoteEmployee(employee);
                updatedEmployeeData.add(promotedEmployee);
            } else {
                updatedEmployeeData.add(updateEmployeePoints(employee));
            }
        }

        employeeWriter.OverWriteData(updatedEmployeeData);
    }

    /**
     * Checks if an employee can be promoted based on ascension count
     * @param employeeId the unique identifier of the employee
     * @return true if the employee can be promoted, false otherwise
     */
    private boolean canBePromoted(String employeeId) {
        return ascensionCount.getOrDefault(employeeId, 0) >= MAX_ASCENSION_COUNT;
    }

    /**
     * Promotes an employee to the next job title
     * @param employeeData current employee data
     * @return updated employee data after promotion
     * @throws IOException if there's an issue reading files
     */
    public String promoteEmployee(String employeeData) throws IOException {
        String[] empSplit = employeeData.split(",");
        
        // Use a specialized PromotionService or Department-specific strategy
        CSVReader departmentReader = new CSVReader("src/Occupations/resources/" + empSplit[7] + ".csv");
        List<String> jobTitles = departmentReader.readColFromCSV(1);
        
        String currentJobTitle = empSplit[8];
        String newJobTitle = findNextJobTitle(jobTitles, currentJobTitle);
        
        if (newJobTitle != null) {
            String tuple = String.format("%s,%s,%s,", empSplit[7], newJobTitle, "1");
            String newSalary = departmentReader.tupleFind(tuple, 3);
            
            empSplit[8] = newJobTitle;
            empSplit[9] = "1";
            empSplit[10] = newSalary;
            
            // Update ascension count
            ascensionCount.merge(empSplit[2], 1, Integer::sum);
        }
        
        return String.join(",", empSplit);
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
     * @param employeeData current employee data
     * @return updated employee data
     * @throws IOException if there's an issue reading files
     */
    private String updateEmployeePoints(String employeeData) throws IOException {
        String[] empSplit = employeeData.split(",");
        String department = empSplit[7];
        String jobTitle = empSplit[8];
        String currentPoints = empSplit[9];
        
        CSVReader departmentReader = new CSVReader("src/Occupations/resources/" + department + ".csv");
        List<String> pointRange = departmentReader.sortByKey(jobTitle, 2);
        int maxPoints = pointRange.size();
        
        if (!currentPoints.equals(String.valueOf(maxPoints))) {
            int newPoints = Integer.parseInt(currentPoints) + 1;
            empSplit[9] = String.valueOf(newPoints);
        }
        
        return String.join(",", empSplit);
    }

    /**
     * Manually promotes an employee
     * @param employeeId unique identifier of the employee
     * @throws IOException if there's an issue reading or writing files
     */
    public void manuallyPromoteEmployee(String employeeId) throws IOException {

        System.out.println(employeeData.size());
        
        for (int i = 0; i < employeeData.size(); i++) {
            if (employeeData.get(i).contains(employeeId)) {
                String promotedEmployee = promoteEmployee(employeeData.get(i));
                employeeData.set(i, promotedEmployee);
                System.out.println("e");
                break;
            }
        }

      employeeWriter.OverWriteData(employeeData);
    }
}
