import UserType.*;
import PaymentProcess.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args) throws IOException {
        UserTypes employee1 = new Employee("John","Doe",23368071,"0870982108","JohnDoe@gmail.com",false,"4927606Q","Union A");
        PaymentProcess process = new PaymentProcess((Double) employee1.getSalary(), LocalDate.now(),employee1);
        ArrayList<Payslip> paySlipHistory = new ArrayList<>();
        PayslipHistory thisCycle = new PayslipHistory(paySlipHistory);
        Payslip payslip = new Payslip(employee1,process,thisCycle);

        payslip.toString2(process);
        payslip.payslipToCSV(process,"May2024");
    }
}
