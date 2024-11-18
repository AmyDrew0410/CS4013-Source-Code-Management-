public class EmployeeTest {
    public static void main(String[] args) {
        UserTypes employee1 = new Employee("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, "1234");
        UserTypes employee2 = new Admin("Jack", "Ryan", 1, "09876543", "Jcak@company.com", null, "1234");

        //System.out.println(employee1.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(employee2.toString());
    }
}
