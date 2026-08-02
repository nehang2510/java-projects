import java.util.Scanner;

/**
 * Employee Salary Calculator
 *
 * Computes gross and net salary from a basic salary using
 * standard percentage-based components:
 *
 *   HRA (House Rent Allowance) = 20% of basic
 *   DA  (Dearness Allowance)   = 10% of basic
 *   PF  (Provident Fund)       = 12% of basic  (deduction)
 *   Tax                        = 10% of gross  (deduction)
 *
 *   Gross Salary = Basic + HRA + DA
 *   Net Salary   = Gross - PF - Tax
 */
public class EmployeeSalaryCalculator {

    // Component rates kept as named constants so the formula is
    // readable and rates can be changed in one place.
    private static final double HRA_RATE = 0.20;
    private static final double DA_RATE  = 0.10;
    private static final double PF_RATE  = 0.12;
    private static final double TAX_RATE = 0.10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter basic salary: ");
        double basicSalary = scanner.nextDouble();

        // Guard against invalid input before doing any math.
        if (basicSalary < 0) {
            System.out.println("Error: basic salary cannot be negative.");
            scanner.close();
            return;
        }

        // Earnings
        double hra   = basicSalary * HRA_RATE;
        double da    = basicSalary * DA_RATE;
        double gross = basicSalary + hra + da;

        // Deductions
        double pf  = basicSalary * PF_RATE;   // PF is on basic, not gross
        double tax = gross * TAX_RATE;        // tax is on gross
        double net = gross - pf - tax;

        // Salary slip output
        System.out.println("\n========= SALARY SLIP =========");
        System.out.printf("Employee Name : %s%n", name);
        System.out.println("-------------------------------");
        System.out.printf("Basic Salary  : %,12.2f%n", basicSalary);
        System.out.printf("HRA (20%%)     : %,12.2f%n", hra);
        System.out.printf("DA  (10%%)     : %,12.2f%n", da);
        System.out.println("-------------------------------");
        System.out.printf("Gross Salary  : %,12.2f%n", gross);
        System.out.printf("PF  (12%%)     : %,12.2f (deduction)%n", pf);
        System.out.printf("Tax (10%%)     : %,12.2f (deduction)%n", tax);
        System.out.println("-------------------------------");
        System.out.printf("Net Salary    : %,12.2f%n", net);
        System.out.println("===============================");

        scanner.close();
    }
}