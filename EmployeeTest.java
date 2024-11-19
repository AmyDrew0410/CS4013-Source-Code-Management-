import java.util.Scanner;

public class EmployeeTest {
    public static void main(String[] args) {

        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        UserTypes employee1 = new Employee("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes employee2 = new Admin("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes employee3 = new HR("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, employees, "1234", "10");
        UserTypes partTime1 = new PartTime("Jack", "Ryan", 0, "0851741927", "Jack@company.com", false, employees, "12324", 30, scanner, "5%");

        //System.out.println(employee1.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        //System.out.println(partTime1.toString());
        scanner.close();

        for(UserTypes user : employees.getListOfEmployees(partTime1)){
            System.out.println(user.toString());
        }

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
}
