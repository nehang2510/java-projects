import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Multi-User ATM Simulation (4 accounts)
 * Flow: enter account number -> verify that account's PIN (3 attempts) -> transact.
 * Each account has its own PIN and balance. Supports multiple sessions
 * (after one user exits, the next user can log in).
 */
public class ATMSimulation {

    private static final int MAX_ATTEMPTS = 3;
    private static final double MAX_WITHDRAWAL_PER_TXN = 25000.0;

    /** Simple account model. */
    static class Account {
        final String accountNumber;
        final String holderName;
        private final String pin;   // String preserves leading zeros e.g. "0456"
        private double balance;
        private boolean locked = false;

        Account(String accountNumber, String holderName, String pin, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.pin = pin;
            this.balance = balance;
        }

        boolean checkPin(String entered) { return pin.equals(entered); }
        double getBalance()              { return balance; }
        void deposit(double amount)      { balance += amount; }
        void withdraw(double amount)     { balance -= amount; }
        boolean isLocked()               { return locked; }
        void lock()                      { locked = true; }
    }

    // In-memory "bank database" of 4 users
    private static final Map<String, Account> ACCOUNTS = new LinkedHashMap<>();
    static {
        ACCOUNTS.put("1001", new Account("1001", "nehang",   "2507", 1000000.0));
        ACCOUNTS.put("1002", new Account("1002", "Bhadi",     "4321", 250000.0));
        ACCOUNTS.put("1003", new Account("1003", "kj", "5499", 5000.0));
        ACCOUNTS.put("1004", new Account("1004", "kapatar",   "0054", 50000.0));
        ACCOUNTS.put("1004", new Account("1004", "kapatar",   "0054", 500.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Welcome to the ATM =====");

        boolean atmRunning = true;
        while (atmRunning) {
            Account account = login(scanner);
            if (account != null) {
                runSession(scanner, account);
            }

            System.out.print("\nAllow another user to log in? (y/n): ");
            String again = scanner.next().trim().toLowerCase();
            if (!again.equals("y")) {
                atmRunning = false;
            }
        }

        System.out.println("ATM shutting down. Goodbye!");
        scanner.close();
    }

    /** Asks for account number, then that account's PIN. Returns null on failure. */
    private static Account login(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNo = scanner.next().trim();

        Account account = ACCOUNTS.get(accNo);
        if (account == null) {
            System.out.println("Account not found.");
            return null;
        }
        if (account.isLocked()) {
            System.out.println("This account is locked. Contact your bank.");
            return null;
        }

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Enter 4-digit PIN: ");
            String pin = scanner.next().trim();

            if (account.checkPin(pin)) {
                System.out.println("\nPIN verified. Welcome, " + account.holderName + "!");
                return account;
            }
            int remaining = MAX_ATTEMPTS - attempt;
            if (remaining > 0) {
                System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
            }
        }

        account.lock();
        System.out.println("Too many incorrect attempts. Account locked.");
        return null;
    }

    /** Transaction loop for one logged-in user. */
    private static void runSession(Scanner scanner, Account account) {
        boolean loggedIn = true;
        while (loggedIn) {
            printMenu(account.holderName);
            int choice = readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> checkBalance(account);
                case 2 -> deposit(scanner, account);
                case 3 -> withdraw(scanner, account);
                case 4 -> {
                    System.out.println("Logging out. Thank you, " + account.holderName + "!");
                    loggedIn = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-4.");
            }
        }
    }

    private static void printMenu(String name) {
        System.out.println("\n===== ATM Menu (" + name + ") =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Logout");
    }

    private static void checkBalance(Account account) {
        System.out.printf("Current balance: Rs. %.2f%n", account.getBalance());
    }

    private static void deposit(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter deposit amount: ");
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        account.deposit(amount);
        System.out.printf("Rs. %.2f deposited successfully.%n", amount);
        checkBalance(account);
    }

    private static void withdraw(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter withdrawal amount: ");

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if (amount % 100 != 0) {
            System.out.println("Withdrawal amount must be in multiples of 100.");
            return;
        }
        if (amount > MAX_WITHDRAWAL_PER_TXN) {
            System.out.printf("Withdrawal limit per transaction is Rs. %.2f.%n", MAX_WITHDRAWAL_PER_TXN);
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance for this withdrawal.");
            checkBalance(account);
            return;
        }

        account.withdraw(amount);
        System.out.printf("Rs. %.2f withdrawn successfully. Please collect your cash.%n", amount);
        checkBalance(account);
    }

    /** Reads an int safely; re-prompts on invalid input instead of crashing. */
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Invalid input. Please enter a whole number.");
            scanner.next(); // discard bad token
        }
    }

    /** Reads a double safely; re-prompts on invalid input instead of crashing. */
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Invalid input. Please enter a numeric amount.");
            scanner.next(); // discard bad token
        }
    }
}