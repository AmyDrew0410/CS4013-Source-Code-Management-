public abstract class UserTypes {
    protected String first_Name;
    protected String last_Name;
    protected int employee_ID;
    protected String email;
    protected String phone_Number;
    protected int current_Points;
    protected Boolean marital_Status;
    protected String PPSN;

    //Constructor
    public UserTypes(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, int current_Points, Boolean marital_Status, Employees employees, String PPSN){
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.employee_ID = employee_ID;
        this.email = email;
        this.phone_Number = phone_Number;
        this.current_Points = current_Points;
        this.marital_Status = marital_Status;
        this.PPSN = PPSN;
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    // Getter methods
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

    public abstract boolean AccessEmployeeList();

    

    @Override
    public String toString(){
        return "First name: " + first_Name + "\n" +
               "Last name: " + last_Name + "\n" +
               "Employee ID: " + employee_ID + "\n" +
               "Email: " + email + "\n" +
               "Phone number: " + phone_Number + "\n" +
               "Current points: " + current_Points + "\n";
    }
}
