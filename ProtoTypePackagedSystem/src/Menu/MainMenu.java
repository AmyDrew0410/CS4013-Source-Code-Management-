package Menu;
import Login.Logins;
import Login.CLI;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainMenu {
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