import java.time.LocalDate;

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
     * getter methods to get different parts of the UserTypes class to then utilise in the structure of the payslip
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
        payslipString.append("PAYSLIP OWNER FIRST NAME: ").append(getFirstName()).append("\n");
        payslipString.append("PAYSLIP OWNER LAST NAME: ").append(getLastName()).append("\n");
        payslipString.append("PPS NUMBER: ").append(getPPSNumber()).append("\n");
        payslipString.append("EMPLOYEE ID: ").append(getEmployeeID()).append("\n");
        payslipString.append("DATE OF PAYMENT: ").append(getDateOfPayslip().toString()).append("\n");
        payslipString.append("GROSS PAY: ").append(String.format("%.2f", getPaymentProcess().getGrossPay())).append("\n");
        payslipString.append("PRSI: ").append(String.format("%.2f", getPaymentProcess().getPRSI())).append("\n");
        payslipString.append("USC: ").append(String.format("%.2f", getPaymentProcess().getUSC())).append("\n");
        payslipString.append("INCOME TAX: ").append(String.format("%.2f", getPaymentProcess().getIncomeTax())).append("\n");
        payslipString.append("NET PAY: ").append(String.format("%.2f", getPaymentProcess().getNetPay())).append("\n");

        return payslipString.toString();
    }
}
