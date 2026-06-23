import java.util.*;

public class TicTacToeAI {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static void printBoard() {
        System.out.println(" 0  1  2");
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
        System.out.println(" 0  1  2");
    }

    static boolean isMovesLeft() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    static int evaluate() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'O') return +10;
                else if (board[row][0] == 'X') return -10;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'O') return +10;
                else if (board[0][col] == 'X') return -10;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'O') return +10;
            else if (board[0][0] == 'X') return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'O') return +10;
            else if (board[0][2] == 'X') return -10;
        }
        return 0;
    }

    static int minimax(int depth, boolean isMax) {
        int score = evaluate();

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!isMovesLeft()) return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    static int[] findBestMove() {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveVal = minimax(0, false);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are X, AI is O");
        printBoard();

        while (true) {
            //player
            System.out.print("Enter your move (row and col: 0-2): ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (board[row][col] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }
            board[row][col] = 'X';
            printBoard();

            if (evaluate() == -10) {
                System.out.println("You win!");
                break;
            }
            if (!isMovesLeft()) {
                System.out.println("It's a draw!");
                break;
            }

            // AI 
            int[] bestMove = findBestMove();
            board[bestMove[0]][bestMove[1]] = 'O';
            System.out.println("AI played at (" + bestMove[0] + ", " + bestMove[1] + ")");
            printBoard();

            if (evaluate() == 10) {
                System.out.println("AI wins!");
                break;
            }
            if (!isMovesLeft()) {
                System.out.println("It's a draw!");
                break;
            }
        }
        sc.close();
    }
}
