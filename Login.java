import java.util.Scanner;

public class Login{

    private String username;
    private String password;

    public Login(String username, String password, String userType){
        this.username = username;
        this.password = password;
        this.userType = userType;
    }//constructor that creates a login

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}
    // Getter methods to access the login details
}