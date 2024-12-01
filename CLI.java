package Login;

import FileHandler.CSVWriter;
import Occupations.PromotionManager;
import PaymentProcess.PaymentCycleManager;
import PaymentProcess.Payslip;
import UserType.Employee;
import UserType.Employees;
import UserType.PartTime;
import UserType.UserTypes;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CLI
{
    private UserTypes employee;
    boolean run = true;
    private  Employees employees;
    private final Logins logins = new Logins();
    private Scanner scanner;
    private PromotionManager promotionManager;
    private LocalDate currentDate = LocalDate.now();
    private int currentDay = currentDate.getDayOfMonth();
    private int currentMonth = currentDate.getMonthValue();
    private int currentYear = currentDate.getYear();
    //can now call methods from these classes

    private String username;
    //data fields

    /**
     * Method that prompts the user to login and runs an authentication check to allow the user access to the
     * System if they're authenticated
     * @throws IOException Throws an input output exception for invalid inputs
     */
    public void login() throws IOException {
        //login method
        scanner = new Scanner(System.in); // Scanner is initialised

        // Prompt to log-in
        System.out.println("==========Login=============");
        System.out.println("Please enter your username: ");

        username = scanner.nextLine(); // Takes what user inputs as the username

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine(); // Takes what user inputs as the password


        if (logins.authenticateLogin(username, password)) {
            System.out.println("Welcome, " + username + "!");
            CLIMenu();
        } else {
            Login.loginFailed();
        }
    }


    /**
     * Method that holds the Command line interface menu for each employee Type
     * @throws IOException throws an input output exception to catch any invalid inputs not handled in the method
     */
    public void CLIMenu() throws IOException {
        // in-app command line interface
        // Store the user type (Admin, HR, Employee)
        String requestingUser = logins.getRequestingUser(); // Stores the requesting users userType for access handling
        CSVWriter employeeWriter = new CSVWriter("src/UserType/resources/employees.csv"); // Instantiates a writer to employees for the class
        promotionManager = new PromotionManager(employeeWriter); // Creates a promotion manager object for later

        // Now, use the user type that was retrieved from the getUserType method
        while (run) // A while true loop that runs into run is set to false with a Q command
        {
            employees = new Employees(); // Instantiates an employees object that holds the details of every employee
            switch (requestingUser) // Switch case for access handling
            {
                case "Admin" -> adminMenu(); // If you're an Admin access the admin menu
                case "HR" -> hrMenu(); // If you're an HR officer access the hr menu
                case "Employee", "PartTime" -> employeeMenu(); // If you're a partTime or full time employee access the employee menu
            }
        }
        scanner.close();
    }

    /**
     * A menu that handles all functionality for admin users
     */
    public void adminMenu(){
        // Prompts the user of possible option choices
        System.out.println("============================");
        System.out.println("What would you like to do?");
        System.out.println("============================");
        System.out.println("""
                            S)ee employee details   R)eview payslip history\s
                            V)iew employee list \
                                A)dd a new employee\s
                            D)elete an employee     P)romote Employee\s
                            T)ime Simulation\s
                            G)et todays Date\s
                            Q)uit""");

        String command = scanner.nextLine().toUpperCase(); // Reads and parses users next choice

        // Handle commands for Admin
        if (command.equalsIgnoreCase("S")) // Shows employee details for a specified employee
        {
            System.out.println("Please enter the user ID of the employee who's details you wish to see if you wish to see your own type 0:");
            int ID = scanner.nextInt(); // Reads wanted employees employee ID
            scanner.nextLine(); // Consume newline

            employee = null; // Reset before attempt
            try {
                if (ID != 0) // If the value entered is greater than 0 search for the specified employee
                {
                    employee = employees.employeeInformation(ID);
                } else  // If the value is 0 look for the users ID
                {
                    employee = employees.employeeInformation(Integer.parseInt(username));
                }
            } catch (NullPointerException | NumberFormatException e) // Handles the cases where the method returns null or a number isn't an argument
            {
                System.out.println("That employee Doesn't exist!");
            }

            // Only print if employee is not null
            if (employee != null) {
                System.out.println(employee);
            }
        }
        else if (command.equalsIgnoreCase("R"))  // Reviews employees payslip history
        {
            // Prompts the admin to specify the employee he wants to see the payslip history of
            System.out.println("Please enter the user ID of the employee who's details you wish to see if you wish to see your own type 0:");
            String employeeID = scanner.next(); // Reads the next ID entered as a string
            if (employeeID.equalsIgnoreCase("0")) // If the string is 0 then get the users PaySlip History
            {
                try // Try catch block to catch if the user isn't found in the chosen payslip history
                {
                    Payslip.getPayslipData(username);
                } catch (IOException e) {
                    System.out.println("The user couldn't be found");
                }
            } else // Else case handles searching for an employee that isn't the user
            {
                try {
                    Payslip.getPayslipData(employeeID);
                } catch (IOException e) {
                    System.out.println("The user count be found!");
                }
            }
        }
        else if (command.equalsIgnoreCase("V")) // Prints out all employees currently working in the company
        {
            int count = 0;
            for (UserTypes user : employees.getListOfEmployees()) // For every employee in the list of employees
            {
                count++;
                System.out.printf("***************** Employee %d :**********************\n", count);
                System.out.println(user.toString()); // Print out the employee
            }
        }
        else if (command.equalsIgnoreCase("A")) // Handles the addition of an employee to the system
        {
            System.out.println("=====================");
            System.out.println("Creating an Employee");
            System.out.println("======================");

            // Constructor for employee based off data
            UserTypes employeeToAdd = newEmployee();
            employees.addEmployee(employeeToAdd);

        }
        else if (command.equalsIgnoreCase("D")) // Handles the deletion of an employee from the system
        {
            System.out.println("=====================");
            System.out.println("Deleting an Employee");
            System.out.println("======================");
            UserTypes employeeToKill = killEmployee();
            if (employeeToKill != null) // If the employee to kill is found
            {
                employees.removeEmployee(employeeToKill); // remove it from the arraylist
            }
            else
            {
                System.out.println("The employee wasn't found!"); // Other-wise print this
            }
        } else if (command.equalsIgnoreCase("T")) // Handles time simulator
        {
            // Prompts user input
            System.out.println("================================================================");
            System.out.println("Type 25 to set the current date to the next instance of the 25th");
            System.out.println("Type year to set the current date to October 1st of the next year");

            String choice = scanner.next(); // Reads the next string passed through
            dialationChoice(choice); // Dilates the time based off the user choice
        } else if (command.equalsIgnoreCase("G"))  // gets the current date
        {
            System.out.printf("========== Todays Date : %s ========== \n", currentDate.toString());
        } else if (command.equalsIgnoreCase("Q")) // quits the program
        {
            run = false;
        }
    }

    /**
     * Handles all commands for an HR officer
     */
    public void hrMenu(){
        // Handle commands for HR
        System.out.println("============================");
        System.out.println("What would you like to do?");
        System.out.println("============================");
        System.out.println("""
                            S)ee employee details   R)eview payslip history\s
                            A)dd a new employee    D)elete an employee
                            P)romote Employee   T)ime Simulation
                            G)et todays Date   Q)uit""");


        String command = scanner.nextLine().toUpperCase();

        if (command.equalsIgnoreCase("S")) // Shows employee details for this specified employee
        {
            int ID = Integer.parseInt(username);
            employee = null; // Reset before attempt
            try {
                employee = employees.employeeInformation(ID);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("That employee Doesn't exist!");
            }

            // Only print if employee is not null
            if (employee != null) {
                System.out.println("======== Your Details =========");
                System.out.println(employee);
            }
        } else if (command.equalsIgnoreCase("R")) //review your payslips
        {
            try
            {
                Payslip.getPayslipData(username);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }
        else if (command.equalsIgnoreCase("V")) // View the list of employees
        {
            int count = 0;
            for (UserTypes user : employees.getListOfEmployees()) // For every employee in the list of employees
            {
                count++;
                System.out.printf("***************** Employee %d :**********************\n", count);
                System.out.println(user.toString()); // Print out the employee
            }
        }
        else if (command.equalsIgnoreCase("A")) // If the command equals 'A' add an employee
        {
            // Prompt for user input
            System.out.println("=====================");
            System.out.println("Creating an Employee");
            System.out.println("======================");

            // Constructor for employee based off data
            UserTypes employeeToAdd = newEmployee();
            employees.addEmployee(employeeToAdd);

        }
        else if (command.equalsIgnoreCase("D")) // Implements employee removal
        {
            System.out.println("=====================");
            System.out.println("Deleting an Employee");
            System.out.println("======================");
            UserTypes employeeToKill = killEmployee();
            if (employeeToKill != null) {
                employees.removeEmployee(employeeToKill);
            } else {
                System.out.println("The employee wasn't found!");
            }
        }
        else if (command.equalsIgnoreCase("P")) // Handles manual promotion of an employee
        {
            // Reads user input
            System.out.println("Please enter the ID of the employee to be promoted to the next job title in the department");
            int employeeID = scanner.nextInt();
            try // Try catch block to handle invalid ID exceptions for promotion manager
            {
                promotionManager.manuallyPromoteEmployee(employeeID);
            } catch (IOException e) {
                System.out.println("An error occured while processing the promotion!");
            }

        } else if (command.equalsIgnoreCase("T")) // Time simulation
        {
            System.out.println("================================================================");
            System.out.println("Type 25 to set the current date to the next instance of the 25th");
            System.out.println("Type year to set the current date to October 1st of the next year");
            String choice = scanner.next();
            dialationChoice(choice);
        } else if (command.equalsIgnoreCase("G")) // Gets todays' date
        {
            System.out.printf("========== Todays Date : %s ========== \n", currentDate.toString());
        }
        else if (command.equalsIgnoreCase("Q")) // Quits the program
        {
            run = false;
        }
    }

    /**
     * Handles all commands for a part or full time employee
     */
    public void employeeMenu(){
        // Handle commands for Employee
        System.out.println("What would you like to do?");
        System.out.println("S)ee my details R)eview payslip history \n");

        String command = scanner.nextLine().toUpperCase();

        if (command.equals("S")) {
            int ID = 0;
            employee = null; // Reset before attempt
            try {
                employee = employees.employeeInformation(ID);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("That employee Doesn't exist!");
            }

            // Only print if employee is not null
            if (employee != null) {
                System.out.println("======== Your Details =========");
                System.out.println(employee);
            }
        } else if (command.equals("P")) {
            try {
                Payslip.getPayslipData(username);
            } catch (IOException e) {
                System.out.println("We couldn't find your ID in this payslip cycle!");
            }
        }
    }

    /**
     * Instantiates a new employee object and adds it to the employee list.
     * @return UserTypes object that represents the new employee
     */
    public UserTypes newEmployee(){
        //First name
        System.out.println("Enter a first Name :");
        String name = scanner.nextLine();
        //Sur name
        System.out.println("Enter a surname : ");
        String surName = scanner.nextLine();
        // Employee ID
        System.out.println("Enter an EmployeeID : ");
        int employeeID = scanner.nextInt();
        // Email Address
        System.out.println("Enter an Email: ");
        String email = scanner.next();
        // Martial status
        System.out.println("Are they married? Y/N");
        boolean married = scanner.next().equalsIgnoreCase("Y");
        // Phone number
        System.out.println("Phone Number : ");
        String phoneNumber = scanner.next();
        // PPSN
        System.out.println("PPSN : ");
        String ppsn = scanner.next();
        // Union
        System.out.println("Are they a part of a union if so type A for UnionA and B for UnionB :");
        String union = scanner.next();
        // Password
        System.out.println("Enter a password for this account:  ");
        String password = scanner.next();
        // Handles part-time and full time employees
        System.out.println("Are you they a P)art time or F)ull time employee: ");
        String choice = scanner.next();
        // If the choice is P
        if(choice.equalsIgnoreCase("P")){
            System.out.println("Enter how many hours they work per week:"); // Read their hours worked per week
            double hours = scanner.nextDouble(); // Store it
            return new PartTime(name,surName,employeeID,phoneNumber,email,married,ppsn,"UserType.PartTime",hours,union,password); // Return a new part-time object
        }else if(choice.equalsIgnoreCase("F")){
            return new Employee(name,surName,employeeID,phoneNumber,email,married,ppsn,union,password); // If the command = F create a new full time employee
        }
        return null; // If it's an invalid input return null
    }

    public UserTypes killEmployee()
    {

        System.out.println("Please enter the employee to be removed :");
        int employeeID = scanner.nextInt();
        for (UserTypes emp : employees.getListOfEmployees()) {
            if (emp.getEmployee_ID() == employeeID) {
                return emp;
            }
        }
        return null;
    }

    public void dialationChoice(String choice){
        if(choice.equalsIgnoreCase("25"))
        {
            timeDialateCycle();
        }else if(choice.equalsIgnoreCase("year")){
            timeDialateYear();
        }else{
            System.out.println("Incorrect input type Try Again");
        }
    }


    public void timeDialateCycle(){
        this.currentDay += 1;

        if (currentDay > 25) {
            this.currentMonth += 1;
            this.currentDay = 25;

            if (currentMonth > 12) {
                this.currentYear += 1;
                this.currentMonth = 1;
            }
        }

        this.currentDate = LocalDate.of(currentYear, currentMonth, currentDay);

        try {
            String monthString = switch (currentMonth) {
                case 1 -> "January";
                case 2 -> "February";
                case 3 -> "March";
                case 4 -> "April";
                case 5 -> "May";
                case 6 -> "June";
                case 7 -> "July";
                case 8 -> "August";
                case 9 -> "September";
                case 10 -> "October";
                case 11 -> "November";
                case 12 -> "December";
                default -> "Invalid month";
            };

            try {
                PaymentCycleManager manager = new PaymentCycleManager();
                manager.processCycle(monthString, currentYear, currentDate);
                this.currentDate = LocalDate.of(currentYear, currentMonth, currentDay);
            } catch (IOException e) {
                System.out.println("Tardis ran into an error returning to root time!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred exiting the time dilation!");
        }
    }

    public void timeDialateYear()
    {
        while(currentMonth !=10) // Iterates through cycle until we reach the 10th month
        {
            timeDialateCycle();
        }
        try
        {
            promotionManager.performYearlyUpgrade();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


