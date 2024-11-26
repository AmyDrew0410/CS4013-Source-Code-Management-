package PaymentProcess;

import FileHandler.CSVWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PayslipHistory {
    //list to hold payslip history
    private ArrayList<Payslip> payslipHistory = new ArrayList<>();

    public PayslipHistory(){
    }

    /**
     * Void method to add a payslip to the payslip history array list.
     * @param payslip
     */
    public void addPayslip(Payslip payslip){
        payslipHistory.add(payslip);
    }

    /**
     * A printPayslipHistory method that prints the payslips within the payslip history arraylist as a string
     * using the toString() method in the payslips class.
     * @return
     */
    public String printPayslipHistory() throws IOException {
        StringBuilder payslipHistoryString = new StringBuilder();

        for(Payslip eachPayslip : this.payslipHistory){
            payslipHistoryString.append(eachPayslip.toString());
            payslipHistoryString.append("\n\n");
        }
        return payslipHistoryString.toString();
    }

    public ArrayList<String> historyToCSVFormat() throws IOException {
        ArrayList<String> toWrite = new ArrayList<>();
        for(Payslip payslip : payslipHistory ) {
            toWrite.add(payslip.toString());
        }
        return toWrite;
    }

    /**
     * Method to write a payslip to a CSV file and use a file not found exception in the method
     * signaature to throw the error if the file is not found at execution.
     *
     * @throws FileNotFoundException
     */
    public void payslipToCSV(String fileName) throws IOException {
        //Make a CSVHandler object for handling writing the payslip to CSV.
        CSVWriter csvWriter = new CSVWriter("src\\PaymentProcess\\PayslipHistory\\" ,fileName + ".csv");

        ArrayList<String> toWrite = historyToCSVFormat();

        //Call the CSVHandler.CSVWriter classes writeToCSV method to write my second toString() method into the csv file.
        csvWriter.writeToCSV(toWrite);
    }
    /**
     *  A getter to return the payslip history after all payslips have been added to the array list
     * @return
     */

    public ArrayList<Payslip> getPayslipHistory(){
        return payslipHistory;
    }
}
