package Login;

import java.io.IOException;
import java.util.ArrayList;
import FileHandler.CSVWriter;

public class Login{

    private int username;
    private String password;
    private String userType;
    private CSVWriter writer;

    // Constructor to initialize the login object
    public Login(int username, String password, String userType) throws IOException {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.writer = new CSVWriter("src/Login/Logins.csv");
    }

    public int getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}
    // Getter methods to access the login details

    // Method to add a new login to the CSV
    public void addNewLogin() throws IOException {
        // Create an ArrayList containing the login details
        ArrayList<String> toWrite = new ArrayList<>();
        String data = String.format("%s,%s,%s",username,password,userType);
        toWrite.add(data);

        // Pass the file path and the ArrayList to write the data
        writer.writeToCSV(toWrite);  // Assuming the file path is "logins.csv"

        System.out.println("New login added for " + username);
    }
}