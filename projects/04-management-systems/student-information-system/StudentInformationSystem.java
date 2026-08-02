import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Student Information System
 *
 * Menu-driven console program:
 *   1. Add student    (roll no, name, course, marks)
 *   2. Display all students
 *   3. Search by roll number
 *   4. Exit
 */
class Student {
    private final int rollNo;
    private final String name;
    private final String course;
    private final double marks;

    public Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }

    /** Grade is derived from marks, so it is computed, never stored. */
    public char getGrade() {
        if (marks >= 90) return 'A';
        if (marks >= 75) return 'B';
        if (marks >= 60) return 'C';
        if (marks >= 40) return 'D';
        return 'F';
    }

    @Override
    public String toString() {
        return String.format("%-8d %-20s %-15s %8.2f %6c",
                rollNo, name, course, marks, getGrade());
    }
}

public class StudentInformationSystem {

    private final List<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new StudentInformationSystem().run();
    }

    private void run() {
        while (true) {
            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search by Roll No");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> searchStudent();
                case 4 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Enter 1-4.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter roll no: ");
        int rollNo = readInt();

        // Enforce unique roll numbers — duplicates would make
        // search results ambiguous.
        if (findByRollNo(rollNo) != null) {
            System.out.println("Error: roll no " + rollNo + " already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: name cannot be empty.");
            return;
        }

        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();

        System.out.print("Enter marks (0-100): ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Error: marks must be between 0 and 100.");
            return;
        }

        students.add(new Student(rollNo, name, course, marks));
        System.out.println("Student added successfully.");
    }

    private void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n----------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %8s %6s%n",
                "RollNo", "Name", "Course", "Marks", "Grade");
        System.out.println("----------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Total students: " + students.size());
    }

    private void searchStudent() {
        System.out.print("Enter roll no to search: ");
        int rollNo = readInt();
        Student s = findByRollNo(rollNo);
        if (s == null) {
            System.out.println("No student found with roll no " + rollNo);
        } else {
            System.out.printf("%-8s %-20s %-15s %8s %6s%n",
                    "RollNo", "Name", "Course", "Marks", "Grade");
            System.out.println(s);
        }
    }

    private Student findByRollNo(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) return s;
        }
        return null;
    }

    /**
     * Reads an int safely. scanner.nextInt() throws on bad input and
     * leaves the token in the buffer; reading the whole line and
     * parsing it avoids both problems.
     */
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }
}