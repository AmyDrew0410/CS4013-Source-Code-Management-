import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logins {

    Employee employee;
    Employees employees;
    PayslipHistory payslipHistory;
    //can now call methods from these classes

    String userType;
    String usernameInput;
    String passwordInput;
    //data fields

    private List<Login> loginList = new ArrayList<>();
    //arraylist of logins

    public void addLogin(String username, String password, String userType) {
        loginList.add(new Login(username, password, userType));
    }//method to add login details to the list

    public String authenticateLogin(){
        return "Login authenticated. Welcome, " + employee.getFirst_Name() + " " + employee.getLast_Name() + ".";
    }//authenticates login to continue

    public String loginFailed(){
        return "Login has failed. please try again.";
    }//tells user they cannot continue

    public String giveAccess(){
        Scanner scanner = new Scanner(System.in);
        //creates a scanner object

        System.out.println("What access level would you like to give? \n Please choose one of the following: \n" +
        "(A)dmin \n (H)R Manager\n (E)mployee");
        //gives user options
        String command = scanner.nextLine().toUpperCase();
        //makes whatever they choose uppercase
        if (command.equals("A")){
            return "Admin";
        }
        else if (command.equals("H")){
            return "HR Manager";
        }
        else if (command.equals("E")){
            return "Employee";
        }
        scanner.close();
        return "";
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        //creates a scanner object

        System.out.println("Please enter your username: ");
        //asks user to input username

        usernameInput = scanner.nextLine();
        //takes what user inputs as the username

        System.out.println("Please enter your password: ");
        //asks user to input password

        passwordInput = scanner.nextLine();
        //takes what user inputs as the password

        scanner.close();
        //closes scanner to save resources
    }

     for (Login login : loginList) {
        if (login.getUsername().equals(usernameInput) && login.getPassword().equals(passwordInput)) {
            authenticateLogin();

            Scanner scanner = new Scanner(System.in);
            //creates a scanner object

            System.out.println("What would you like to do?");
            System.out.println("(S)ee my details \n (R)eview payslip history \n (V)iew employee list");
            if(userType == "Admin"){
                System.out.print("(A)dd a new employee \n (D)elete an employee \n");
            }
            if(userType == "HR Manager"){
                System.out.println("(P)romote an employee \n");
            }
            String command = scanner.nextLine().toUpperCase();
            if(command.equals("S")){
                employees.employeeInformation(employee.getEmployee_ID(),employee.user_Type(), String.valueOf(employee.getEmployee_ID()));
            }
            if(command.equals("R")){
                payslipHistory.getPayslipHistory();
            }
            if(command.equals("V")){
                employees.getListOfEmployees();
            }
            if(command.equals("A")){
                employees.addEmployee(employee);
            }
            if(command.equals("D")){
                employees.removeEmployee(employee);
            }
            if(command.equals("P")){
                employee.ascend();
            }

        }
        else{loginFailed();}
    }

}