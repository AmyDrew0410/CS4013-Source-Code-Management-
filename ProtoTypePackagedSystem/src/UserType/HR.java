package UserType;

public class HR extends UserTypes {

    public HR(String firstName, String last_Name, int employee_ID, String phone_Number, String email, Boolean marital_Status, Employees employees, String PPSN, String unionFees){
        super(firstName, last_Name, employee_ID, phone_Number, email, marital_Status, employees, PPSN, "UserType.HR", unionFees);
    }

    public HR(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, Boolean marital_Status, String PPSN, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, PPSN, "UserType.HR", unionFees);
    }

    public String getFirstName(){
        return firstName;
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
