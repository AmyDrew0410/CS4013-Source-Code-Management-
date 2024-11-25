package PaymentProcess;

import UserType.UserTypes;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentCycleManager {
    private ArrayList<String> employees; //List of all employees
    private PayslipHistory payslipHistory; //Central history of payslips

    public PaymentCycleManager(ArrayList<String> employees, PayslipHistory payslipHistory) {
        this.employees = employees;
        this.payslipHistory = payslipHistory;
    }

    /**
     * Method to check if the date is the 25th and if it is, generate payslips for all employees
     * in the list of employees of type userType.
     */
    public void processCycle(){
        LocalDate today = LocalDate.now();

        //Check if today is the 25th
        if(today.getDayOfMonth() == 25){
            System.out.println("Today is the 25th. Generating payslips...");

            //Loop through all employees and generate payslips
            for(String employee : employees){
                String[] employeeData = employee.split(",");
                //Create PaymentProcess object for the employee
                double salary = Double.parseDouble(employeeData[10]);
                PaymentProcess paymentProcess = new PaymentProcess(salary, today, employeeData[5], employeeData[13]);

                //Create Payslip object and add it to the payslip history
                Payslip payslip = new Payslip(employee, paymentProcess, payslipHistory);

                //Save payslip to CSV
                try{
                    String fileName = employee.getFirstName() + "_" + employee.getLast_Name() + "_Payslip_" + today.toString();
                    payslip.payslipToCSV(paymentProcess, fileName);
                    System.out.println("Payslip generated for: " + employee.getFirstName() + " " + employee.getLast_Name());
                }catch (Exception e){
                    System.out.println("Error generating payslip for " + employee.getFirstName() + ": " + e.getMessage());
                }
            }
        }else{
            System.out.println("Today is not the 25th. No payroll processing required.");
        }
    }
}
