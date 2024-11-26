package Login;

import FileHandler.CSVReader;
import UserType.Employees;
import UserType.UserTypes;

import java.io.IOException;

public class Logins {
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private CSVReader csvReader;
    //can now call methods from these classes

    public Logins() {
        try {
            csvReader = new CSVReader("src/Login/Logins.csv");
             // Assuming Employees class is initialized here
        } catch (IOException e) {
            System.out.println("Error initializing CSV reader: " + e.getMessage());
        }
    } // constructor that initializes csvReader and employees

    public boolean authenticateLogin(String username, String password) {
        try {
            // Use tupleFind to search for matching username and password
            String tuplePattern = username + "," + password;
            requestingUser = csvReader.tupleFind(tuplePattern, 2); // User type is in the 3rd column (index 2)
            if(requestingUser != null) { // If the specified tuple matches any logins in the logins.csv file
                System.out.println("Login! authenticated");
                return true;
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return false;
    }

    public String getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(String requestingUser) {
        this.requestingUser = requestingUser;
    }

    public String loginFailed() {
        return "Login has failed. Please try again.";
    } // Tells user they cannot continue
    }