import java.util.List;
import java.util.ArrayList;

public class PayslipHistory {
    private List<GeneratePayslip> payslipHistory;

    public PayslipHistory(){
        payslipHistory = new ArrayList<>();
    }

    /**
     * method to add an entry of generate payslip type into array list
     */
    public void addPayslip(GeneratePayslip payslip){
        payslipHistory.add(payslip);
    }
}
