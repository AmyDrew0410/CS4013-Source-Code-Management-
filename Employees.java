import java.util.ArrayList;

public class Employees{
    // List of employees
    ArrayList<Employee> ListOfEmployees = new ArrayList<Employee>();

    // Method adds employee to the list, is called in the Employee class
    public void addEmployee(Employee employee){
        ListOfEmployees.add(employee);
    }
}