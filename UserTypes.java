package UserType;

import Occupations.*;


public abstract class UserTypes {
    protected String firstName;
    protected String last_Name;
    protected int employee_ID;
    protected String email;
    protected String phone_Number;
    protected Boolean marital_Status;
    protected String PPSN;
    public Occupation occupation;
    protected String userType;
    protected String unionFees;

    /**
     * Constructor that creates an instance of the type UserType
     * @param firstName
     * @param last_Name
     * @param employee_ID
     * @param email
     * @param phone_Number
     * @param marital_Status
     * @param employees
     * @param PPSN
     * @param userType
     * @param unionFees
     */
    public UserTypes(String firstName, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, Employees employees, String PPSN, String userType, String unionFees){
        this.firstName = firstName;
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
        this.unionFees = unionFees;
    }

    /**
     * The constructor takes a String value and separates it into an array,
     * Designed for reading into a CSV file
     * @param csvData
     */
    public UserTypes(String csvData)
    {
        String[] split = csvData.split(",");

        this.firstName = split[0]; // first index contains first name
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
    /**
     * constructor
     * @param firstName
     * @param last_Name
     * @param employee_ID
     * @param email
     * @param phone_Number
     * @param marital_Status
     * @param PPSN
     * @param userType
     * @param unionFees
     */
    public UserTypes(String firstName, String last_Name, int employee_ID, String email, String phone_Number, Boolean marital_Status, String PPSN, String userType, String unionFees){
        this.firstName = firstName;
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

    /**
     * Method gets firstName
     * @return firstName
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Method gets marital status
     * @return marital_Status
     */
    public Boolean getMarital_Status(){
        return marital_Status;
    }

    /**
     * Method gets last name
     * @return last_Name
     */
    public String getLast_Name(){
        return last_Name;
    }

    /**
     * Method gets the employee's id
     * @return employee_id
     */
    public int getEmployee_ID(){
        return employee_ID;
    }

    /**
     * Method gets the email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Method gets phone number
     * @return phone_Number
     */
    public String getPhone_Number(){
        return phone_Number;
    }

    /**
     * Method gets PPS number
     * @return PPSN
     */
    public String getPPSN(){
        return PPSN;
    }

    /**
     * Method gets the User Type
     * @return userType
     */
    public String getUserType(){
        return userType;
    }

    /**
     * Method gets the union fees
     * @return unionFees
     */
    public String getUnionFees(){
        return unionFees;
    }

    /**
     * Abstract method that gives access to the list for different user types
     * @return
     */
    public abstract boolean AccessEmployeeList();

    
    
    /**
     * To String method that prints out the user information
     * @return Information on employee
     */
    @Override
    public String toString(){
        return "First name: " + firstName + "\n" +
               "Last name: " + last_Name + "\n" +
               "Employee ID: " + employee_ID + "\n" +
               "Email: " + email + "\n" +
               "Phone number: " + phone_Number + "\n" +
                occupation.toString();
    }

    /**
     * Method gets salary
     * @return Salary
     */
    public Object getSalary() {
        return occupation.getSalary();
    }

    /**
     * Method formats the fields of userTypes into a comma seperated string to be read into a CSV file
     * @return String
     */
    public String toCSV(){
        return String.format("%s,%s,%d,%s,%s,%b,%s,%s,%s,%s",firstName,last_Name,employee_ID,email,phone_Number,marital_Status,getPPSN(),occupation.toCSV(),userType,unionFees);

    }

}
