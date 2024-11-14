import java.util.ArrayList;

public class PayslipHistory {
    //list to hold payslip history
    private Payslip payslip;
    private ArrayList<Payslip> payslipHistory = new ArrayList<>();

    public PayslipHistory(Payslip payslip){
        this.payslip = payslip;
    }

    public void addPayslip(Payslip payslip){
        payslipHistory.add(payslip);
    }

    /**
     * get the employee id
     * get currentDate
     * preset string to 25th of a year, look for highest month in that schema and put data in string
     * array and return that
     */

    public String printPayslipHistory(){
        StringBuilder payslipHistoryString = new StringBuilder();

        for(Payslip eachPayslip : this.payslipHistory){
            payslipHistoryString.append(eachPayslip.toString());
            payslipHistoryString.append("\n\n");
        }
        return payslipHistoryString.toString();
    }
}
