package TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "_";
            }
        }

        printBoard(board);
        boolean xTurn = true;

        while (true) {

            int row, col;
            boolean validMove = false;

            while (!validMove) {
                System.out.print("Enter the coordinates: ");
                try {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row < 1 || row > 3 || col < 1 || col > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (!board[row - 1][col - 1].equals("_")) {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }

                    board[row - 1][col - 1] = xTurn ? "X" : "O";
                    validMove = true;

                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            }

            printBoard(board);

            if (checkWin(board, xTurn ? "X" : "O")) {
                System.out.println((xTurn ? "X" : "O") + " wins");
                break;
            } else if (isDraw(board)) {
                System.out.println("Draw");
                break;
            }

            xTurn = !xTurn;
        }
    }

    public static void printBoard(String[][] board) {
        System.out.println("---------");
        for (String[] row : board) {
            System.out.print("| ");
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static boolean checkWin(String[][] board, String player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) ||
                    (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))) {
                return true;
            }
        }
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    public static boolean isDraw(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }
}
