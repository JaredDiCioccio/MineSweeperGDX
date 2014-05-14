package com.jmd.minesweeper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.jmd.minesweeper.utilities.Utilities.randInt;

public class MineSweeper {

    public Difficulty difficulty;
    public Cell[][] board;
    public int flagsPlaced;
    public boolean running = true;
    boolean lost;

    public MineSweeper(Difficulty difficulty) {
        this.difficulty = difficulty;
        flagsPlaced = 0;
        this.lost = false;
        this.running = true;

        System.out.println("Initializing Board");
        initializeBoard();
    }

    /**
     *
     * @param row
     * @param col
     * @return TRUE if a mine is hit
     */
    public boolean clickCell(int row, int col) {
        Cell cell = this.board[row][col];
        cell.state = Cell.State.REVEALED;
        return cell.hasMine;
    }

    public void initializeBoard() {
        board = new Cell[difficulty.rows][difficulty.columns];
        for (int row = 0; row < difficulty.rows; row++) {
            for (int col = 0; col < difficulty.columns; col++) {
                board[row][col] = new Cell(row, col, Cell.State.COVERED, false, this);
            }
        }
        int minesPlaced = 0;
        while (minesPlaced < difficulty.numOfMines) {
            minesPlaced += placeMine();
        }
    }

    public void revealBoard() {
        for (int row = 0; row < difficulty.rows; row++) {
            for (int col = 0; col < difficulty.columns; col++) {
                board[row][col].state = Cell.State.REVEALED;
            }
        }
    }

    private int placeMine() {
        int row = randInt(0, difficulty.rows - 1);
        int col = randInt(0, difficulty.columns - 1);
        System.out.println("Row: " + Integer.toString(row));
        System.out.println("Col: " + Integer.toString(col));
        if (!board[row][col].hasMine) {
            board[row][col].hasMine = true;
            System.out.println("Placing mine");
            return 1;
        } else {
            return 0;
        }

    }

    public int getNumberOfSurroundingMines(int row, int col) {
        int mines = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j == difficulty.columns || i < 0 || i == difficulty.rows) {
                    // do nothing
                } else {
                    if (j == col && i == row) {
                        // do nothing
                    } else {
                        if (board[i][j].hasMine) {
                            mines++;
                        }
                    }

                }
            }
        }
        return mines;
    }

    public boolean gameWon() {
        boolean won = true;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = board[row][col];
                won = won && (cell.hasFlag && cell.hasMine);
            }
        }
        return won;
    }

    public void render(SpriteBatch batch) {

        batch.begin();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = board[row][col];
                batch.draw(cell.getImage(), col * 32, row * 32);
            }
        }
        batch.end();
    }

    void toggleFlag(int row, int col) {
        Cell cell = board[row][col];
        if (cell.state == Cell.State.COVERED) {
            cell.hasFlag = !cell.hasFlag;
        }
    }

}
