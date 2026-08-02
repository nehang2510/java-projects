import java.util.Random;
import java.util.Scanner;

/**
 * Dice Game
 * Player vs Computer. Each round both sides roll one die (1-6);
 * higher roll wins the round, ties are replayed prompts but scored as draws.
 * First to win the majority of rounds (best-of-N) takes the match.
 * Tracks roll frequency to show the dice are fair.
 */
public class DiceGame {

    private static final int SIDES = 6;
    private static final int ROUNDS = 5;   // best of 5

    private static final Random random = new Random();

    // Frequency of each face across the whole session (index 0 unused)
    private static final int[] rollCounts = new int[SIDES + 1];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Dice Game: You vs Computer =====");
        System.out.printf("Best of %d rounds. Higher roll wins each round.%n", ROUNDS);

        boolean playAgain = true;
        while (playAgain) {
            playMatch(scanner);

            System.out.print("\nPlay another match? (y/n): ");
            playAgain = scanner.next().trim().equalsIgnoreCase("y");
        }

        printSessionStats();
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void playMatch(Scanner scanner) {
        int playerWins = 0;
        int computerWins = 0;
        int draws = 0;

        for (int round = 1; round <= ROUNDS; round++) {
            System.out.printf("%n--- Round %d of %d ---%n", round, ROUNDS);
            System.out.print("Press Enter to roll...");
            scanner.nextLine();          // consume pending newline
            // (first round: consumes leftover; later rounds: waits for Enter)

            int playerRoll = rollDie();
            int computerRoll = rollDie();

            System.out.println("You rolled      : " + diceFace(playerRoll) + "  (" + playerRoll + ")");
            System.out.println("Computer rolled : " + diceFace(computerRoll) + "  (" + computerRoll + ")");

            if (playerRoll > computerRoll) {
                playerWins++;
                System.out.println(">> You win this round!");
            } else if (computerRoll > playerRoll) {
                computerWins++;
                System.out.println(">> Computer wins this round.");
            } else {
                draws++;
                System.out.println(">> Draw.");
            }

            System.out.printf("Score: You %d - %d Computer (draws: %d)%n",
                    playerWins, computerWins, draws);

            // Early exit: match already decided mathematically
            int remaining = ROUNDS - round;
            if (playerWins > computerWins + remaining || computerWins > playerWins + remaining) {
                System.out.println("Match decided early — remaining rounds can't change the result.");
                break;
            }
        }

        System.out.println("\n========== Match Result ==========");
        if (playerWins > computerWins) {
            System.out.println("YOU WIN THE MATCH!");
        } else if (computerWins > playerWins) {
            System.out.println("Computer wins the match.");
        } else {
            System.out.println("The match is a draw.");
        }
        System.out.printf("Final: You %d - %d Computer (draws: %d)%n",
                playerWins, computerWins, draws);
    }

    /** Rolls one fair die and records the result for session statistics. */
    private static int rollDie() {
        int roll = random.nextInt(1, SIDES + 1);   // uniform over [1, 6]
        rollCounts[roll]++;
        return roll;
    }

    /** Unicode die faces for nicer output; falls back gracefully everywhere. */
    private static String diceFace(int value) {
        // U+2680 is ⚀ (die face-1); faces are consecutive code points
        return String.valueOf((char) (0x2680 + value - 1));
    }

    /** Shows roll distribution — with enough rolls, each face trends to ~16.7%. */
    private static void printSessionStats() {
        int total = 0;
        for (int i = 1; i <= SIDES; i++) total += rollCounts[i];
        if (total == 0) return;

        System.out.println("\n--- Session roll distribution (" + total + " rolls) ---");
        for (int face = 1; face <= SIDES; face++) {
            double pct = rollCounts[face] * 100.0 / total;
            System.out.printf("%d: %-4d (%5.1f%%) %s%n",
                    face, rollCounts[face], pct, "#".repeat(rollCounts[face]));
        }
        System.out.println("(Expected ~16.7% per face for a fair die.)");
    }
}