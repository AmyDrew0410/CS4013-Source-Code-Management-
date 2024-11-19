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
    public void writeToCSV( ArrayList<String> toWrite) throws IOException {
        for(int i = 0; i<toWrite.size(); i++){
            String line = (String) toWrite.get(i);
            writer.write(line);
            writer.newLine();
        }
        writer.close();


    }

}
