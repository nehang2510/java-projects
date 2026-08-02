import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contact Management System
 * Features: add contact (validated 10-digit phone, duplicate check),
 * display all contacts, search by name (partial, case-insensitive),
 * delete by phone number.
 */
public class ContactManagementSystem {

    /** Contact model. Phone stored as String, never as a numeric type. */
    static class Contact {
        private final String name;
        private final String phone;

        Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        String getName()  { return name; }
        String getPhone() { return phone; }
    }

    private static final List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Contact Management System =====");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");
            scanner.nextLine(); // consume leftover newline before any nextLine() reads

            switch (choice) {
                case 1 -> addContact(scanner);
                case 2 -> displayAllContacts();
                case 3 -> searchByName(scanner);
                case 4 -> deleteByPhone(scanner);
                case 5 -> {
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-5.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Add Contact");
        System.out.println("2. Display All Contacts");
        System.out.println("3. Search Contact by Name");
        System.out.println("4. Delete Contact by Phone");
        System.out.println("5. Exit");
    }

    private static void addContact(Scanner scanner) {
        String name = readNonEmptyLine(scanner, "Enter name: ");
        String phone = readValidPhone(scanner, "Enter 10-digit phone number: ");

        if (findByPhone(phone) != null) {
            System.out.println("A contact with phone " + phone + " already exists.");
            return;
        }

        contacts.add(new Contact(name, phone));
        System.out.println("Contact saved. Total contacts: " + contacts.size());
    }

    private static void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts saved yet.");
            return;
        }

        System.out.println("\n---------------------------------------------");
        System.out.printf("%-5s %-25s %-12s%n", "No.", "Name", "Phone");
        System.out.println("---------------------------------------------");
        int i = 1;
        for (Contact c : contacts) {
            System.out.printf("%-5d %-25s %-12s%n", i++, c.getName(), c.getPhone());
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total contacts: " + contacts.size());
    }

    private static void searchByName(Scanner scanner) {
        String query = readNonEmptyLine(scanner, "Enter name to search: ").toLowerCase();

        List<Contact> matches = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(query)) {
                matches.add(c);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No contacts match \"" + query + "\".");
            return;
        }

        System.out.println("\n--- " + matches.size() + " match(es) found ---");
        for (Contact c : matches) {
            System.out.printf("%-25s %s%n", c.getName(), c.getPhone());
        }
    }

    private static void deleteByPhone(Scanner scanner) {
        String phone = readValidPhone(scanner, "Enter phone number of contact to delete: ");
        Contact target = findByPhone(phone);

        if (target == null) {
            System.out.println("No contact found with phone " + phone + ".");
            return;
        }

        System.out.print("Delete " + target.getName() + " (" + target.getPhone() + ")? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        contacts.removeIf(c -> c.getPhone().equals(phone)); // safe removal, no CME
        System.out.println("Contact deleted. Total contacts: " + contacts.size());
    }

    /** Exact phone match; returns null if not found. Phone is the unique key. */
    private static Contact findByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
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

    private static String readNonEmptyLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    /**
     * Accepts exactly 10 digits. Strips spaces and hyphens first so
     * "98765 43210" and "98765-43210" are both accepted and normalized.
     */
    private static String readValidPhone(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();
            String normalized = raw.replaceAll("[\\s-]", "");

            if (normalized.matches("\\d{10}")) {
                return normalized;
            }
            System.out.println("Invalid phone. Enter exactly 10 digits (spaces/hyphens allowed).");
        }
    }
}