import java.io.IOException;
import java.util.ArrayList;

import UserType.Admin;
import UserType.Employee;
import UserType.Employees;
import UserType.HR;
import UserType.PartTime;
import UserType.UserTypes;

/*public class EmployeeTest {
    public static void main(String[] args) {

        ArrayList<UserTypes> employees5 = new ArrayList<>();
        Employees employees = new Employees(employees5);

        UserTypes employee1 = new Employee("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes employee2 = new Admin("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes employee3 = new HR("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes partTime1 = new PartTime("Jack", "Ryan", 0, "0851741927", "Jack@company.com", false, employees, "12324", 30, "5%");

        employees5.add(employee1);
        employees5.add(employee2);
        employees5.add(employee3);
        employees5.add(partTime1);

        //System.out.println(employee1.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        //System.out.println(partTime1.toString());

        for(UserTypes user : employees.getListOfEmployees(partTime1)){
            System.out.println(user.toString());
        }

        employees.writeToEmployeeData("employees.csv");



        for(UserTypes user : employees.getListOfEmployees(employee2)){
            System.out.println(user.toString());
        }

        for(UserTypes user : employees.getListOfEmployees(employee1)){
            System.out.println(user.toString());
        }

        for(UserTypes user : employees.getListOfEmployees(employee3)){
            System.out.println(user.toString());
        }
    }
}*/

public class EmployeeTest {
    public static void main(String[] args) throws IOException {

        ArrayList<UserTypes> employees5 = new ArrayList<>();

        UserTypes employee1 = new Employee("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, "1234", "10");
        UserTypes employee2 = new Admin("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, "1234", "10");
        UserTypes employee3 = new HR("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, "1234", "10");
        UserTypes partTime1 = new PartTime("Jack", "Ryan", 0, "0851741927", "Jack@company.com", false, "12324", 30, "5%");

        employees5.add(employee1);
        employees5.add(employee2);
        employees5.add(employee3);
        employees5.add(partTime1);

        Employees employees = new Employees(employees5);

        // System.out.println(employee1.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        // System.out.println(partTime1.toString());

        for (UserTypes user : employees.getListOfEmployees(partTime1)) {
            System.out.println(user.toString());
        }

        employees.writeToEmployeeData("employees.csv");

        /*
         * for(UserTypes user : employees.getListOfEmployees(employee2)){
         * System.out.println(user.toString());
         * }
         * 
         * for(UserTypes user : employees.getListOfEmployees(employee1)){
         * System.out.println(user.toString());
         * }
         * 
         * for(UserTypes user : employees.getListOfEmployees(employee3)){
         * System.out.println(user.toString());
         * }
         */
    }
}
