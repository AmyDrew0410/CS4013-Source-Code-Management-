public class Admin extends UserTypes{
    
    public Admin(String first_Name, String last_Name, int employee_ID, String phone_Number, int current_Points, String email, Boolean marital_Status){
        super(first_Name, last_Name, employee_ID, phone_Number, current_Points, email, marital_Status);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}