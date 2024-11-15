import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartTime extends Employee{

    private List<PartTime> partTimeList= new ArrayList<>();
    private double hours_Per_Week;
    private boolean formFilledOut;
    
    /*public PartTime(String first_Name, String last_Name,int employee_ID, String phone_Number, String email, int current_Points, boolean marital_Status, Employees employee, String PPSN, double hours_Per_Week, Scanner scanner){
        super(first_Name, last_Name, partTimeList.size() + 1, phone_Number, email, 0, false, employee, PPSN);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled(scanner);
        partTimeList.add(this);

    }*/

    public PartTime(String first_Name, String last_Name, int employee_ID, String phone_Number, String email, int current_Points, boolean marital_Status, Employees employee, String PPSN, double hours_Per_Week, Scanner scanner) {
        super(first_Name, last_Name, employee_ID, phone_Number, email, current_Points, marital_Status, employee, PPSN);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled(scanner);
        partTimeList.add(this);
    }

// Overloaded constructor with default maritalStatus (set to false) and default currentPoints (set to 0)
    public PartTime(String first_Name, String last_Name, String phone_Number, String email, String PPSN, double hours_Per_Week, Employees employee, Scanner scanner) {
        this(first_Name, last_Name, partTimeList.size() + 1, phone_Number, email, 0, false, employee, PPSN, hours_Per_Week, scanner);
    }


    public boolean validateFormFilled(Scanner scanner){
        //Scanner scanner = new Scanner(System.in);
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

    public List<PartTime> getPartTimeList(UserTypes users){
        if(users instanceof Admin){
        return partTimeList;
        }
        else if(users instanceof HR){
            return new ArrayList<>(partTimeList);
        }
        else{
            System.out.println("Access denied.");
            return new ArrayList<>(); // empty list (means nothing)
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
        return super.toString() + "Hours worked per week: " + hours_Per_Week + "\n";
    }

}
