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
    public CSVReader(String pathName) throws FileNotFoundException {
        this.pathName = pathName;
        File file = new File(pathName);
        if (!file.exists()) {
            System.err.println("File not found: " + pathName);
        } else {
            System.out.println("File found and readable: " + pathName);
        }
        this.CSVfile = new BufferedReader(new FileReader(pathName));
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
        try {
            // Holds our next tuple in the csv File
            String nextTuple;
            while ((nextTuple = CSVfile.readLine()) != null) { //While the next read line isn't null
                String[] splitTuple = nextTuple.split(","); //Split the string returned from readLine by its commas

                // If our return variable is empty or if it doesn't contain the value found, add it
                if (toReturn.isEmpty() || !toReturn.contains(splitTuple[colIdentifier])) {
                    toReturn.add(splitTuple[colIdentifier]);
                }

            }
            // Used to reset the buffered reader after each method invocation
            resetCSV();
            // Return statement
            return toReturn;
        } catch (IOException e)
        { // Catches any IO exceptions when reading through the file.
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
            String nextTuple;
            while((nextTuple = CSVfile.readLine()) != null)
            {
                String[] splitTuple = nextTuple.split(",");
                if(splitTuple[colIdentifier-1].contains(key) && !keyTuples.contains(splitTuple[colIdentifier]))
                {
                    keyTuples.add(splitTuple[colIdentifier]);

                }
            }
            resetCSV();
            return keyTuples;

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * This method takes a string tuple as a parameter written in the form
     * 'Value1,Value2,Value...' splits the string values by their commas into a string array
     * Compares the Values to find the final value in the csv file represented by colIdentifier
     * @param tuplePattern
     * @param colIdentifier
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

    public String findLine(String primaryKey,int specIndex) throws IOException {
        String nextLine;
        while((nextLine = CSVfile.readLine()) != null)
        {
            String[] tuple = nextLine.split(",");
            if(tuple[specIndex].contains(primaryKey)){
                return nextLine;
            }

        }
        return nextLine;

    }

    /**
     * Method used to handle reseting the CSV reader after each method call so that it can read through the csv
     * file again allowing for multiple search queries
     */
    public void resetCSV() {
        try {
            if (CSVfile != null) {
                CSVfile.close();
            }
            CSVfile = new BufferedReader(new FileReader(pathName));
            System.out.println("BufferedReader reset for: " + pathName);
        } catch (IOException e) {
            System.err.println("Failed to reset CSV file: " + e.getMessage());
        }
    }



}


