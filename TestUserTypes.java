public class TestUserTypes {
    public static void main(String[] args){
        // Initialize the Employees list manager
        Employees employees = new Employees();

        // Create test users: Admin, HR, and a regular Employee
        Admin adminUser = new Admin("Alice", "Smith", 1, "alice@company.com", "123-456-7890", 100, true, employees);
        HR hrUser = new HR("Bob", "Jones", 2, "bob@company.com", "123-456-7891", 80, false, employees);
        Employee regularEmployee = new Employee("Carol", "Davis", 3, "carol@company.com", "123-456-7892", 50, true, employees);

        for (UserTypes user : employees.getListOfEmployees(adminUser))
        System.out.println(user.toString());

        for (UserTypes user : employees.getListOfEmployees(hrUser))
        System.out.println(user.toString());

        for (UserTypes user : employees.getListOfEmployees(regularEmployee))
        System.out.println(user.toString());
        
        UserTypes getInfo = employees.employeeInformation(3, adminUser);

        System.out.println(getInfo);
    }
}
