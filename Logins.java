package Login;

import FileHandler.CSVReader;

import java.io.IOException;

public class Logins {
    private String requestingUser; // Store the user type (Admin, HR, Employee)
    private CSVReader csvReader;
    //can now call methods from these classes

    public Logins() {
        csvReader = new CSVReader("src/Login/Logins.csv");
        // Assuming Employees class is initialized here
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

    }