package Menu;

import Login.CLI;
import Login.Logins;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    public void run() throws IOException {
        CLI cli = new CLI();
        cli.login();
    }
}
