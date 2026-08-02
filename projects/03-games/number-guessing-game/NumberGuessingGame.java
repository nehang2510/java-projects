import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game
 * Computer picks a random number in [1, 100]; player guesses with
 * higher/lower hints, limited attempts, input validation, and replay option.
 */
public class NumberGuessingGame {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 7;   // ceil(log2(100)) = 7, binary search always wins

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("===== Number Guessing Game =====");
        System.out.printf("I'm thinking of a number between %d and %d.%n", MIN, MAX);
        System.out.printf("You have %d attempts. Good luck!%n", MAX_ATTEMPTS);

        boolean playAgain = true;
        int gamesPlayed = 0;
        int gamesWon = 0;

        while (playAgain) {
            gamesPlayed++;
            boolean won = playOneRound(scanner, random);
            if (won) {
                gamesWon++;
            }

            System.out.printf("Score: %d won / %d played%n", gamesWon, gamesPlayed);
            System.out.print("Play again? (y/n): ");
            String answer = scanner.next().trim().toLowerCase();
            playAgain = answer.equals("y");
        }

        System.out.println("Thanks for playing. Goodbye!");
        scanner.close();
    }

    /** Plays one full round. Returns true if the player guessed the number. */
    private static boolean playOneRound(Scanner scanner, Random random) {
        int secret = random.nextInt(MIN, MAX + 1);  // Java 17+ bounded nextInt
        int low = MIN;   // running bounds shown to the player as a hint
        int high = MAX;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            int remaining = MAX_ATTEMPTS - attempt + 1;
            String prompt = String.format("[Attempt %d/%d, range %d-%d] Your guess: ",
                    attempt, MAX_ATTEMPTS, low, high);
            int guess = readIntInRange(scanner, prompt, MIN, MAX);

            if (guess == secret) {
                System.out.printf("Correct! You got it in %d attempt%s.%n",
                        attempt, attempt == 1 ? "" : "s");
                return true;
            }

            if (guess < secret) {
                System.out.println("Too low.");
                low = Math.max(low, guess + 1);
            } else {
                System.out.println("Too high.");
                high = Math.min(high, guess - 1);
            }

            if (remaining - 1 > 0) {
                System.out.printf("Attempts remaining: %d%n", remaining - 1);
            }
        }

        System.out.printf("Out of attempts! The number was %d.%n", secret);
        return false;
    }

    /**
     * Reads an int within [min, max]; re-prompts on non-numeric input
     * or out-of-range values instead of crashing.
     */
    private static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a whole number.");
                scanner.next(); // discard bad token
                continue;
            }
            int value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.printf("Out of range. Enter a number between %d and %d.%n", min, max);
                continue;
            }
            return value;
        }
    }
}