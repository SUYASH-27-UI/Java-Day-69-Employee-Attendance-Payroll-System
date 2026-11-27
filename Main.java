import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double monthlySalary;
    int presentDays = 0;
    int absentDays = 0;

    Employee(int id, String name, double monthlySalary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    int totalDays() {
        return presentDays + absentDays;
    }

    double calculateSalary() {
        return (monthlySalary / 30) * presentDays;
    }

    void display() {
        System.out.println("\nEmployee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Monthly Salary: Rs." + monthlySalary);
        System.out.println("Present Days: " + presentDays);
        System.out.println("Absent Days: " + absentDays);
        System.out.printf("Calculated Salary: Rs.%.2f%n",
                          calculateSalary());
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();

    static Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.id == id) {
                return employee;
            }
        }

        return null;
    }

    static void addEmployee() {
        System.out.print("\nEnter employee ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (findEmployee(id) != null) {
            System.out.println("Employee ID already exists!");
            return;
        }

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter monthly salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        if (!Double.isFinite(salary) || salary <= 0) {
            System.out.println("Invalid salary!");
            return;
        }

        employees.add(new Employee(id, name, salary));

        System.out.println("Employee added successfully!");
    }

    static void markAttendance() {
        System.out.print("\nEnter employee ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Employee employee = findEmployee(id);

        if (employee == null) {
            System.out.println("Employee not found!");
            return;
        }

        if (employee.totalDays() >= 30) {
            System.out.println("Monthly attendance limit reached!");
            return;
        }

        System.out.print("Mark attendance (P/A): ");
        String status = sc.nextLine().trim();

        if (status.equalsIgnoreCase("P")) {
            employee.presentDays++;
            System.out.println("Present marked successfully!");
        } else if (status.equalsIgnoreCase("A")) {
            employee.absentDays++;
            System.out.println("Absent marked successfully!");
        } else {
            System.out.println("Invalid attendance status!");
        }
    }

    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employee records found.");
            return;
        }

        System.out.println("\n===== EMPLOYEE RECORDS =====");

        for (Employee employee : employees) {
            employee.display();
        }
    }

    static void generatePayroll() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employee records found.");
            return;
        }

        System.out.println("\n===== MONTHLY PAYROLL =====");

        for (Employee employee : employees) {
            System.out.println("\nEmployee: " + employee.name);
            System.out.println("ID: " + employee.id);
            System.out.println("Present Days: " + employee.presentDays);

            System.out.printf("Salary: Rs.%.2f%n",
                              employee.calculateSalary());
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== ATTENDANCE & PAYROLL SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Display Employees");
            System.out.println("4. Generate Payroll");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        markAttendance();
                        break;

                    case 3:
                        displayEmployees();
                        break;

                    case 4:
                        generatePayroll();
                        break;

                    case 5:
                        System.out.println("System closed.");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric input!");
                choice = 0;
            }

        } while (choice != 5);

        sc.close();
    }
}
