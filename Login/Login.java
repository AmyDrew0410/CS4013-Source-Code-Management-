package Login;

import java.io.IOException;
import java.util.ArrayList;
import CSVHandler.CSVWriter;

public class Login{

    private String username;
    private String password;
    private String userType;
    private CSVWriter writer;

    /**
     * this class is to construct a login using a username, password and user type.
     * It also adds any new logins to the csv file by passing the path and an arraylist.
     * author : Evelyn Sadlier
     * student ID : 23367989
     */

    /**Constructor to initialize the login object*/
    public Login(String username, String password, String userType, CSVWriter csvWriter) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.writer = csvWriter;
    }

    /**Getter methods to access the login details*/
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}


    /**Method to add a new login to the CSV*/
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