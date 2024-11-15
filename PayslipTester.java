import java.time.LocalDate;
import java.util.ArrayList;

public class PayslipTester {
    public static void main(String[] args) {
        //testing each class

        Employees listOfEmployees = new Employees();
        Employee employee = new Employee("Jonathan", "Bailey", 12345678, "0853040555", "jonathanBailey123@hotmail.com", 4, Boolean.TRUE, listOfEmployees);
        PaymentProcess paymentProcess = new PaymentProcess(123489, LocalDate.now(), employee);

        ArrayList<Payslip> payslipHistoryArrayList = new ArrayList<>();

        PayslipHistory payslipHistory = new PayslipHistory(payslipHistoryArrayList);
        Payslip payslip = new Payslip(employee, paymentProcess, payslipHistory);

        System.out.println(employee);
        System.out.println(paymentProcess);
        System.out.println(payslip.toString());
        System.out.println(payslipHistory.printPayslipHistory());
    }
}
