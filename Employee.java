public class Employee extends UserTypes{
    
    public Employee(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, Boolean marital_Status, String PPSN, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, PPSN, "Employee", unionFees);
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

    public String getPPSN(){
        return PPSN;
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
