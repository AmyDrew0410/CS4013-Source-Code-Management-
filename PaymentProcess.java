public class PaymentProcess {
    private double grossPay;
    private double netPay;
    private double salary;
    private double PRSI;
    private double USC;
    private double PAYE;
    private double maxTaxCredit;

    /**
     * Method to calculate the total prsi deduction from the grossPay depending on
     * employees monthly earnings and the max tax credits they are entitled to
     * dependent on their salary.
     */
    public double calcPRSI(double PRSI, double grossPay, double maxTaxCredit){
        if(grossPay == 1408.04){
            double PRSITotal = grossPay * PRSI;
            double totalPRSIDeducted = PRSITotal - maxTaxCredit;
            grossPay =- totalPRSIDeducted;
        }else if(grossPay > 1408.04){
            double oneSixthOfEarnings = (grossPay - 1408.04)/6;
            double totalPRSICredit = maxTaxCredit - oneSixthOfEarnings;
            double PRSITotal = grossPay - PRSI;
            double totalPRSIDeducted = PRSITotal - totalPRSICredit;
            grossPay =- totalPRSIDeducted;
        }
        return grossPay;
    }

    /**
     *  Method to calculate an employees usc based on their yearly salary
     */

    public double calcUSC(double USC, double salary){
        if(salary >= 13001 && salary <= 25760){
            double USCDeductedFirst = 12012 * 0.5;
            double USCDeductedSecond = (salary - 12012) * 2.0;
            double totalUSCDeducted = USCDeductedFirst + USCDeductedSecond;
            salary =- totalUSCDeducted;
        }else if(salary >= 25760.01 && salary <= 70044){
            double USCDeducted = salary * 4.0;
            salary =- USCDeducted;
        }else{
            double USCDeducted = salary * 8.0;
            salary =- USCDeducted;
        }
        return salary;
    }
    public double calcPAYE(){
        
    }
}
