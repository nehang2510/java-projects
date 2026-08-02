import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Simple Library Book System.
 * Features: add books, issue (check out) books, return books, list all books.
 * Single-file version suitable for coursework; compile with `javac LibrarySystem.java`
 * and run with `java LibrarySystem`.
 */
public class LibrarySystem {

    // ---------- Domain model ----------
    static class Book {
        private final int id;
        private final String title;
        private final String author;
        private boolean issued;
        private String issuedTo; // null when the book is on the shelf

        Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.issued = false;
            this.issuedTo = null;
        }

        int getId()          { return id; }
        String getTitle()    { return title; }
        boolean isIssued()   { return issued; }

        void issueTo(String memberName) {
            this.issued = true;
            this.issuedTo = memberName;
        }

        void returned() {
            this.issued = false;
            this.issuedTo = null;
        }

        @Override
        public String toString() {
            String status = issued ? "ISSUED to " + issuedTo : "AVAILABLE";
            return String.format("ID: %-4d | %-30s | %-20s | %s",
                    id, title, author, status);
        }
    }

    // ---------- Library operations ----------
    static class Library {
        private final List<Book> books = new ArrayList<>();
        private int nextId = 1; // auto-incrementing ID so users can't create duplicates

        Book addBook(String title, String author) {
            Book book = new Book(nextId++, title, author);
            books.add(book);
            return book;
        }

        Optional<Book> findById(int id) {
            return books.stream()
                        .filter(b -> b.getId() == id)
                        .findFirst();
        }

        /** Returns true if the issue succeeded, false if book not found or already issued. */
        boolean issueBook(int id, String memberName) {
            Optional<Book> match = findById(id);
            if (match.isEmpty() || match.get().isIssued()) {
                return false;
            }
            match.get().issueTo(memberName);
            return true;
        }

        /** Returns true if the return succeeded, false if book not found or not issued. */
        boolean returnBook(int id) {
            Optional<Book> match = findById(id);
            if (match.isEmpty() || !match.get().isIssued()) {
                return false;
            }
            match.get().returned();
            return true;
        }

        void displayAll() {
            if (books.isEmpty()) {
                System.out.println("No books in the library yet.");
                return;
            }
            System.out.println("-".repeat(80));
            books.forEach(System.out::println);
            System.out.println("-".repeat(80));
        }
    }

    // ---------- Console UI ----------
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY BOOK SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine().trim();

                    if (title.isEmpty() || author.isEmpty()) {
                        System.out.println("Title and author cannot be empty.");
                        break;
                    }
                    Book added = library.addBook(title, author);
                    System.out.println("Book added successfully with ID " + added.getId());
                }
                case 2 -> {
                    System.out.print("Enter book ID to issue: ");
                    int issueId = readInt(scanner);
                    System.out.print("Enter member name: ");
                    String member = scanner.nextLine().trim();

                    if (member.isEmpty()) {
                        System.out.println("Member name cannot be empty.");
                        break;
                    }
                    if (library.issueBook(issueId, member)) {
                        System.out.println("Book ID " + issueId + " issued to " + member + ".");
                    } else {
                        System.out.println("Cannot issue: book not found or already issued.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter book ID to return: ");
                    int returnId = readInt(scanner);
                    if (library.returnBook(returnId)) {
                        System.out.println("Book ID " + returnId + " returned.");
                    } else {
                        System.out.println("Cannot return: book not found or was not issued.");
                    }
                }
                case 4 -> library.displayAll();
                case 5 -> {
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Enter 1-5.");
            }
        }
    }

    /** Reads an int safely; re-prompts on bad input instead of crashing with InputMismatchException. */
    private static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}