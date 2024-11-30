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
     * @param toWrite An array list of type String that contains the data we wish to write to the specified file
     * @throws IOException throws an IO exception if the bufferedWriters file path doesn't exist
     */
    public void writeToCSV( ArrayList<String> toWrite) throws IOException
    {
        if(writer == null) // Creates a new bufferedWriter location if the writer is null using the file path
        {
            writer = new BufferedWriter(new FileWriter(pathName)); // Create a bufferedWriter to the file path
        }

        for (String s : toWrite) // For each string S in to Write
        {
            writer.write(s); // Write the data
            writer.newLine(); // Create a new line for the next piece of data
        }
        writer.flush(); // Flush the writer at the end to ensure all data is written

    }

    /**
     * Method to overwrite the csv data currently contained in a file if some-ones data is changed.
     * @param toOverWrite An array list of type string that contains all the data we wish to overwrite to the CSVFile
     * @throws IOException Throws an input output exception if the bufferedWriter isn't instantiated properly
     * Due to incorrect file pathing
     */
    public void OverWriteData(ArrayList<String> toOverWrite) throws IOException
    {
        // Create a new writer with the append Flag set to false
        try
        {
            writer = new BufferedWriter(new FileWriter(pathName,false)); // Append flag set to false allows us to truncate data
            writeToCSV(toOverWrite); // invoke the 'write to CSV' method with this new writer
        }
        catch (IOException e) // Exception Handling
        {
            System.out.println("An error occured when trying to write to the file! This file doesn't exist");
        }
        writer.flush(); // Flush the writer to ensure the data is written
    }

}
