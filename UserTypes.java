public abstract class UserTypes {
    protected String first_Name;
    protected String last_Name;
    protected int employee_ID;
    protected String email;
    protected String phone_Number;
    protected Boolean marital_Status;
    protected String PPSN;
    protected Occupation occupation;
    protected String userType;

    //Constructor
    public UserTypes(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, Employees employees, String PPSN, String userType){
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.employee_ID = employee_ID;
        this.email = email;
        this.phone_Number = phone_Number;
        this.marital_Status = marital_Status;
        this.PPSN = PPSN;
        employees.addEmployee(this); // adds employee to the ListOfEmployees
        OccupationMenu menu = new OccupationMenu();
        occupation = menu.run();
        this.userType = userType;
    }

    public UserTypes(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, String PPSN, String userType){
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.employee_ID = employee_ID;
        this.email = email;
        this.phone_Number = phone_Number;
        this.marital_Status = marital_Status;
        this.PPSN = PPSN;
        OccupationMenu menu = new OccupationMenu();
        occupation = menu.run();
        this.userType = userType;
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

    public String getPPSN(){
        return PPSN;
    }

    public String getUserType(){
        return userType;
    }

    public abstract boolean AccessEmployeeList();

    

    @Override
    public String toString(){
        return "First name: " + first_Name + "\n" +
               "Last name: " + last_Name + "\n" +
               "Employee ID: " + employee_ID + "\n" +
               "Email: " + email + "\n" +
               "Phone number: " + phone_Number + "\n" +
               "Current points: " + "\n" +
                occupation.toString();
    }
}