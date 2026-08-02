import java.util.Scanner;

class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        char value = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = value++;
            }
        }
    }

    public void displayBoard() {
        System.out.println();
        System.out.println(" " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
        System.out.println("---|---|---");
        System.out.println(" " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
        System.out.println("---|---|---");
        System.out.println(" " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
        System.out.println();
    }

    public boolean placeMark(int position, char mark) {
        if (position < 1 || position > 9) {
            return false;
        }

        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (grid[row][col] == 'X' || grid[row][col] == 'O') {
            return false;
        }

        grid[row][col] = mark;
        return true;
    }

    public boolean checkWin(char mark) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark) {
                return true;
            }
        }

        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) {
            return true;
        }

        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != 'X' && grid[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}

class Player {
    private String name;
    private char mark;

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public char getMark() {
        return mark;
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== TIC TAC TOE (Console Based) =====");

        System.out.print("Enter name of Player 1 (X): ");
        String player1Name = sc.nextLine();

        System.out.print("Enter name of Player 2 (O): ");
        String player2Name = sc.nextLine();

        Player p1 = new Player(player1Name, 'X');
        Player p2 = new Player(player2Name, 'O');
        Board board = new Board();

        Player currentPlayer = p1;

        while (true) {
            board.displayBoard();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getMark() + "), enter position (1-9): ");
            int position;

            if (sc.hasNextInt()) {
                position = sc.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                sc.next();
                continue;
            }

            boolean placed = board.placeMark(position, currentPlayer.getMark());

            if (!placed) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (board.checkWin(currentPlayer.getMark())) {
                board.displayBoard();
                System.out.println("Congratulations! " + currentPlayer.getName() + " wins the game!");
                break;
            }

            if (board.isFull()) {
                board.displayBoard();
                System.out.println("The game is a draw!");
                break;
            }

            if (currentPlayer == p1) {
                currentPlayer = p2;
            } else {
                currentPlayer = p1;
            }
        }

        sc.close();
    }
}