package FileHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * Method for reading a folder of data and returning information such as specific file names.
 * @author Ben Bastianelli 23368071
 * @version 1.0
 */
public class FolderReader
{
    // Class Attributes
    String folderPath;
    File folder;

    public FolderReader(String folderPath){
        this.folderPath = folderPath;
        this.folder = new File(folderPath);
    }

    /**
     * Gets the file names of every file in a folder
     * @return fileNames
     */
    public ArrayList<String> getFileNames()
    {
        ArrayList<String> fileNames = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            // Get all files in the directory
            File[] files = folder.listFiles();

            // Check if the directory is not empty
            if (files != null) {
                for (File file : files) {
                    // Print only file names, not directories
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Invalid folder path.");
        }
        return fileNames;

    }
}
