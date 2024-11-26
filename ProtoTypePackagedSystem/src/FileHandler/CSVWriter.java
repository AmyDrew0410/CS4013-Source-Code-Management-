package FileHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * Classes purpose is to create a buffered writer for a specified file path and contains methods
 * for writing and overwriting to specified csv file
 * @author Ben Bastianelli 23368071
 * @version 1.2
 */
public class CSVWriter
{
    // Class attributes
    public final String pathName;
    public BufferedWriter writer;

    // Constructor for the writer
    public CSVWriter(String pathName) throws IOException {
        this.pathName = pathName; // Saves the file path to be constructed later
        this.writer = null; // Sets the writer to null currently

    }
    // Second constructor for the writer that deals with when you need to construct a writer from a specific csv file dynamically
    public CSVWriter(String pathToDirectory, String fileName) throws IOException
    {
        this.pathName = pathToDirectory + fileName; // Sets the path name
        try
        {
            writer = new BufferedWriter(new FileWriter(this.pathName)); // Create a buffered writer to the specified file path
        }
        catch(FileNotFoundException e) // Creates a file if one doesn't exist
        {
            System.out.println("File not found! Generating the file"); // Generates the file
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(pathToDirectory + file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to write to a specified csv file without overwriting the pre-existing data
     * @param toWrite
     * @throws IOException
     */
    public void writeToCSV( ArrayList<String> toWrite) throws IOException
    {
        writer = new BufferedWriter(new FileWriter(pathName));

        for (String s : toWrite) {
            System.out.println("The line to write :" + s);
            writer.write(s);
            writer.newLine();
        }
        writer.flush();

    }

    /**
     * Method to overwrite the csv data currently contained in a file if somones data is changed.
     * @param toOverWrite
     * @throws IOException
     */
    public void OverWriteData(ArrayList<String> toOverWrite) throws IOException
    {
        // Create a new writer with the append Flag set to false
        try {
            writer = new BufferedWriter(new FileWriter(pathName,false));
            writeToCSV(toOverWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.flush();
    }

}
