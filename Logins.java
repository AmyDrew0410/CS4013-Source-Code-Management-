import java.util.Scanner;
import java.io.IOException;

public class Logins {

    private Employees employees;
    private UserTypes usertypes;
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private CSVHandler csvReader;
    //can now call methods from these classes

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
    }