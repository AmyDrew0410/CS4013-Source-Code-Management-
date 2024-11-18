import java.time.LocalDate;
import java.util.ArrayList;

public class PayslipTester {
    public static void main(String[] args) {
        //testing each class

        Employees listOfEmployees = new Employees();
        Employee employee = new Employee("Jonathan", "Bailey", 123456,
                "0853040555", "jonathanBailey123@hotmail.com", 4, Boolean.TRUE,
                listOfEmployees, "26507W", "Aviva");
        PaymentProcess paymentProcess = new PaymentProcess(50000.00, LocalDate.now(), employee);

        ArrayList<Payslip> payslipHistoryArrayList = new ArrayList<>();

        PayslipHistory payslipHistory = new PayslipHistory(payslipHistoryArrayList);
        Payslip payslip = new Payslip(employee, paymentProcess, payslipHistory);

        System.out.println(employee);
        System.out.println(payslipHistory.printPayslipHistory());

        Employees listOfEmployees2 = new Employees();
        Employee employee2 = new Employee("Fionn", "O'Sullivan", 696969,
                "0838316189", "fosullivan.asr@gmail.com", 6, Boolean.FALSE,
                listOfEmployees2, "265072W", "VHI");
        PaymentProcess paymentProcess2 = new PaymentProcess(23000, LocalDate.now(), employee2);

        ArrayList<Payslip> payslipHistoryArrayList2 = new ArrayList<>();

        PayslipHistory payslipHistory2 = new PayslipHistory(payslipHistoryArrayList2);
        Payslip payslip2 = new Payslip(employee2, paymentProcess2, payslipHistory2);

        System.out.println(employee2);
        System.out.println(payslipHistory2.printPayslipHistory());
    }
}
