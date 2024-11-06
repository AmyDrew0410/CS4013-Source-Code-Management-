import java.util.ArrayList;
import java.util.List;

public class Employees{
    // List of employees
    private List<Employee> listOfEmployees = new ArrayList<>();

    // Method adds employee to the list, is called in the Employee class
    public void addEmployee(Employee employee){
        if(listOfEmployees.contains(employee)){
            System.out.println("Employee already exists.");
        }else{
            listOfEmployees.add(employee);
        }
    }

    public void removeEmployee(Employee employee){
        listOfEmployees.remove(employee);
    }

    public List<Employee> getListOfEmployees(){
        return new ArrayList<>(listOfEmployees);
    }
}