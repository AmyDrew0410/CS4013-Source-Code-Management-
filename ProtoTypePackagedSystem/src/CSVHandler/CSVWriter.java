package CSVHandler;

import java.io.*;
import java.util.ArrayList;

public class CSVWriter
{
    public final String pathName;
    public BufferedWriter writer;

    public CSVWriter(String pathName) throws IOException {
        this.pathName = pathName;
        try{
            writer = new BufferedWriter(new FileWriter(this.pathName));
        }catch(FileNotFoundException e){
            System.out.println("File not found! Generating the file");
            File file = new File(this.pathName);
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public CSVWriter(String pathToDirectory, String fileName) throws IOException {
        this.pathName = pathToDirectory + fileName;
        try{
            writer = new BufferedWriter(new FileWriter(this.pathName));
        }catch(FileNotFoundException e){
            System.out.println("File not found! Generating the file");
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(pathToDirectory + file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void writeToCSV( ArrayList<String> toWrite) throws IOException {
        for (String s : toWrite) {
            writer.write(s);
            writer.newLine();
        }
        writer.close();
    }

}
