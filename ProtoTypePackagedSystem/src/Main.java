import Menu.MainMenu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        try
        {
            MainMenu menu = new MainMenu();
            menu.run();
        }
        catch(RuntimeException e)
        {
            System.out.println("Something has gone horribly wrong and idk what");
            e.printStackTrace();
        }

    }
}
