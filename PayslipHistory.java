import java.util.ArrayList;

public class PayslipHistory {
    //list to hold payslip history
    private ArrayList<Payslip> payslipHistory = new ArrayList<>();

    public PayslipHistory(ArrayList<Payslip> payslipHistory){
        this.payslipHistory = payslipHistory;

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
    public String printPayslipHistory(){
        StringBuilder payslipHistoryString = new StringBuilder();

        for(Payslip eachPayslip : this.payslipHistory){
            payslipHistoryString.append(eachPayslip.toString());
            payslipHistoryString.append("\n\n");
        }
        return payslipHistoryString.toString();
    }

    /**
     *  A getter to return the payslip history after all payslips have been added to the array list
     * @return
     */

    public ArrayList<Payslip> getPayslipHistory(){
        return payslipHistory;
    }
}
