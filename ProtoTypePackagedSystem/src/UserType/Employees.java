package UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;

public class Employees{
    // List of employees
    private ArrayList<UserTypes> listOfEmployees = new ArrayList<>();

    public Employees(ArrayList<UserTypes> users){
        this.listOfEmployees = users;
    }

    // Method adds employee to the list, is called in the UserType.Employee class
    public void addEmployee(UserTypes employee){
        if(listOfEmployees.contains(employee)){
            System.out.println("UserType.Employee already exists.");
        }else{
                listOfEmployees.add(employee);
        }
    }

    public void removeEmployee(UserTypes employee){
        listOfEmployees.remove(employee);
    }

    // Checks what type of user you are in order to give you access to the list of employees
    public ArrayList<UserTypes> getListOfEmployees(UserTypes users){
        if(users instanceof Admin){
        return listOfEmployees;
        }
        else if(users instanceof HR){
            return new ArrayList<>(listOfEmployees);
        }
        else{
            System.out.println("Access denied.");
            return new ArrayList<>(); // empty list (means nothing)
        }
    }

   // shows a specific employees info based on the user type
    public UserTypes employeeInformation(int employee_ID, UserTypes requestingUser) throws IOException {

        CSVReader reader = new CSVReader("src/UserType/resources/employees.csv");
        String data = null;
        data = reader.findLine(String.valueOf(employee_ID),2);

        UserTypes toReturn = new Employee(data);
            if(requestingUser instanceof Admin || requestingUser instanceof HR || toReturn.equals(requestingUser)){
                return toReturn;
            }
            else{
                System.out.println("Access denied.");
                return null;
            }

    }

    public void writeToEmployeeData(String fileName) throws IOException{
        ArrayList<String> write = new ArrayList<String>();
        CSVWriter CSvWriter = new CSVWriter("src/UserType/resources/" , fileName);

        for(UserTypes user : listOfEmployees){
            
            String currentUser = user.toCSV();
            write.add(currentUser);
        }

        CSvWriter.writeToCSV(write);
    }


}