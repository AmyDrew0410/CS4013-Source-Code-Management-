import java.util.Scanner;

public class PartTime extends Employee{
    
    public PartTime(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, String password, int currentPoints){
        super(first_Name, last_Name, employee_ID, email, phone_Number, password, currentPoints);
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

}
