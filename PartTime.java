import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartTime extends Employee{

    private static List<PartTime> partTimeList= new ArrayList<>();
    private double hours_Per_Week;
    private boolean formFilledOut;
    
    public PartTime(String first_Name, String last_Name, String email, String phone_Number, double hours_Per_Week, Scanner scanner){
        super(first_Name, last_Name, email, phone_Number);
        this.hours_Per_Week = hours_Per_Week;
        this.formFilledOut = validateFormFilled(scanner);
        partTimeList.add(this);

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
