package UserType;

import java.io.IOException;
import java.util.ArrayList;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;

public class Employees{
    // List of employees
    protected ArrayList<UserTypes> listOfEmployees = new ArrayList<>();

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

    // Back up method that adds employee to the list, is called in the UserType.Employee class
    public void addEmployee(UserTypes employee){
        if(listOfEmployees.contains(employee)){
            System.out.println("UserType.Employee already exists.");
        }else{
                listOfEmployees.add(employee);
        }
    }

    
    public void removeEmployee(UserTypes employee){
        listOfEmployees.remove(employee);
        try {
            writeToEmployeeData("employees.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Checks what type of user you are in order to give you access to the list of employees
    public ArrayList<UserTypes> getListOfEmployees(){
        return listOfEmployees;
    }

   // shows a specific employees info based on the user type
    public UserTypes employeeInformation(int employee_ID, UserTypes requestingUser){
        for(UserTypes user : listOfEmployees){
            if(user.getEmployee_ID() == employee_ID){

                if(requestingUser instanceof Admin || requestingUser instanceof HR || user.equals(requestingUser)){

                    return user;

                }
                else{

                    System.out.println("Access denied.");
                    return null;

                }
            }
        }

        System.out.println("Employee not found.");
        return null;

    }

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