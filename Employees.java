package UserType;

import java.io.IOException;
import java.util.ArrayList;

import CSVHandler.CSVWriter;

public class Employees{
    // List of employees
    protected ArrayList<UserTypes> listOfEmployees = new ArrayList<>();

    public Employees(ArrayList<UserTypes> users){
        this.listOfEmployees = users;
    }

    // Back up method that adds employee to the list, is called in the UserType.Employee class
    public void addEmployee(UserTypes employee){
        if(listOfEmployees.contains(employee)){
            System.out.println("UserType.Employee already exists.");
        }else{
                listOfEmployees.add(employee);
        }
    }

    //Method adds users to the list based on their level of access
    /*public void addEmployee(UserTypes employee){
        if(listOfEmployees.contains(employee)){

            System.out.println("Employee already exists.");

        }else{
            if( Admin || user instanceof HR){

                listOfEmployees.add(employee);
                System.out.println("Employee added.");

            }else{

                System.out.println("Unauthorized to make changes.");

            }
        }
    }*/
    
    public void removeEmployee(UserTypes employee){
        listOfEmployees.remove(employee);
    }

    //Method removes users based on their level of access
    /*public void removeEmployee(UserTypes employee, UserTypes user){
        if( !listOfEmployees.contains(employee)){

            System.out.println("Employee not found.");

        }else{
            if(user instanceof Admin || user instanceof HR){

                listOfEmployees.remove(employee);
                System.out.println("Employee removed.");

            }else{

                System.out.println("Unauthorized to make changes.");

            }
        }
    }*/


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
        CSVWriter CSvWriter = new CSVWriter("src/UserType/EmployeeData/" , fileName);

        for(UserTypes user : listOfEmployees){
            
            String currentUser = user.toCSV();
            write.add(currentUser);

        }

        CSvWriter.writeToCSV(write);
    }

    public void add(UserTypes employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public void remove(UserTypes employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }


}