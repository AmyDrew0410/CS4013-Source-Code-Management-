import java.util.Scanner;

public class PartTime extends Employee{
    private double hours_Per_Week;
    
    public PartTime(String first_Name, String last_Name, String email, String phone_Number, double hours_Per_Week, boolean validateFormFilled){
        super(first_Name, last_Name, email, phone_Number);
        this.hours_Per_Week = hours_Per_Week;
        this.validateFormFilled = validateFormFilled();
    }

    

    public boolean validateFormFilled(){
        Scanner scanner = new Scanner();
        System.out.println("Have you filled out the Part-time payment form? ");
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

    @Override
    public String toString(){
        return super.toString() + "Hours worked per week: " + hours_Per_Week + "\n";
    }

}
