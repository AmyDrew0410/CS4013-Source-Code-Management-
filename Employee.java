public class Employee extends UserTypes{
    
    Employee(String first_Name, String last_Name, int employee_ID, String phone_Number, int current_Points, String email, boolean marital_Status){
        super(first_Name, last_Name, employee_ID, phone_Number, current_Points, email, marital_Status);

    }

    @Override
    public boolean AccessEmployeeList(){
        return false;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
