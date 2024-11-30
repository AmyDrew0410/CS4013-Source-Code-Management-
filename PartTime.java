package UserType;

import java.util.Scanner;

public class PartTime extends Employee{

    private double hours_Per_Week;
    private boolean formFilledOut;
    
    /**
     * Constructor makes a part time object
     * @param first_Name
     * @param last_Name
     * @param employee_ID
     * @param phone_Number
     * @param email
     * @param marital_Status
     * @param employees
     * @param PPSN
     * @param hours_Per_Week
     * @param scanner
     * @param unionFees
     */
    public PartTime(String first_Name, String last_Name,int employee_ID, String phone_Number, String email, boolean marital_Status, Employees employees, String PPSN, double hours_Per_Week, Scanner scanner, String unionFees){
        super(first_Name, last_Name, 101, phone_Number, email, false, PPSN, unionFees);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled(scanner);
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    /**
     * Method checks if the part time employee has filled out the payment form
     * @param scanner
     * @return boolean
     */
    public boolean validateFormFilled(Scanner scanner){
        System.out.println("Have you filled out the Part-time payment form? (y) / (n) ");
        String input = scanner.nextLine();

        if(input.equalsIgnoreCase("y")){
            System.out.println("Form filled out.");
            return true;
        }
        else{
            System.out.println("Form not filled out.");
            return false;
        }
    }

    /**
     * Method sets the amount of hours worked by the part time employee
     * @param hours_Per_Week
     */
    public void setHours_Per_Week(double hours_Per_Week){
        this.hours_Per_Week = hours_Per_Week;
    }

    /**
     * Method checks if the form is filled out,
     * Used with the validateFormFilled method
     * @return boolean
     */
    public boolean isFormFilledOut(){
        return formFilledOut;
    }

    @Override
    public String toString(){
        return super.toString() + "Hours worked per week: " + hours_Per_Week + "\n" + "Is form filled: " + formFilledOut + "\n";
    }

}
