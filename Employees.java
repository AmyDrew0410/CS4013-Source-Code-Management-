
package UserType;

import java.io.IOException;
import java.util.ArrayList;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;

public class Employees{
    // List of employees
    protected ArrayList<UserTypes> listOfEmployees = new ArrayList<>();

     /**
      * Reads employee data from a CSV file and initialises a list of UserTypes objects based on the data
      * @return
      */
    public Employees()
    {
        CSVReader reader = null;
        try
        {
            reader = new CSVReader("src/UserType/resources/employees.csv");
        }catch (IOException e)
        {
            System.out.println("File was not found!");
        }
        ArrayList<String> employeeData = new ArrayList<>();
        // If the reader is initialised
        if(reader != null){
            employeeData = reader.readAllFromCSV();
        }

        UserTypes employeeToParse;
        for(String employee : employeeData)
        {
            String[] split = employee.split(",");
            String userType = split[12];
            if(userType.contains("UserType.Employee")){
                employeeToParse = new Employee(employee);
            }else if(userType.contains("UserType.HR")){
                employeeToParse = new HR(employee);
            }else{
                employeeToParse = new Admin(employee);
            }
            listOfEmployees.add(employeeToParse);
        }




    }

    /**
     * Back up method that adds employee to the list, is called in the UserType.Employee class
     * @param employee
     */
    public void addEmployee(UserTypes employee){
        if(!listOfEmployees.contains(employee)){
                listOfEmployees.add(employee);
            try {
                writeToEmployeeData("employees.csv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Removes an employee from the list of employees and from the CSV file where their data is stored
     * @param employee
     */
    public void removeEmployee(UserTypes employee){
        listOfEmployees.remove(employee);
        try {
            writeToEmployeeData("employees.csv");
        } catch (IOException e) {
            System.out.println("There was an error writing to the csv File try again!");
        }
    }


    /**
     * Checks what type of user you are in order to give you access to the list of employees
     * @return
     */
    public ArrayList<UserTypes> getListOfEmployees(){
        return listOfEmployees;
    }

   /**
    * shows a specific employees info based on the user type
    * @param employee_ID
    * @return user
    */
    public UserTypes employeeInformation(int employee_ID){
        for(UserTypes user : listOfEmployees){
            if(user.getEmployee_ID() == employee_ID){
                return user;
            }
        }

        System.out.println("Employee not found.");
        return null;

    }

    /**
     * Writes employees data into a CSV file
     * @param fileName
     * @throws IOException
     */
    public void writeToEmployeeData(String fileName) throws IOException{

        ArrayList<String> write = new ArrayList<String>();
        CSVWriter CSvWriter = new CSVWriter("src/UserType/resources/" , fileName);

        for(UserTypes user : listOfEmployees){
            
            String currentUser = user.toCSV();
            write.add(currentUser);

        }

        CSvWriter.OverWriteData(write);
    }
}