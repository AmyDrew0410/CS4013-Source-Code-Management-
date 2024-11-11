import java.util.ArrayList;
import java.util.List;

public class Employees{
    // List of employees
    private List<UserTypes> listOfEmployees = new ArrayList<>();

    // Method adds employee to the list, is called in the Employee class
    public void addEmployee(UserTypes employee){
        if(listOfEmployees.contains(employee)){
            System.out.println("Employee already exists.");
        }else{
            listOfEmployees.add(employee);
        }
    }

    public void removeEmployee(UserTypes employee){
        listOfEmployees.remove(employee);
    }

    // Checks what type of user you are in order to give you access to the list of employees
    public List<UserTypes> getListOfEmployees(UserTypes users){
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

}