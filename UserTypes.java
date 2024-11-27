package UserType;

import Occupations.Occupation;
import Occupations.OccupationMenu;


public abstract class UserTypes {
    protected String first_Name;
    protected String last_Name;
    protected int employee_ID;
    protected String email;
    protected String phone_Number;
    protected Boolean marital_Status;
    protected String PPSN;
    public Occupation occupation;
    protected String userType;
    protected String unionFees;

    //Constructor
    public UserTypes(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, Employees employees, String PPSN, String userType, String unionFees){
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
        this.unionFees = unionFees;
        employees.addEmployee(this); // adds employee to the ListOfEmployees
    }

    public UserTypes(String csvData)
    {
        String[] split = csvData.split(",");

        this.first_Name = split[0]; // first index contains first name
        this.last_Name = split[1]; // Second index contains last name
        this.employee_ID = Integer.parseInt(split[2]); // Third index parsed to an int contains employeeID
        this.email = split[3]; // Fourth index contains email
        this.phone_Number = split[4]; // Fifth index contains phone number
        this.marital_Status = split[5].contains("true"); // If sixth index contains true return true otherwise return false
        this.PPSN = split[6]; // seventh index contains PPSN
        this.occupation = new Occupation(split[7],split[8],Integer.parseInt(split[9]),Double.parseDouble(split[10])); // Indexs 8 - 11 contain occupational data
        this.userType = split[11]; // twelfth index contains the userType
        this.unionFees = split[12]; // Thirteenth index contains the unionType

    }

    public UserTypes(String first_Name, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, String PPSN, String userType, String unionFees){
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
        this.unionFees = unionFees;
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

    public String getUnionFees(){
        return unionFees;
    }

    public abstract boolean AccessEmployeeList();


    @Override
    public String toString(){
        return "First name: " + first_Name + "\n" +
               "Last name: " + last_Name + "\n" +
               "Employee ID: " + employee_ID + "\n" +
               "Email: " + email + "\n" +
               "Phone number: " + phone_Number + "\n" +
                occupation.toString();
    }

    public String toCSV(){
        return String.format("%s, %s, %d, %s, %s, %b, %s, %s, %s, %s" , first_Name, last_Name, employee_ID, email, phone_Number, marital_Status, PPSN, occupation.toCSV(), userType, unionFees);
    }

    public Object getSalary() {
        return occupation.getSalary();
    }
}
