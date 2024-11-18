import java.util.Scanner;
import java.io.IOException;

public class Logins {

    private Employee employee;
    private Employees employees;
    private PayslipHistory payslipHistory;
    private UserTypes usertype;
    private CSVHandler csvReader;
    private Occupation occupation;
    //can now call methods from these classes

    private String username;
    private String password;
    private UserTypes requestingUser;

    //data fields

    public Logins() {
        try {
            csvReader = new CSVHandler("logins.csv");
        } catch (IOException e) {
            System.out.println("Error initializing CSV reader: " + e.getMessage());
        }
    }//constructor that initialises csv reader

    public void login() {
        Scanner scanner = new Scanner(System.in);
        //creates a scanner object

        System.out.println("Please enter your username: ");
        //asks user to input username

        username = scanner.nextLine();
        //takes what user inputs as the username

        System.out.println("Please enter your password: ");
        //asks user to input password

        password = scanner.nextLine();
        //takes what user inputs as the password

        scanner.close();
        //closes scanner to save resources

        authenticateLogin(username, password);

        if (authenticateLogin(username, password)) {
            System.out.println("Welcome, " + username + "!");
            CLI();
        }
        else{
            loginFailed();
        }
    }

    public boolean authenticateLogin(String username, String password) {
        try {
            // Use tupleFind to search for matching username and password
            String tuplePattern = username + "," + password;
            String userTypeString = csvReader.tupleFind(tuplePattern, 2); // User type is the 3rd column (index 2)

            if (userTypeString != null) {
                requestingUser = UserTypes.(userTypeString.toUpperCase());
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return false;
    }// Method to authenticate login using the CSV file

    public String loginFailed(){
        return "Login has failed. please try again.";
    }//tells user they cannot continue

    public void CLI() {
        Scanner scanner = new Scanner(System.in);
        //creates a scanner object

        if (requestingUser instanceof Admin) {

            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "\n (D) Add a new employee \n (E) Delete an employee \n");

            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "A" -> employees.employeeInformation(employee.employee_ID, requestingUser);
                case "B" -> payslipHistory.printPayslipHistory();
                case "C" -> employees.getListOfEmployees(requestingUser);
                case "D" -> employees.addEmployee(requestingUser);
                case "E" -> employees.removeEmployee(requestingUser);
                default -> System.out.println("Invalid command.");
            }
        }
        else if (requestingUser instanceof HR) {
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "(D) Promote an employee \n");

            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "A" -> employees.employeeInformation(employee.employee_ID, requestingUser);
                case "B" -> payslipHistory.printPayslipHistory();
                case "C" -> employees.getListOfEmployees(requestingUser);
                case "D" -> occupation.ascend();
                default -> System.out.println("Invalid command.");
            }
        }
        else {
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list ");

            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "A" -> employees.employeeInformation(employee.employee_ID, requestingUser);
                case "B" -> payslipHistory.printPayslipHistory();
                case "C" -> employees.getListOfEmployees(requestingUser);
                default -> System.out.println("Invalid command.");
            }
        }

        }
        }
    }

}