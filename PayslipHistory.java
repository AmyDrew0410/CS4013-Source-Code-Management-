import java.util.List;
import java.util.ArrayList;

public class PayslipHistory {
    //list to hold payslip history
    private static List<GeneratePayslip> payslipHistory = new ArrayList<>();

    public static void addPayslip(GeneratePayslip payslip){
        payslipHistory.add(payslip);
    }

    /**
     * get the employee id
     * get currentDate
     * preset string to 25th of a year, look for highest month in that schema and put data in string
     * array and return that
     */

}
