package PaymentProcess;

import UserType.UserTypes;
import FileHandler.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class Payslip {
    private PaymentProcess paymentProcess;
    private UserTypes employee;

    /**
     * this class is to generate a payslip using the usertypes and payment process classes
     * as object types.
     * author : Amy Drew
     * student ID : 23370076
     * @param employee
     * @param paymentProcess
     */

    //Constructor to build payslip object from the payment process class and type and employee class and type
    public Payslip(UserTypes employee, PaymentProcess paymentProcess, PayslipHistory historyOfThisCycle){
        this.employee = employee;
        this.paymentProcess = paymentProcess;
        historyOfThisCycle.addPayslip(this);
    }

    /**
     * Getter method to return the payslip object in the payment process type
     * @return
     */
    public PaymentProcess getPaymentProcess(){
        return paymentProcess;
    }

    /**
     * getter methods to get different parts of the UserType.UserTypes class to then utilise in the structure of the payslip
     */
    public UserTypes getEmployee(){
        return employee;
    }

    public int getEmployeeID(){
        return getEmployee().getEmployee_ID();
    }

    public String getFirstName(){
        return getEmployee().getFirstName();
    }

    public String getLastName(){
        return getEmployee().getLast_Name();
    }

    public String getPPSNumber(){
        return getEmployee().getPPSN();
    }

    public LocalDate getDateOfPayslip(){
        return getPaymentProcess().getDateOfProcess();
    }

    /**
     * toString() method to use string builder to construct the payslip and
     * print it as a string.
     * @return
     */

    public String toString() {

        String payslipLine = String.join(", ", String.valueOf(getEmployeeID()), getFirstName(), getLastName(), getPPSNumber(), getDateOfPayslip().toString(),
                String.format("%.2f", getPaymentProcess().getGrossPay()),
                String.format("%.2f", getPaymentProcess().getPRSI()),
                String.format("%.2f", getPaymentProcess().getUSC()),
                String.format("%.2f", getPaymentProcess().getIncomeTax()),
                String.format("%.2f", getPaymentProcess().getHealthInsurance()),
                String.format("%.2f", getPaymentProcess().getUnionFees()),
                String.format("%.2f", getPaymentProcess().getNetPay()));

        return payslipLine;
    }


    public static void getPayslipData(String userName) throws IOException {
        //folder to hold the directory to the folder holding csv files
        String folder = "src\\PaymentProcess\\PayslipHistory";
        FolderReader reader = new FolderReader(folder);
        //array list to hold the csv file names as strings
        ArrayList<String> payslipHistoryNames = reader.getFileNames();
        String userChoice = retrieveUserChoice(payslipHistoryNames);

        //instantiate a csv reader object to read the directory and the choice the user makes
        CSVReader csvReader = new CSVReader(folder + userChoice);
        //reads the data in the csv file the user chooses
        String payslipData = csvReader.findLine(userName,2);

        //conditional statements for if user is or is not found.
        if(payslipData != null){
            String[] data = payslipData.split(",");

            //string builder to print the payslip from the csv file as a structured string
            StringBuilder payslipString = new StringBuilder();
            payslipString.append("======== PAYSLIP ======== \n\n");
            payslipString.append("|RECEIVER INFORMATION| \n\n");
            payslipString.append("FIRST NAME: ").append(data[0]).append("\n");
            payslipString.append("LAST NAME: ").append(data[1]).append("\n");
            payslipString.append("PPS NUMBER: ").append(data[2]).append("\n");
            payslipString.append("EMPLOYEE ID: ").append(data[3]).append("\n\n");

            payslipString.append("DATE OF PAYMENT: ").append(data[4]).append("\n\n");

            payslipString.append("======= PAYMENT AND DEDUCTIONS ======== \n\n");

            payslipString.append("GROSS PAY: ").append(format("%.2f", Double.parseDouble(data[5]))).append("\n");
            payslipString.append("PRSI: ").append(format("%.2f", Double.parseDouble(data[6]))).append("\n");
            payslipString.append("USC: ").append(format("%.2f", Double.parseDouble(data[7]))).append("\n");
            payslipString.append("INCOME TAX: ").append(format("%.2f", Double.parseDouble(data[8]))).append("\n");
            payslipString.append("HEALTH INSURANCE: ").append(format("%.2f", Double.parseDouble(data[9]))).append("\n");
            payslipString.append("UNION FEES: ").append(format("%.2f", Double.parseDouble(data[10]))).append(("\n"));
            payslipString.append("NET PAY: ").append(format("%.2f", Double.parseDouble(data[11]))).append("\n");

            System.out.println(payslipString);
        }else{
            System.out.printf("Employee data does not exist for this date in the payslip history. Please contact HR for further assistance");
        }
    }

    public static String retrieveUserChoice(ArrayList<String> payslipHistoryNames){
        Scanner in = new Scanner(System.in);

        //using aski values to use letters as the choice values in hte command line for users
        char index = 'A';

        //for each string payslip csv file in payslipHistoryNames, print the index for
        //that payslip and allow the user to choose one of the options
        for(String payslip : payslipHistoryNames){
            System.out.println(index + ")" + payslip);
            index++;
        }
        System.out.println("Your choice: ");
        String userChoice = in.nextLine().trim().toUpperCase();

        if (userChoice.length() == 1) {
            int n = userChoice.charAt(0) - 'A';
            if (n >= 0 && n < payslipHistoryNames.size()) {
                return payslipHistoryNames.get(n);
            }
        }
        System.out.println("Invalid choice, please select a valid option");
        return null;
   }
}
