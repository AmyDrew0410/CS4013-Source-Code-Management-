public class Employee {
    // Data fields
    private String first_Name;
    private String last_Name;
    private int employee_ID;
    private String email;
    private String phone_Number;
    private int current_Points;
    private String user_Type;

    //Constructor
    public Employee(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, int current_Points, String user_Type){
        this.last_Name = first_Name;
        this.last_Name = last_Name;
        this.employee_ID = employee_ID;
        this.email = email;
        this.phone_Number = phone_Number;
        this.current_Points = current_Points;
        this.user_Type = user_Type;
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    // Getter methods
    public String getFirst_Name(){
        return first_Name;
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

    public String user_Type(){
        return user_Type;
    }
}
