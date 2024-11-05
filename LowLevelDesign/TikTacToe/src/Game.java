import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Models.Board;
import Models.PlayerEnum;
import Models.PlayerO;
import Models.PlayerX;
import Models.Players;

public class Game {
    Board board;
    Deque<Players> players = new LinkedList<>();

    public Game() {
        initialize();
    }

    private void initialize() {
        board = new Board(3);
        players.add(new Players(new PlayerX(), "Player 1"));
        players.add(new Players(new PlayerO(), "Player 2"));
    }

    public void StartGrame() {
        boolean gameOver = false;
        while (!gameOver) {
            board.printBoard();
            List<Map<Integer, Integer>> pairs = board.getFreeCells();
            if (pairs.size() == 0) {
                System.out.println("Game Over.");
                gameOver = true;
                break;
            }
            Players playerTurn = players.removeFirst();
            // read the user input
            System.out.print("Player:" + playerTurn.Name + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int row1 = Integer.valueOf(values[0]);
            int col1 = Integer.valueOf(values[1]);

            boolean isPeiceAddedSucessfully = board.addPlayer(row1, col1, playerTurn.getPlayerType());
            if (!isPeiceAddedSucessfully) {
                System.out.println("Cell is already occupied. Try again.");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            if (isMatchWon(row1, col1, playerTurn.type.type)) {
                System.out.println(playerTurn.getName() + " Won!");
                board.printBoard();
                gameOver = true;
                break;
            }
        }
    }

    private boolean isMatchWon(int row, int col, PlayerEnum playerType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagMatch = true;
        boolean antiDiagMatch = true;

        // Check row
        for (int j = 0; j < board.size; j++) {
            if (board.board[row][j] == null || board.board[row][j].type != playerType) {
                rowMatch = false;
                break;
            }
        }

        // Check column
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][col] == null || board.board[i][col].type != playerType) {
                colMatch = false;
                break;
            }
        }

        // Check diagonal
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][i] == null || board.board[i][i].type != playerType) {
                diagMatch = false;
                break;
            }
        }

        // Check anti-diagonal
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][board.size - 1 - i] == null || board.board[i][board.size - 1 - i].type != playerType) {
                antiDiagMatch = false;
                break;
            }
        }
        return rowMatch || colMatch || diagMatch || antiDiagMatch;
    }
}
