public class HR extends UserTypes{

    public HR(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, int current_Points, Boolean marital_Status, Employees employees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, current_Points, marital_Status, employees);
    }

    @Override
    public boolean AccessEmployeeList(){
        return true;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
