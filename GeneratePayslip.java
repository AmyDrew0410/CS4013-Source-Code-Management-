public class GeneratePayslip {
    private PaymentProcess payslip;

    //constructor to build payslip object from the payment process class and type
    public GeneratePayslip(double grossPay, double salary, double netPay, double PRSI, double PAYE, double USC){
        payslip = new PaymentProcess(grossPay, salary, netPay, 0.041, USC, PAYE);
    }

    /**
     * getter method to return the payslip object in the payment proces type
     * @return
     */
    public PaymentProcess getPayslip(){
        return payslip;
    }
}
