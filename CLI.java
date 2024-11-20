import java.util.Scanner;

public class CLI {
    private Employee employee;
    private Employees employees;
    private PayslipHistory payslipHistory;
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private Logins logins;
    private UserTypes userType;
    //can now call methods from these classes

    private String username;
    private String password;
    //data fields

    public void login() {
        //login method
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username: ");
        username = scanner.nextLine(); // Takes what user inputs as the username

        System.out.println("Please enter your password: ");
        password = scanner.nextLine(); // Takes what user inputs as the password

        scanner.close(); // Closes scanner to save resources

        if (logins.authenticateLogin(username, password)) {
            System.out.println("Welcome, " + username + "!");
            CLI();
        } else {
            System.out.println(logins.loginFailed());
        }
    }

    public void CLI() {
        //in-app command line interface
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
                occupation.ascend();
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


