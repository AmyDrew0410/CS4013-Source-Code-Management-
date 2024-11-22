package CSVHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class handles all CSV reading and writing methods to help de-compartmentalise the code in all classes
 */
public class CSVReader {
    //Class attributes
    private BufferedReader CSVfile;
    private final String pathName;
    //Default constructor for the OccupationCSV class that sets up a file reader for Occupations.csv
    //Has exception handling for if a file isn't found
    public CSVReader(String pathName) throws FileNotFoundException {
        this.pathName = pathName;
        // Load file from Occupations.resources
        var resource = getClass().getClassLoader().getResourceAsStream(this.pathName);
        if (resource == null) {
            throw new FileNotFoundException("File not found in Occupations.resources: " + this.pathName);
        }
        CSVfile = new BufferedReader(new InputStreamReader(resource));
    }

    /**
     * Method used to read our CSV file and return data based on the required column to
     * an array List
     * @params colIdentifier
     * @Return toReturn
     */
    public ArrayList<Object> readFromCSV(int colIdentifier) {
        //Our return Variable
        ArrayList<Object> toReturn = new ArrayList<>();
        //Try catch for Input Output exception handling
        try {
            //Holds our next tuple in the csv File
            String nextTuple;
            while ((nextTuple = CSVfile.readLine()) != null) { //While the next read line isn't null
                String[] splitTuple = nextTuple.split(","); //Split the string returned from readLine by its commas

                //If our return variable is empty or if it doesn't contain the value found, add it
                if (toReturn.isEmpty() || !toReturn.contains(splitTuple[colIdentifier])) {
                    toReturn.add(splitTuple[colIdentifier]);
                }

            }
            //Used to reset the buffered reader after each method invocation
            resetCSV();
            //Return statement
            return toReturn;
        } catch (IOException e) { //Catches any IO exceptions when reading through the file.
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
    public ArrayList<Object> sortByKey(String key, int colIdentifier){

        //ArrayList that will store the tuples from the csv file that contain our key
        ArrayList<Object> keyTuples = new ArrayList<>();

        //Try catch for input output Exception handling
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
        //Initialises the array we will compare csv values to find the desired tuple value
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
     * Method used to handle reseting the CSV reader after each method call so that it can read through the csv
     * file again allowing for multiple search queries
     */
    private void resetCSV(){
        try {
            CSVfile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVfile = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(pathName))));
    }

}
