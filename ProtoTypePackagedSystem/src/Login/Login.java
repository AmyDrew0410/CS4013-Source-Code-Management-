package Login;

import java.io.IOException;
import java.util.ArrayList;
import CSVHandler.CSVWriter;

public class Login{

    private String username;
    private String password;
    private String userType;
    private CSVWriter writer;

    // Constructor to initialize the login object
    public Login(String username, String password, String userType, CSVWriter csvWriter) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.writer = csvWriter;
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
        writer.writeToCSV(toWrite, "logins.csv");

        System.out.println("New login added for " + username);
    }
}