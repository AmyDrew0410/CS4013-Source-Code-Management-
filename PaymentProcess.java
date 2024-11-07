public class PaymentProcess {
    private double grossPay;
    private double netPay;
    private double salary;
    private double PRSI;
    private double USC;
    private double PAYE;
    private double maxTaxCredit;

    //payment process constructor to create obj in generate payslips class
    public PaymentProcess(double grossPay, double netPay, double salary, double PRSI, double USC, double PAYE){
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.salary = salary;
        this.PRSI = PRSI;
        this.USC = USC;
        this.PAYE = PAYE;
    }

    /**
     * Method to calculate the total prsi deduction from the grossPay depending on
     * employees monthly earnings and the max tax credits they are entitled to
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
        return totalUSCDeducted;
    }

    /**
     * Calculates the net pay based on the results of the previous methods, using those results
     * to deduce the net pay after tax.
     */
    public double calcNetPay(double USCDeducted, double totalPRSIDeducted){
        netPay = (grossPay - USCDeducted) - totalPRSIDeducted;
        return netPay;
    }

    /**
     * gets and returns gross pay and makes it accessible in generate payslip class
     * @return
     */
    public double getGrossPay(){
        return grossPay;
    }

    /**
     * gets and returns net pay and makes it accessible in generate payslip class
     * @return
     */
    public double getNetPay(){
        return netPay;
    }

    /**
     * gets and returns salary and makes it accessible in generate payslip class
     * @return
     */
    public double getSalary(){
        return salary;
    }

    /**
     * gets and returns USC and makes it accesible in generate payslip class
     * @return
     */
    public double getUSC(){
        return USC;
    }
}
