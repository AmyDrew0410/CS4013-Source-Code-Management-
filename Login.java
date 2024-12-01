package Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import FileHandler.CSVReader;
import FileHandler.CSVWriter;

public class Login{

    private int username;
    private String password;
    private String userType;
    private CSVWriter writer;
    private static CLI cli = new CLI();

    // Constructor to initialize the login object
    public Login(int username, String password, String userType) throws IOException {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.writer = null;
    }

    public int getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}
    // Getter methods to access the login details

    // Method to add a new login to the CSV
    public void addNewLogin() throws IOException {
        writer = new CSVWriter("src/Login/Logins.csv");
        CSVReader reader = new CSVReader("src/Login/Logins.csv");
        // Create an ArrayList containing the login details
        ArrayList<String> toWrite = reader.readAllFromCSV();
        String data = String.format("%s,%s,%s",username,password,userType);
        toWrite.add(data);

        // Pass the file path and the ArrayList to write the data
        writer.writeToCSV(toWrite);  // Assuming the file path is "logins.csv"

        System.out.println("New login added for " + username);
    }

    public static void loginFailed() {
        System.out.println("Login has failed.");
        System.out.println("Please choose an option : \n" +
                "T)ry again \nQ)uit");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().toUpperCase();

        if(command.equalsIgnoreCase("T")){
            try {
                cli.login();
            } catch (IOException e) {
                System.out.println("An error occured while reading User input!");
            }
        }
        else if (command.equalsIgnoreCase("Q")){
            System.out.println("Exiting the system!");
        }else{
            System.out.println("Incorrect input aborting process!");
        }
    }
}