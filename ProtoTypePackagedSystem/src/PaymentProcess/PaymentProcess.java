package PaymentProcess;

import UserType.UserTypes;

import java.time.LocalDate;

public class PaymentProcess {
    private double grossPay;
    private double netPay;
    private double salary;
    private double PRSI;
    private double USC;
    private double incomeTax;
    private double healthInsurance;
    private double unionFees;
    private double maxTaxCredit;
    private LocalDate dateOfProcess;

    UserTypes employee;

    /**
     * this class is to run through multiple methods to calculate the net pay once
     * all tax deductions have been made to the employees salary and monthly gross pay
     * author : Amy Drew
     * student ID : 23370076
     */

    //payment process constructor to create obj in generate payslips class
    public PaymentProcess(double salary, LocalDate dateOfProcess, UserTypes employee){
        this.salary = salary;
        this.employee = employee;
        this.grossPay = calcGrossPay();
        this.PRSI = calcPRSI();
        this.USC = calcUSC();
        this.incomeTax = calcIncomeTax();
        this.healthInsurance = calcHealthInsurance();
        this.unionFees = calcUnionFees();
        this.dateOfProcess = dateOfProcess;
        this.netPay = calcNetPay(PRSI, USC, incomeTax, healthInsurance, unionFees);
    }

    /**
     * method to calculate the monthly gross pay
     * by dividing salary by 12
     * @return
     */
    public double calcGrossPay(){
        grossPay = salary / 12;
        return grossPay;
    }

    /**
     * Method to calculate the total prsi deduction from the grossPay depending on
     * am employees monthly earnings and the max tax credits they are entitled to
     * dependent on their salary.
     */
    public double calcPRSI(){
        double totalPRSIDeducted = 0;
        //if monthly gross pay = to 1408.04, find PRSI percentage of 352.01 across 4 weeks.
        if(grossPay == 1408.04){
            double PRSITotal = grossPay * PRSI;
            totalPRSIDeducted = PRSITotal - maxTaxCredit;
            //if gross pay is greater, find 1/6th of earnings after 1408.04
        }else if(grossPay > 1408.04){
            double oneSixthOfEarnings = (grossPay - 1408.04)/6;
            //minus that one sixth from the max tax credit
            double totalPRSICredit = maxTaxCredit - oneSixthOfEarnings;
            //find PRSI percentage of entire gross pay
            double PRSITotal = grossPay * PRSI;
            //minus total PRSI credit from the PRSI deduced from entire gross pay
            totalPRSIDeducted = PRSITotal - totalPRSICredit;
        }
        return totalPRSIDeducted;

    }

    /**
     *  Method to calculate an employees usc based on their yearly salary
     */
    public double calcUSC(){
        double totalUSCDeducted = 0;
        //if salary is greater than or equal to 13001 and is less than or equal to 25760
        //deduct 0.5% from the first 12012 and 2% from the remainder of what is earned
        if(salary >= 13001 && salary <= 25760){
            double USCDeductedFirst = 12012 * 0.005;
            double USCDeductedSecond = (salary - 12012) * 0.02;
            totalUSCDeducted = USCDeductedFirst + USCDeductedSecond;
            //if salary is greater than or equal to 25760.01 and less than or equal to 70044
            //deduct 4% from salary
        }else if(salary >= 25760.01 && salary <= 70044){
            totalUSCDeducted = salary * 0.04;
            //if it's greater, deduct 8% from the salary
        }else{
            totalUSCDeducted = salary * 0.08;

        }
        return totalUSCDeducted / 12;
    }

    /**
     * method to calculate income tax dependent on if a person is married or not,
     * and if their salary is above or under the band for their marital status.
      * @return
     */

    public double calcIncomeTax(){
        boolean maritalStatus = employee.getMarital_Status();
        double totalIncomeTax;
        if(maritalStatus && salary >= 80000) {
            totalIncomeTax = 80000 * 0.2;
        }else if(maritalStatus){
            totalIncomeTax = salary * 0.2;
        }else if(!maritalStatus && salary >= 40000){
            totalIncomeTax = 40000 * 0.2;
        }else{
            totalIncomeTax = salary * 0.2;
        }
        return totalIncomeTax / 12;
    }

    /**
     * method to calculate an employees health insurance deduction based on their yearly salary and then divided
     * by 12 to find the total deduction made to each employees monthly gross wage
     * @return
     */

    public double calcHealthInsurance(){
        double healthInsuranceDeducted;
        if(salary >= 20000 && salary < 35000){
            healthInsuranceDeducted = salary * 0.005;
        } else if(salary >= 35001 && salary < 55000){
            healthInsuranceDeducted = salary * 0.02;
        } else if(salary >= 55001 && salary < 75000){
            healthInsuranceDeducted = salary * 0.04;
        }else{
            healthInsuranceDeducted = salary * 0.06;
        }
        return healthInsuranceDeducted / 12;
    }

    public double calcUnionFees(){
        double unionFeesDeducted;
        if(employee.getUnionFees() == "A"){
            unionFeesDeducted = salary * 0.005;
        }else{
            unionFeesDeducted = salary * 0.01;
        }
        return unionFeesDeducted / 12;
    }

    /**
     * Calculates the net pay based on the results of the previous methods, using those results
     * to deduce the net pay after tax.
     */

    public double calcNetPay(double USCDeducted, double totalPRSIDeducted, double totalIncomeTax, double healthInsuranceDeducted, double unionFeesDeducted){
        netPay = grossPay - USCDeducted - totalPRSIDeducted - totalIncomeTax - healthInsuranceDeducted - unionFeesDeducted;
        return netPay;
    }

    /**
     * getter methods for global variables in the class
     * @return
     */

    public double getGrossPay(){
        return grossPay;
    }

    public double getNetPay(){
        return netPay;
    }

    public double getSalary(){
        return salary;
    }

    public double getUSC(){
        return USC;
    }

    public double getPRSI(){
        return PRSI;
    }


    public double getIncomeTax(){
        return incomeTax;
    }

     public double getHealthInsurance(){
        return healthInsurance;
    }

    public double getUnionFees(){
        return unionFees;
    }

    public LocalDate getDateOfProcess(){
        return dateOfProcess;
    }
}
