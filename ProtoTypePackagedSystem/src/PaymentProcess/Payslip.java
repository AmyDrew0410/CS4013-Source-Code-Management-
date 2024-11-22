package PaymentProcess;

import UserType.UserTypes;
import CSVHandler.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
        return getEmployee().getFirst_Name();
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

    public String toString(){
        StringBuilder payslipString = new StringBuilder();
        payslipString.append("======== PAYSLIP ======== \n\n");
        payslipString.append("|RECEIVER INFORMATION| \n\n");
        payslipString.append("FIRST NAME: ").append(getFirstName()).append("\n");
        payslipString.append("LAST NAME: ").append(getLastName()).append("\n");
        payslipString.append("PPS NUMBER: ").append(getPPSNumber()).append("\n");
        payslipString.append("EMPLOYEE ID: ").append(getEmployeeID()).append("\n\n");

        payslipString.append("DATE OF PAYMENT: ").append(getDateOfPayslip().toString()).append("\n\n");

        payslipString.append("======= PAYMENT AND DEDUCTIONS ======== \n\n");

        payslipString.append("GROSS PAY: ").append(format("%.2f", getPaymentProcess().getGrossPay())).append("\n");
        payslipString.append("PRSI: ").append(format("%.2f", getPaymentProcess().getPRSI())).append("\n");
        payslipString.append("USC: ").append(format("%.2f", getPaymentProcess().getUSC())).append("\n");
        payslipString.append("INCOME TAX: ").append(format("%.2f", getPaymentProcess().getIncomeTax())).append("\n");
        payslipString.append("HEALTH INSURANCE: ").append(format("%.2f", getPaymentProcess().getHealthInsurance())).append("\n");
        payslipString.append("UNION FEES: ").append(format("%.2f", getPaymentProcess().getUnionFees())).append(("\n"));
        payslipString.append("NET PAY: ").append(format("%.2f", getPaymentProcess().getNetPay())).append("\n");

        return payslipString.toString();
    }

    public String toString2(PaymentProcess payslip) throws IOException{

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

    /**
     * Method to write a payslip to a CSV file and use a file not found exception in the method
     * signaature to throw the error if the file is not found at execution.
     * @param payslip
     * @throws FileNotFoundException
     */

    public void payslipToCSV(PaymentProcess payslip,String fileName) throws IOException {
        //Make a CSVHandler object for handling writing the payslip to CSV.
        CSVWriter csvWriter = new CSVWriter("src\\PaymentProcessing\\PayslipHistory\\" ,fileName);

        //Make an array list of type object to then fill with the second toString method that separates every
        //piece of information for the payslip by commas to then write it to my CSV file.
        ArrayList<String> payslipHistory = new ArrayList<>();
        payslipHistory.add(toString2(payslip));

        //Call the CSVHandler.CSVWriter classes writeToCSV method to write my second toString() method into the csv file.
        csvWriter.writeToCSV(payslipHistory);
    }
}
