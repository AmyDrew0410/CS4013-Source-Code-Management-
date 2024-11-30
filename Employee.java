package UserType;

public class Employee extends UserTypes{
    
    /**
     * Constructor makes instance of a regular employee
     * @param firstName
     * @param last_Name
     * @param employee_ID
     * @param phone_Number
     * @param email
     * @param marital_Status
     * @param employees
     * @param PPSN
     * @param unionFees
     */
    public Employee(String firstName, String last_Name, int employee_ID, String phone_Number, String email, Boolean marital_Status, Employees employees, String PPSN, String unionFees){
        super(firstName, last_Name, employee_ID, phone_Number, email, marital_Status, employees, PPSN, "UserType.Employee", unionFees);
    }

    /**
     * Secondary constructor
     * @param first_Name
     * @param last_Name
     * @param employee_ID
     * @param phone_Number
     * @param email
     * @param marital_Status
     * @param PPSN
     * @param unionFees
     */
    public Employee(String first_Name, String last_Name, int employee_ID, String phone_Number,  String email, Boolean marital_Status, String PPSN, String unionFees){
        super(first_Name, last_Name, employee_ID, phone_Number, email, marital_Status, PPSN, "UserType.Employee", unionFees);
    }
    /**
     * Super from UserTypes, takes a string that represents CSV data and converts it back to an employee
     * @param data
     */
    public Employee(String data){
        super(data);
    }


    /**
     * Method gets first name
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
     * Method gets employee's ID
     * @return employee_ID
     */
    public int getEmployee_ID(){
        return employee_ID;
    }

    /**
     * Method gets email
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
     * Denies access to the list of employees for a regular employee
     * @return boolean
     */
    @Override
    public boolean AccessEmployeeList(){
        return false;
    }

    /**
     * Method prints out employee information
     * @return Strings
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
