package Login;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;
import Occupations.OccupationMenu;
import Occupations.PromotionManager;
import PaymentProcess.Payslip;
import UserType.Employee;
import UserType.Employees;
import PaymentProcess.PayslipHistory;
import UserType.UserTypes;


import java.io.IOException;
import java.util.Scanner;

public class CLI
{
    private Employee employee;
    private Employees employees;
    private PayslipHistory payslipHistory;
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private Logins logins = new Logins();
    private UserTypes userType;
    private OccupationMenu menu;
    private Scanner scanner;
    //can now call methods from these classes

    private String username;
    private String password;
    //data fields

    public void login() throws IOException {
        //login method
        scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        username = scanner.nextLine(); // Takes what user inputs as the username

        System.out.println("Please enter your password: ");
        password = scanner.nextLine(); // Takes what user inputs as the password



        if (logins.authenticateLogin(username, password)) {
            System.out.println("Welcome, " + username + "!");
            CLI();
        } else {
            logins.loginFailed();
        }
    }


    public void CLI() throws IOException {
        //in-app command line interface
        requestingUser = logins.getRequestingUser();
        // Now, use the user type that was retrieved from the getUserType method
        if (requestingUser.equals("Admin")) {
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "\n (D) Add a new employee \n (E) Delete an employee \n");

            String command = scanner.nextLine().toUpperCase();

            // Handle commands for Admin
            if (command.equals("A")) {
                employees.employeeInformation(employee.getEmployee_ID(), userType);
            } else if (command.equals("B")) {
                payslipHistory.printPayslipHistory();
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            } else if (command.equals("D")) {
                employees.addEmployee(userType);
            } else if (command.equals("E")) {
                employees.removeEmployee(userType);
            }
        }else if (requestingUser.equals("HR")) {
            // Handle commands for HR
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list " +
                    "\n (D) Manually Promote an employee \n (E) Perform Yearly Upgrade");

            String command = scanner.nextLine().toUpperCase();

            if (command.equals("A")) {
                employees.employeeInformation(employee.getEmployee_ID(), userType);
            } else if (command.equals("B")) {
                Payslip.getPayslipData(username);
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            } else if (command.equals("D")) {
                System.out.println("Enter the EmployeeID of the employee you want to manually promote");
                String employeeID = scanner.next();

                // Create CSVReader and CSVWriter instances
                CSVReader csvReader = new CSVReader("src/UserType/resources/employees.csv"); // Specify the correct path
                CSVWriter csvWriter = new CSVWriter("src/UserType/resources/employees.csv"); // Specify the correct path

                // Create PromotionManager instance
                PromotionManager promotionManager = new PromotionManager(csvReader.readAllFromCSV(), csvWriter);

                try {
                    promotionManager.manuallyPromoteEmployee(employeeID);
                    System.out.println("Employee " + employeeID + " has been manually promoted.");
                } catch (IOException e) {
                    System.out.println("Error promoting employee: " + e.getMessage());
                }
            } else if (command.equals("E")) {
                // Create CSVReader and CSVWriter instances
                CSVReader csvReader = new CSVReader("path/to/employees.csv"); // Specify the correct path
                CSVWriter csvWriter = new CSVWriter("path/to/employees.csv"); // Specify the correct path

                // Create PromotionManager instance
                PromotionManager promotionManager = new PromotionManager(csvReader.readAllFromCSV(), csvWriter);

                try {
                    promotionManager.performYearlyUpgrade();
                    System.out.println("Yearly upgrade completed successfully.");
                } catch (IOException e) {
                    System.out.println("Error performing yearly upgrade: " + e.getMessage());
                }
            }
        } else if (requestingUser.equals("Employee")) {
            // Handle commands for Employee
            System.out.println("What would you like to do?");
            System.out.println("(A) See my details \n (B) Review payslip history \n (C) View employee list ");

            String command = scanner.nextLine().toUpperCase();

            if (command.equals("A")) {
                employees.employeeInformation(employee.getEmployee_ID(), userType);
            } else if (command.equals("B")) {
                payslipHistory.printPayslipHistory();
            } else if (command.equals("C")) {
                employees.getListOfEmployees(userType);
            }
        }

        scanner.close();
    }
}


