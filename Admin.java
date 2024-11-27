package UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import FileHandler.CSVWriter;

public class Admin extends UserTypes{

    
    public Admin(String first_Name, String last_Name, int employee_ID, String phone_Number, String email, Boolean marital_Status, Employees employees, String PPSN, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, employees, PPSN, "UserType.Admin", unionFees);
    }

    public Admin(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, Boolean marital_Status, String PPSN, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, PPSN, "UserType.Admin", unionFees);
    }

    public Admin(String data){
        super(data);
    }

    public String getFirst_Name(){
        return first_Name;
    }

    public Boolean getMarital_Status(){
        return marital_Status;
    }

    public String getLast_Name(){
        return last_Name;
    }

    public int getEmployee_ID(){
        return employee_ID;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone_Number(){
        return phone_Number;
    }

    public String getPPSN(){
        return PPSN;
    }

    @Override
    public boolean AccessEmployeeList(){
        return true;
    }
    
    @Override
    public void addEmployee(UserTypes employee, Employees listOfEmployees){
        if(listOfEmployees.equals(employee)){
            System.out.println("UserType.Employee already exists.");
        }else{
                listOfEmployees.add(employee);
        }
    }

    @Override
    public void removeEmployee(UserTypes employee, Employees listOfEmployees){
        // Remove the specified employee From the list
        listOfEmployees.remove(employee);
        try
        {
            CSVWriter writer = new CSVWriter("src/UserType/resources/employees.csv");
            ArrayList<UserTypes> empData = listOfEmployees.getListOfEmployees();
            ArrayList<String> toWrite = new ArrayList<>();
            for(UserTypes emp : empData)
            {
                toWrite.add(emp.toCSV());
            }
        }catch(IOException | InputMismatchException e)
        {
            if(e instanceof FileNotFoundException){
                System.err.println("The file Could Not be found! ");
            }else{
                System.err.println("The employee ID doesn't exist in this file");
            }
        }

    }


    @Override
    public String toString(){
        return super.toString();
    }


}