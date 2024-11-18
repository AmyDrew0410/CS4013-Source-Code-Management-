import java.util.Scanner;
import java.io.IOException;

public class Logins {

    private Employee employee;
    private Employees employees;
    private UserTypes usertypes;
    private PayslipHistory payslipHistory;
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private CSVHandler csvReader;
    //can now call methods from these classes

    private String username;
    private String password;
    private UserTypes userType;

    public Logins() {
        try {
            csvReader = new CSVHandler("logins.csv");
            employees = new Employees(); // Assuming Employees class is initialized here
        } catch (IOException e) {
            System.out.println("Error initializing CSV reader: " + e.getMessage());
        }
    } // constructor that initializes csvReader and employees

    public boolean authenticateLogin(String username, String password) {
        try {
            // Use tupleFind to search for matching username and password
            String tuplePattern = username + "," + password;
            String userTypeString = csvReader.tupleFind(tuplePattern, 2); // User type is in the 3rd column (index 2)

            if (userTypeString != null) {
                // Retrieve user type from Employees using the getUserType method
                requestingUser = usertypes.getUserType();
                return !requestingUser.equals("Unknown"); // If user type is found, return true
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return false;
    } // Method to authenticate login using the CSV file

    public String loginFailed() {
        return "Login has failed. Please try again.";
    } // Tells user they cannot continue

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        username = scanner.nextLine(); // Takes what user inputs as the username

        System.out.println("Please enter your password: ");
        password = scanner.nextLine(); // Takes what user inputs as the password

        scanner.close(); // Closes scanner to save resources

        if (authenticateLogin(username, password)) {
            System.out.println("Welcome, " + username + "!");
            CLI();
        } else {
            System.out.println(loginFailed());
        }
    }

    public void CLI() {
        Scanner scanner = new Scanner(System.in);

        // Now, use the user type that was retrieved from the getUserType method
        if (requestingUser.equals("Admin")) {
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "\n (D) Add a new employee \n (E) Delete an employee \n");

            String command = scanner.nextLine().toUpperCase();

            // Handle commands for Admin
            if (command.equals("A")) {
                employees.employeeInformation(employee.employee_ID, userType);
            } else if (command.equals("B")) {
                payslipHistory.printPayslipHistory();
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            } else if (command.equals("D")) {
                employees.addEmployee(userType);
            } else if (command.equals("E")) {
                employees.removeEmployee(userType);
            }
        } else if (requestingUser.equals("HR")) {
            // Handle commands for HR
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "(D) Promote an employee \n");

            String command = scanner.nextLine().toUpperCase();

            if (command.equals("A")) {
                employees.employeeInformation(employee.employee_ID, userType);
            } else if (command.equals("B")) {
                payslipHistory.printPayslipHistory();
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            } else if (command.equals("D")) {
                employee.ascend();
            }
        } else if (requestingUser.equals("Employee")) {
            // Handle commands for Employee
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list ");

            String command = scanner.nextLine().toUpperCase();

            if (command.equals("A")) {
                employees.employeeInformation(employee.employee_ID, userType);
            } else if (command.equals("B")) {
                payslipHistory.printPayslipHistory();
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            }
        }

        scanner.close();
    }
}