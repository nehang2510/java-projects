import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Online Quiz System
 * Features: MCQ bank stored as Question objects, shuffled question order,
 * validated A-D answer input, per-question feedback, final score with
 * percentage and grade, review of wrong answers, replay option.
 */
public class OnlineQuizSystem {

    /** One MCQ: prompt, four options, index of the correct option (0-3). */
    static class Question {
        private final String prompt;
        private final String[] options;      // exactly 4 options
        private final int correctIndex;      // 0=A, 1=B, 2=C, 3=D

        Question(String prompt, String[] options, int correctIndex) {
            if (options.length != 4) {
                throw new IllegalArgumentException("Each question needs exactly 4 options.");
            }
            if (correctIndex < 0 || correctIndex > 3) {
                throw new IllegalArgumentException("correctIndex must be 0-3.");
            }
            this.prompt = prompt;
            this.options = options;
            this.correctIndex = correctIndex;
        }

        String getPrompt()          { return prompt; }
        String[] getOptions()       { return options; }
        int getCorrectIndex()       { return correctIndex; }
        String getCorrectAnswer()   { return (char) ('A' + correctIndex) + ". " + options[correctIndex]; }
        boolean isCorrect(int idx)  { return idx == correctIndex; }
    }

    /** Records one answered question for the end-of-quiz review. */
    static class Attempt {
        final Question question;
        final int chosenIndex;

        Attempt(Question question, int chosenIndex) {
            this.question = question;
            this.chosenIndex = chosenIndex;
        }

        boolean wasCorrect() { return question.isCorrect(chosenIndex); }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questionBank = buildQuestionBank();

        System.out.println("===== Online Quiz System =====");
        System.out.println("Answer each question with A, B, C, or D.\n");

        boolean playAgain = true;
        while (playAgain) {
            runQuiz(scanner, questionBank);

            System.out.print("\nTake the quiz again? (y/n): ");
            playAgain = scanner.next().trim().equalsIgnoreCase("y");
            System.out.println();
        }

        System.out.println("Thanks for playing. Goodbye!");
        scanner.close();
    }

    private static void runQuiz(Scanner scanner, List<Question> questionBank) {
        // Shuffle a copy so the original bank order is preserved across replays
        List<Question> quiz = new ArrayList<>(questionBank);
        Collections.shuffle(quiz);

        List<Attempt> attempts = new ArrayList<>();
        int score = 0;

        for (int i = 0; i < quiz.size(); i++) {
            Question q = quiz.get(i);

            System.out.printf("Q%d/%d: %s%n", i + 1, quiz.size(), q.getPrompt());
            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                System.out.printf("   %c. %s%n", (char) ('A' + j), opts[j]);
            }

            int chosen = readAnswer(scanner);
            attempts.add(new Attempt(q, chosen));

            if (q.isCorrect(chosen)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong. Correct answer: " + q.getCorrectAnswer() + "\n");
            }
        }

        printSummary(score, quiz.size(), attempts);
    }

    private static void printSummary(int score, int total, List<Attempt> attempts) {
        double percentage = (score * 100.0) / total;

        System.out.println("========== Quiz Summary ==========");
        System.out.printf("Score      : %d / %d%n", score, total);
        System.out.printf("Percentage : %.1f%%%n", percentage);
        System.out.println("Grade      : " + gradeFor(percentage));

        List<Attempt> wrong = new ArrayList<>();
        for (Attempt a : attempts) {
            if (!a.wasCorrect()) {
                wrong.add(a);
            }
        }

        if (wrong.isEmpty()) {
            System.out.println("Perfect score — nothing to review!");
        } else {
            System.out.println("\n--- Review: questions you missed ---");
            for (Attempt a : wrong) {
                System.out.println("Q: " + a.question.getPrompt());
                System.out.printf("   Your answer    : %c. %s%n",
                        (char) ('A' + a.chosenIndex),
                        a.question.getOptions()[a.chosenIndex]);
                System.out.println("   Correct answer : " + a.question.getCorrectAnswer());
            }
        }
        System.out.println("==================================");
    }

    private static String gradeFor(double percentage) {
        if (percentage >= 90) return "A (Excellent)";
        if (percentage >= 75) return "B (Good)";
        if (percentage >= 60) return "C (Average)";
        if (percentage >= 40) return "D (Pass)";
        return "F (Fail)";
    }

    /** Reads A/B/C/D (case-insensitive); re-prompts on anything else. Returns 0-3. */
    private static int readAnswer(Scanner scanner) {
        while (true) {
            System.out.print("Your answer (A-D): ");
            String input = scanner.next().trim().toUpperCase();

            if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
                return input.charAt(0) - 'A';
            }
            System.out.println("Invalid input. Enter A, B, C, or D.");
        }
    }

    /** The MCQ bank. Add questions here; nothing else needs to change. */
    private static List<Question> buildQuestionBank() {
        List<Question> bank = new ArrayList<>();

        bank.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"},
                1));

        bank.add(new Question(
                "What is the size of an int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on the OS"},
                1));

        bank.add(new Question(
                "Which collection class does NOT allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"},
                2));

        bank.add(new Question(
                "What is the default value of a boolean instance variable?",
                new String[]{"true", "false", "null", "0"},
                1));

        bank.add(new Question(
                "Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"},
                2));

        bank.add(new Question(
                "Which exception is thrown when dividing an int by zero?",
                new String[]{"NullPointerException", "ArithmeticException",
                             "NumberFormatException", "ClassCastException"},
                1));

        bank.add(new Question(
                "String objects in Java are:",
                new String[]{"Mutable", "Immutable", "Abstract", "Primitive"},
                1));

        bank.add(new Question(
                "Which access modifier gives the widest visibility?",
                new String[]{"private", "protected", "default", "public"},
                3));

        return bank;
    }
}