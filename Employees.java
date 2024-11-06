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

    // Checks what type of user you are in order to give you access to the list of employees
    public List<Employee> getListOfEmployees(){
        if(role.equals("Admin")){
        return listOfEmployees;
        }
        else if(role.equals("HR")){
            return new ArrayList<>(listOfEmployees);
        }
        else{
            System.out.println("Access denied.");
        }
    }

    // returns the specific employee info if the Admin, HR or the itself requests the info
    public Employee employeeInformation(int employee_ID, String role, String requestedEmployee_ID){
        for(Employee employee : listOfEmployees){
            if(employee.getEmployee_ID() == employee_ID){

                if(role.equals("HR") || role.equals("Admin") || employee_ID == requestedEmployee_ID){
                    return employee;
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

}