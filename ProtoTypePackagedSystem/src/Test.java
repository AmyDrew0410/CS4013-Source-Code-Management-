import FileHandler.*;
import Occupations.PromotionManager;

import java.io.IOException;

import java.util.ArrayList;

public class Test
{
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("src/UserType/resources/employees.csv"); // Generates reader to file
        CSVWriter writer = new CSVWriter("src/UserType/resources/employees.csv"); // Generates writer to file


        ArrayList<String> empData = reader.readAllFromCSV(); //
        PromotionManager manager = new PromotionManager(empData,writer);
        manager.manuallyPromoteEmployee("23368071");
    }
}
