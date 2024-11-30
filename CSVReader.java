package FileHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handles all CSV reading and writing methods to help de-compartmentalise the code in all classes
 */
public class CSVReader {
    // Class attributes
    BufferedReader CSVfile;
    private final String pathName;
    // Default constructor for the OccupationCSV class that sets up a file reader for Occupations.csv

    // Has exception handling for if a file isn't found
    public CSVReader(String pathName) {
        this.pathName = pathName; // Store the file path as a class attribute for resetting the object later
        try
        {
            this.CSVfile = new BufferedReader(new FileReader(pathName)); // Create a new buffered reader
        }
        catch (FileNotFoundException e) // Catches a file not found exception
        {
            System.err.println("File not found: " + pathName); // Handle the error

        }
    }

    /**
     * Method used to read our CSV file and return data based on the required column to
     * an array List
     *
     * @params colIdentifier
     * @Return toReturn
     */
    public ArrayList<String> readColFromCSV(int colIdentifier) {
        // Our return Variable
        ArrayList<String> toReturn = new ArrayList<>();
        // Try catch for Input Output exception handling
        try
        {
            // Holds our next tuple in the csv File
            String nextTuple;
            while ((nextTuple = CSVfile.readLine()) != null) // While the next read line isn't null
            {
                String[] splitTuple = nextTuple.split(","); //Split the string returned from readLine by its commas

                // If our return variable is empty or if it doesn't contain the value found, add it
                if (toReturn.isEmpty() || !toReturn.contains(splitTuple[colIdentifier]))
                {
                    toReturn.add(splitTuple[colIdentifier]);
                }

            }
            // Used to reset the buffered reader after each method invocation
            resetCSV();
            // Return statement
            return toReturn;
        } catch (IOException e)// Catches any IO exceptions when reading through the file.
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to read only data associated with a certain column in a csv file
     * and that is specified by a key of a column.
     * It only returns all unique types in the column and stores them in an array list
     *
     * @return an array list 'toReturn'
     * @params previousKey, colIdentifier
     */
    public ArrayList<String> sortByKey(String key, int colIdentifier){

        // ArrayList that will store the tuples from the csv file that contain our key
        ArrayList<String> keyTuples = new ArrayList<>();

        // Try catch for input output Exception handling
        try
        {
            String nextTuple; // Stores our next line
            while((nextTuple = CSVfile.readLine()) != null) // While the next line isn't null
            {
                String[] splitTuple = nextTuple.split(","); // Split into an array based off commas as a regex
                if(splitTuple[colIdentifier-1].contains(key) && !keyTuples.contains(splitTuple[colIdentifier])) // If the split tuple at column Identifier contains the key -1 and the key tuples variable doesn't
                {
                    keyTuples.add(splitTuple[colIdentifier]); // Add it to the list

                }
            }
            resetCSV(); // reset the CSV
            return keyTuples; // Return the list

        }catch (IOException e){
            System.out.println("An error occured when trying to read the file!");
        }
        return null;
    }

    /**
     * This method takes a string tuple as a parameter written in the form
     * 'Value1,Value2,Value...' splits the string values by their commas into a string array
     * Compares the Values to find the final value in the csv file represented by colIdentifier
     * @param tuplePattern A string that contains the specified tuple
     * @param colIdentifier The identifier of the desired column
     * @return Desired data as a string
     */
    public String tupleFind(String tuplePattern,int colIdentifier) throws IOException {
        // Initialises the array we will compare csv values to find the desired tuple value
        String[] ourQuery = tuplePattern.split(",");

        String nextTuple;
        while((nextTuple = CSVfile.readLine()) != null){
            String[] splitTuple = nextTuple.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            int correctAttributes = 0;

            for(int i = 0; i< ourQuery.length; i++){
                if(splitTuple[i].equals(ourQuery[i])){
                    correctAttributes++;
                }else{
                    correctAttributes = 0;
                    break;
                }
            }

            if(correctAttributes == ourQuery.length)
            {
                return splitTuple[colIdentifier];
            }
        }
        resetCSV();
        return null;
    }

    /**
     * This Method reads through the specified CSV file
     * and returns every line of it in an arrayList of type String
     * @return toReturn
     */
    public ArrayList<String> readAllFromCSV()
    {
        // Our arrayList to return
        ArrayList<String> toReturn = new ArrayList<>();

        // Iterate through the desired csv and return every Line
        String nextLine;
        try {
            while ((nextLine = CSVfile.readLine()) != null)
            {
                toReturn.add(nextLine);
            }
        }catch(IOException e){
            System.out.println("An error occured while reading the file!");
        }
        resetCSV();
        return toReturn;
    }

    /**
     * Searches the desired CSV file for your specified primary key at a specified index of the line
     * If the line contains the primary key at the specified index it returns the nextLine
     * @param primaryKey The primary key of the data you're searching for
     * @param specIndex The location of that data in each line of the CSV file
     * @return The desired string based off the primaryKey
     */

    public String findLine(String primaryKey,int specIndex) throws IOException {
        String nextLine;
        while((nextLine = CSVfile.readLine()) != null)
        {
            String[] tuple = nextLine.split(",");
            if(tuple[specIndex].contains(primaryKey)){
                return nextLine;
            }

        }
        return null; // Returns null if no matching line is found

    }

    /**
     * Method used to handle resetting the CSV reader after each method call so that it can read through the csv
     * file again allowing for multiple search queries
     */
    public void resetCSV() {
        try {
            if (CSVfile != null) {
                CSVfile.close();
            }
            CSVfile = new BufferedReader(new FileReader(pathName));
        } catch (IOException e) {
            System.err.println("Failed to reset CSV file: " + e.getMessage());
        }
    }



}


