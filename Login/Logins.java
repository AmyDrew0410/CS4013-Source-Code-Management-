package Login;

import CSVHandler.CSVReader;
import Occupations.OccupationMenu;
import PaymentProcess.PayslipHistory;
import UserType.Employee;
import UserType.Employees;
import UserType.UserTypes;

import java.util.Scanner;
import java.io.IOException;
import Login.CLI;

public class Logins {

    private Employee employee;
    private Employees employees;
    private UserTypes usertypes;
    private PayslipHistory payslipHistory;
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private CSVReader csvReader;
    private CLI cli;
    //can now call methods from these classes

    /**
     * this class contains the methods to either authenticate or fail the login.
     * The CSV is searched for the entered data and if it is there the login is authenticated.
     * If it's not present the login fails, and they can either try again or quit.
     * author : Evelyn Sadlier
     * student ID : 23367989
     */

    private String username;
    private String password;
    private UserTypes userType;

    /**constructor that initializes csvReader and employees*/
    public Logins() {
        try {
            csvReader = new CSVReader("logins.csv");
            employees = new Employees();
        } catch (IOException e) {
            System.out.println("Error initializing CSV reader: " + e.getMessage());
        }
    }

    /**method authenticateLogin() which checks the CSV file for the entered details. If present,
     * login is authenticated. If not, login fails.
     * @param username
     * @param password
     * @return
     */
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

    /**method loginFailed() which tells the user the login failed and gives them
     * the option to either quit or try again
     */
    public void loginFailed() {
        System.out.println("Login has failed.");
        System.out.println("Please choose an option : \n" +
                "(A) Try again \n (B) Quit");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().toUpperCase();

        if(command == "A"){
            cli.login();
        }
        else if (command == "B"){}
    } // Tells user they cannot continue and gives options to quit or try again


}