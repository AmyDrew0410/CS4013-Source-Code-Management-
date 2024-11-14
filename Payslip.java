import java.time.LocalDate;

public class Payslip {
    private PaymentProcess paymentProcess;
    private Employee employee;

    /**
     * this class is to generate a payslip using the employee and payment process classes
     * as object types.
     * author : Amy Drew
     * student ID : 23370076
     * @param employee
     * @param paymentProcess
     */

    //constructor to build payslip object from the payment process class and type and employee class and type
    public Payslip(Employee employee, PaymentProcess paymentProcess){
        this.employee = employee;
        this.paymentProcess = paymentProcess;
        PayslipHistory.addPayslip(this);
    }

    /**
     * getter method to return the payslip object in the payment process type
     * @return
     */
    public PaymentProcess getPaymentProcess(){
        return paymentProcess;
    }

    /**
     * getter method to return the employee object in the employee ty[e
     */
    public Employee getEmployee(){
        return employee;
    }

    public int getEmployeeID(){
        return getEmployee().getEmployeeID();
    }

    public LocalDate getDateOfPayslip(){
        return getPaymentProcess().getDateOfProcess();
    }

    public String toString(){
        StringBuilder payslipString = new StringBuilder();
        payslipString.append("PAYSLIP FOR ").append(getDateOfPayslip().toString()).append("\n");
        payslipString.append("GROSS PAY: ").append(getPaymentProcess().getGrossPay()).append("\n");
        payslipString.append("PRSI: ").append(getPaymentProcess().getPRSI()).append("\n");
        payslipString.append("USC: ").append(getPaymentProcess().getUSC()).append("\n");
        payslipString.append("INCOME TAX: ").append(getPaymentProcess().getIncomeTax()).append("\n");
        payslipString.append("NET PAY: ").append(getPaymentProcess().getNetPay()).append("\n");

        return payslipString.toString();
    }
}
