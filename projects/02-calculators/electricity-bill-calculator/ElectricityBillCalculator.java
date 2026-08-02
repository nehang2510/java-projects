import java.util.Scanner;

/**
 * Electricity Bill Calculator
 * Calculates a bill from units consumed using a PROGRESSIVE slab tariff:
 * each slab rate applies only to the units falling within that slab,
 * not to the total consumption. Prints an itemized breakdown.
 *
 * Tariff (typical Indian domestic structure, adjust rates as needed):
 *   Slab 1:   0-100 units  -> Rs. 3.50 / unit
 *   Slab 2: 101-200 units  -> Rs. 5.00 / unit
 *   Slab 3: 201-400 units  -> Rs. 6.50 / unit
 *   Slab 4:  401+   units  -> Rs. 8.00 / unit
 *   Fixed meter charge     -> Rs. 50.00
 *   Tax (electricity duty) -> 5% on energy charges
 */
public class ElectricityBillCalculator {

    /** One tariff slab: units up to `limit` (cumulative) billed at `rate`. */
    static class Slab {
        final int limit;        // cumulative upper bound; Integer.MAX_VALUE = open-ended
        final double rate;      // Rs. per unit within this slab
        final String label;

        Slab(int limit, double rate, String label) {
            this.limit = limit;
            this.rate = rate;
            this.label = label;
        }
    }

    // Slabs must be in ascending order of limit; last slab is open-ended.
    private static final Slab[] SLABS = {
            new Slab(100,               3.50, "0-100"),
            new Slab(200,               5.00, "101-200"),
            new Slab(400,               6.50, "201-400"),
            new Slab(Integer.MAX_VALUE, 8.00, "401+"),
    };

    private static final double FIXED_CHARGE = 50.00;
    private static final double TAX_RATE = 0.05;   // 5% on energy charges

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Electricity Bill Calculator =====");

        boolean again = true;
        while (again) {
            int units = readNonNegativeInt(scanner, "\nEnter units consumed: ");
            printBill(units);

            System.out.print("\nCalculate another bill? (y/n): ");
            again = scanner.next().trim().equalsIgnoreCase("y");
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    /** Computes and prints an itemized bill for the given units. */
    private static void printBill(int units) {
        System.out.println("\n================ ELECTRICITY BILL ================");
        System.out.printf("Units consumed : %d%n", units);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s %-10s %-12s %-12s%n", "Slab", "Units", "Rate/Unit", "Amount");
        System.out.println("--------------------------------------------------");

        double energyCharges = 0.0;
        int previousLimit = 0;
        int remaining = units;

        for (Slab slab : SLABS) {
            if (remaining <= 0) break;

            int slabCapacity = slab.limit == Integer.MAX_VALUE
                    ? remaining                       // open-ended top slab
                    : slab.limit - previousLimit;     // width of this slab

            int unitsInSlab = Math.min(remaining, slabCapacity);
            double amount = unitsInSlab * slab.rate;
            energyCharges += amount;

            System.out.printf("%-12s %-10d Rs. %-8.2f Rs. %-10.2f%n",
                    slab.label, unitsInSlab, slab.rate, amount);

            remaining -= unitsInSlab;
            previousLimit = slab.limit;
        }

        double tax = energyCharges * TAX_RATE;
        double total = energyCharges + FIXED_CHARGE + tax;

        System.out.println("--------------------------------------------------");
        System.out.printf("%-35s Rs. %10.2f%n", "Energy charges", energyCharges);
        System.out.printf("%-35s Rs. %10.2f%n", "Fixed meter charge", FIXED_CHARGE);
        System.out.printf("%-35s Rs. %10.2f%n", "Tax (5% on energy charges)", tax);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-35s Rs. %10.2f%n", "TOTAL PAYABLE", total);
        System.out.println("==================================================");

        if (units > 0) {
            System.out.printf("Effective average rate: Rs. %.2f per unit%n", total / units);
        }
    }

    /** Reads an int >= 0; re-prompts on invalid or negative input. */
    private static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a whole number.");
                scanner.next(); // discard bad token
                continue;
            }
            int value = scanner.nextInt();
            if (value < 0) {
                System.out.println("Units cannot be negative.");
                continue;
            }
            return value;
        }
    }
}