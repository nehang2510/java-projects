import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    private double balance;
    private String accountNumber;
    private static int accountCounter = 1000; // To generate account numbers

    // Constructor to initialize account details
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = "BA" + accountCounter++;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    // View balance method
    public void viewBalance() {
        System.out.println("Account Balance: " + balance);
    }

    // Account details
    public void accountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + balance);
    }
}

class BankAccountManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("Welcome to Bank Account Management System");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Balance");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Create account
                    System.out.print("Enter your name: ");
                    sc.nextLine(); // Consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter initial deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account = new BankAccount(name, depositAmount);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    // Deposit money
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double deposit = sc.nextDouble();
                        account.deposit(deposit);
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;

                case 3:
                    // Withdraw money
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawal = sc.nextDouble();
                        account.withdraw(withdrawal);
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;

                case 4:
                    // View balance
                    if (account != null) {
                        account.viewBalance();
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;

                case 5:
                    // View account details
                    if (account != null) {
                        account.accountDetails();
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using our service!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}