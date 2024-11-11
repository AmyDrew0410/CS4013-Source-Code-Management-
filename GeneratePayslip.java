public class GeneratePayslip {
    private PaymentProcess payslip;
    private Employee employee;

    /**
     * this class is to generate a payslip using the employee and payment process classes
     * as object types.
     * author : Amy Drew
     * student ID : 23370076
     * @param employee
     * @param payslip
     */

    //constructor to build payslip object from the payment process class and type and employee class and type
    public GeneratePayslip(Employee employee, PaymentProcess payslip){
        this.employee = employee;
        this.payslip = payslip;
        PayslipHistory.addPayslip(this);
    }

    /**
     * getter method to return the payslip object in the payment process type
     * @return
     */
    public PaymentProcess getPayslip(){
        return payslip;
    }

    /**
     * getter method to return the employee object in the employee ty[e
     */
    public Employee getEmployee(){
        return employee;
    }
}
