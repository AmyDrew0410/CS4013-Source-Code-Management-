package Menu;
import Login.Logins;
import Login.CLI;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * this class runs the CLI class and also gets the date when it is run,
 * which must be used in seperate classes.
 * author : Evelyn Sadlier
 * student ID : 23367989
 */

public class MainMenu {
    Scanner in = new Scanner(System.in);
    LocalDate date = LocalDate.now();
    public void run()
    {
        CLI obj = new CLI();
        obj.login();

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}