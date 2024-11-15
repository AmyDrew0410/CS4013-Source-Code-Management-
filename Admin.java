public class Admin extends UserTypes{
    
    public Admin(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, int current_Points, Boolean marital_Status, Employees employees, String PPSN){
        super(first_Name, last_Name, employee_ID, phone_Number, email, current_Points, marital_Status, employees, PPSN);
    }

    public String getFirst_Name(){
        return first_Name;
    }

    public Boolean getMarital_Status(){
        return marital_Status;
    }

    public String getLast_Name(){
        return last_Name;
    }

    public int getEmployee_ID(){
        return employee_ID;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone_Number(){
        return phone_Number;
    }

    public int getCurrent_Points(){
        return current_Points;
    }

    public String getPPSN(){
        return PPSN;
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