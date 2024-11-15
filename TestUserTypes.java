import java.util.Scanner;

public class TestUserTypes {
    public static void main(String[] args){
        // Initialize the Employees list manager
        Employees employees = new Employees();

        Scanner scanner = new Scanner(System.in);


        // Create test users: Admin, HR, and a regular Employee
        Admin adminUser = new Admin("Alice", "Smith", 1, "alice@company.com", "123-456-7890", 100, true, employees, "1223");
        HR hrUser = new HR("Bob", "Jones", 2, "bob@company.com", "123-456-7891", 80, false, employees,"112244");
        Employee regularEmployee = new Employee("Carol", "Davis", 3, "carol@company.com", "123-456-7892", 50, true, employees,"114466");
        PartTime partTimeEmployee = new PartTime("Jack", "Ryan", "085-174-1927", "jack@company.com", "556677", 20, employees, scanner);

        /* 
        for (UserTypes user : employees.getListOfEmployees(adminUser))
        System.out.println(user.toString());



        for (UserTypes user : employees.getListOfEmployees(hrUser))
        System.out.println(user.toString());

        for (UserTypes user : employees.getListOfEmployees(regularEmployee))
        System.out.println(user.toString());
        
        UserTypes getInfo = employees.employeeInformation(3, adminUser);

        System.out.println(getInfo);
        */

        for (UserTypes user : employees.getListOfEmployees(adminUser))
        System.out.println(user.toString());
    }
}
