package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    public int size;
    public PlayersType[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayersType[size][size];
    }

    public List<Map<Integer, Integer>> getFreeCells() {
        List<Map<Integer, Integer>> pairs = new ArrayList<Map<Integer, Integer>>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    Map<Integer, Integer> pair = Map.of(i, j);
                    pairs.add(pair);
                }
            }
        }
        return pairs;
    }

    public boolean addPlayer(int row, int col, PlayersType player) {
        if (board[row][col] != null) {
            return false;
        }
        board[row][col] = player;
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null)
                    System.out.print(board[i][j].type.name());
                else
                    System.out.print(" ");
                System.out.print(" |  ");
            }
            System.out.println();
        }
    }
}
