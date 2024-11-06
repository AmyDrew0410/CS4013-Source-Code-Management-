public class Employee {
    // Data fields
    private String name;
    private int employee_ID;
    private String email;
    private String phone_Number;
    private String password;
    private int currentPoints;
    private String role;

    //Constructor
    public Employee(String name, int employee_ID, String email, String phone_Number, String password, int currentPoints, String role){
        this.name = name;
        this.employee_ID = employee_ID;
        this.email = email;
        this.phone_Number = phone_Number;
        this.password = password;
        this.currentPoints = currentPoints;
        this.roile = role;
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    // Getter methods
    public String getName(){
        return name;
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

    public String getPassword(){
        return password;
    }

    public int getCurrentPoints(){
        return currentPoints;
    }

    public String role(){
        return role;
    }
}
