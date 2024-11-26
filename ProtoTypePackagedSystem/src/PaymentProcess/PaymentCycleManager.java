package PaymentProcess;

import FileHandler.CSVReader;
import UserType.Employee;
import UserType.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentCycleManager {
    private ArrayList<String> employees; //List of all employees
    private PayslipHistory payslipHistory; //Central history of payslips

    public PaymentCycleManager() throws FileNotFoundException {
        CSVReader reader = new CSVReader("src/UserType/resources/employees.csv");
        this.employees = reader.readAllFromCSV();
        this.payslipHistory = new PayslipHistory();
    }

    /**
     * Method to check if the date is the 25th and if it is, generate payslips for all employees
     * in the list of employees of type userType.
     */
    public void processCycle() throws IOException {
        LocalDate today = LocalDate.now();

        //Check if today is the 25th
        if(today.getDayOfMonth() == 25){
            System.out.println("Today is the 25th. Generating payslips...");

            PayslipHistory history = new PayslipHistory();
            //Loop through all employees and generate payslips
            for(String employee : employees){
                UserTypes employeeData = new Employee(employee);
                PaymentProcess paymentProcess = new PaymentProcess(employeeData.getSalary(), today, employeeData.getMarital_Status(), employeeData.getUnionFees());

                //Create Payslip object and add it to the payslip history
                Payslip payslip = new Payslip(employeeData, paymentProcess, payslipHistory);

                history.addPayslip(payslip);
                //Save payslip to CSV

            }
        }else{
            System.out.println("Today is not the 25th. No payroll processing required.");
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the month and year For this cycle: ");
        payslipHistory.payslipToCSV(in.next());

    }
}
