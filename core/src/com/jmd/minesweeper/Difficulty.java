package com.jmd.minesweeper;

import java.awt.Dimension;

public class Difficulty {

    public static final Difficulty EASY = new Difficulty(10, 8, 8);
    public static final Difficulty MEDIUM = new Difficulty(40, 16, 16);
    public static final Difficulty HARD = new Difficulty(99, 30, 16);
    public static final Difficulty INSANE = new Difficulty(99, 16, 16);

    public int numOfMines;
    public int columns;
    public int rows;

    private Dimension dimension;

    public Difficulty(int numOfMines, int width, int height) {
        this.numOfMines = numOfMines;
        this.columns = width;
        this.rows = height;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Mines: ").append(Integer.toString(this.numOfMines)).append(System.getProperty("line.separator"));
        sb.append("Columns: ").append(Integer.toString(this.columns)).append(System.getProperty("line.separator"));
        sb.append("Rows: ").append(Integer.toString(this.rows)).append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
