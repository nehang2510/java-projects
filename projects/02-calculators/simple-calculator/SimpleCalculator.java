import java.util.Scanner;

/**
 * Simple Calculator
 * Add, subtract, multiply, divide two numbers via a menu loop.
 * Handles division by zero, non-numeric input, and modulo as a bonus op.
 */
public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Simple Calculator =====");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");

            if (choice == 6) {
                System.out.println("Exiting. Goodbye!");
                running = false;
                continue;
            }
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please select 1-6.");
                continue;
            }

            double a = readDouble(scanner, "Enter first number : ");
            double b = readDouble(scanner, "Enter second number: ");

            switch (choice) {
                case 1 -> printResult(a, "+", b, a + b);
                case 2 -> printResult(a, "-", b, a - b);
                case 3 -> printResult(a, "*", b, a * b);
                case 4 -> divide(a, b);
                case 5 -> modulo(a, b);
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Modulo (remainder)");
        System.out.println("6. Exit");
    }

    private static void divide(double a, double b) {
        if (b == 0) {
            // Explicit guard: with doubles, a/0 silently yields Infinity or NaN
            // instead of throwing ArithmeticException like int division does.
            System.out.println("Error: division by zero is not allowed.");
            return;
        }
        printResult(a, "/", b, a / b);
    }

    private static void modulo(double a, double b) {
        if (b == 0) {
            System.out.println("Error: modulo by zero is not allowed.");
            return;
        }
        printResult(a, "%", b, a % b);
    }

    /** Prints results as integers when whole (5 + 3 = 8, not 8.0). */
    private static void printResult(double a, String op, double b, double result) {
        System.out.printf("Result: %s %s %s = %s%n",
                trim(a), op, trim(b), trim(result));
    }

    /** Renders 8.0 as "8" but keeps 8.5 as "8.5". */
    private static String trim(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    // ---------- Input helpers (re-prompt instead of crashing) ----------

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Invalid input. Enter a whole number.");
            scanner.next(); // discard bad token
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Invalid input. Enter a number.");
            scanner.next(); // discard bad token
        }
    }
}