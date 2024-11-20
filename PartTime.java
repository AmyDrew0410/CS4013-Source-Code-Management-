package UserType;

import java.util.Scanner;

public class PartTime extends Employee{

    private double hours_Per_Week;
    private boolean formFilledOut;
    
    public PartTime(String first_Name, String last_Name,int employee_ID, String phone_Number, String email, boolean marital_Status, Employees employees, String PPSN, double hours_Per_Week, String unionFees){
        super(first_Name, last_Name, 101, phone_Number, email, false, PPSN, unionFees);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled();
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    public PartTime(String first_Name, String last_Name, int employee_ID, String phone_Number, String email, boolean marital_Status, String PPSN, double hours_Per_Week, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, PPSN, unionFees);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled();
    }


    public boolean validateFormFilled(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Have you filled out the Part-time payment form? (y) / (n) ");
        String input = scanner.nextLine();
        scanner.close();

        if(input.equalsIgnoreCase("y")){
            System.out.println("Form filled out.");
            return true;
        }
        else{
            System.out.println("Form not filled out.");
            return false;
        }
    }

    public void setHours_Per_Week(double hours_Per_Week){
        this.hours_Per_Week = hours_Per_Week;
    }

    public boolean isFormFilledOut(){
        return formFilledOut;
    }

    @Override
    public String toString(){
        return super.toString() + "Hours worked per week: " + hours_Per_Week + "\n" + "Is form filled: " + formFilledOut + "\n";
    }

}
