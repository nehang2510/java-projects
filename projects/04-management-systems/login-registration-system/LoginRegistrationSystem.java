import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Login & Registration System
 * Features: register (username rules, password strength check, duplicate check),
 * login (salted SHA-256 hash comparison, 3-attempt lockout per user),
 * passwords are NEVER stored in plain text.
 */
public class LoginRegistrationSystem {

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    /** Stored credentials: salt + hash, never the password itself. */
    static class User {
        final String username;
        final String saltB64;     // Base64-encoded random salt
        final String hashB64;     // Base64-encoded SHA-256(salt + password)
        int failedAttempts = 0;
        boolean locked = false;

        User(String username, String saltB64, String hashB64) {
            this.username = username;
            this.saltB64 = saltB64;
            this.hashB64 = hashB64;
        }
    }

    // Keyed by lowercase username so "Admin" and "admin" are the same account
    private static final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Login & Registration System =====");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> register(scanner);
                case 2 -> login(scanner);
                case 3 -> {
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-3.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    // ---------- Registration ----------

    private static void register(Scanner scanner) {
        String username = readValidUsername(scanner);
        if (username == null) return; // duplicate, message already printed

        String password = readValidPassword(scanner);

        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        String saltB64 = Base64.getEncoder().encodeToString(salt);
        String hashB64 = hashPassword(password, salt);

        users.put(username.toLowerCase(), new User(username, saltB64, hashB64));
        System.out.println("Registration successful. You can now log in as \"" + username + "\".");
    }

    /** Username: 3-20 chars, letters/digits/underscore, must not already exist. */
    private static String readValidUsername(Scanner scanner) {
        while (true) {
            System.out.print("Choose a username (3-20 chars, letters/digits/_): ");
            String username = scanner.nextLine().trim();

            if (!username.matches("[A-Za-z0-9_]{3,20}")) {
                System.out.println("Invalid format. Use 3-20 letters, digits, or underscores.");
                continue;
            }
            if (users.containsKey(username.toLowerCase())) {
                System.out.println("Username already taken. Choose another.");
                return null;
            }
            return username;
        }
    }

    /** Password: min length, must contain a letter and a digit, confirmed twice. */
    private static String readValidPassword(Scanner scanner) {
        while (true) {
            System.out.printf("Choose a password (min %d chars, at least 1 letter and 1 digit): ",
                    MIN_PASSWORD_LENGTH);
            String password = scanner.nextLine();

            if (password.length() < MIN_PASSWORD_LENGTH) {
                System.out.println("Too short.");
                continue;
            }
            if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
                System.out.println("Must contain at least one letter and one digit.");
                continue;
            }

            System.out.print("Confirm password: ");
            String confirm = scanner.nextLine();
            if (!password.equals(confirm)) {
                System.out.println("Passwords do not match. Try again.");
                continue;
            }
            return password;
        }
    }

    // ---------- Login ----------

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        User user = users.get(username.toLowerCase());

        // NOTE: same message whether the user exists or not — prevents
        // username enumeration (attacker probing which accounts exist).
        if (user == null) {
            promptPasswordAndFailGenerically(scanner);
            return;
        }
        if (user.locked) {
            System.out.println("This account is locked due to repeated failures.");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        byte[] salt = Base64.getDecoder().decode(user.saltB64);
        String attemptHash = hashPassword(password, salt);

        if (constantTimeEquals(attemptHash, user.hashB64)) {
            user.failedAttempts = 0;
            System.out.println("\nLogin successful. Welcome, " + user.username + "!");
        } else {
            user.failedAttempts++;
            int remaining = MAX_LOGIN_ATTEMPTS - user.failedAttempts;
            if (remaining <= 0) {
                user.locked = true;
                System.out.println("Invalid username or password. Account locked.");
            } else {
                System.out.println("Invalid username or password. Attempts remaining: " + remaining);
            }
        }
    }

    /** Consumes a password prompt for a nonexistent user, then fails generically. */
    private static void promptPasswordAndFailGenerically(Scanner scanner) {
        System.out.print("Password: ");
        scanner.nextLine(); // read and discard
        System.out.println("Invalid username or password.");
    }

    // ---------- Crypto helpers ----------

    /** SHA-256(salt || password), Base64-encoded. */
    private static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            md.update(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 is mandated by the JVM spec; this cannot happen in practice
            throw new IllegalStateException("SHA-256 unavailable", e);
        }
    }

    /** Compares two strings in constant time to avoid timing side channels. */
    private static boolean constantTimeEquals(String a, String b) {
        return MessageDigest.isEqual(
                a.getBytes(StandardCharsets.UTF_8),
                b.getBytes(StandardCharsets.UTF_8));
    }

    // ---------- Input helper ----------

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
}