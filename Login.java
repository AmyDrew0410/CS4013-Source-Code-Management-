import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login{

    private String username;
    private String password;
    private String userType;
    private CSVHandler csvHandler;

    // Constructor to initialize the login object
    public Login(String username, String password, String userType, CSVHandler csvHandler) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.csvHandler = csvHandler;
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}
    // Getter methods to access the login details

    // Method to add a new login to the CSV
    public void addNewLogin() throws IOException {
        // Create an ArrayList containing the login details
        ArrayList<Object> toWrite = new ArrayList<>();
        toWrite.add(username);
        toWrite.add(password);
        toWrite.add(userType);

        // Pass the file path and the ArrayList to write the data
        csvHandler.writeToCSV("logins.csv", toWrite);  // Assuming the file path is "logins.csv"

        System.out.println("New login added for " + username);
    }
}