package com.jmd.minesweeper;

import com.badlogic.gdx.graphics.Texture;

public class Cell {

    public State state;
    public boolean hasMine;
    public boolean hasFlag;

    static Texture coveredSquare = new Texture("coveredSquare.png");
    static Texture emptySquare = new Texture("emptySquare.png");
    static Texture mineSquare = new Texture("mineSquare.png");
    static Texture hitMineSquare = new Texture("hitMineSquare.png");
    static Texture flaggedSquare = new Texture("flaggedSquare.png");
    static Texture wrongFlaggedSquare = new Texture("wrongFlaggedSquare.png");

    static Texture has1Square = new Texture("1Square.png");
    static Texture has2Square = new Texture("2Square.png");
    static Texture has3Square = new Texture("3Square.png");
    static Texture has4Square = new Texture("4Square.png");
    static Texture has5Square = new Texture("5Square.png");

    private MineSweeper game;
    private final int col;
    private final int row;

    public Cell(int row, int col, State state, boolean hasMine, MineSweeper game) {
        this.row = row;
        this.col = col;
        this.state = state;
        this.hasMine = hasMine;
        this.game = game;
        this.hasFlag = false;
    }

    @Override
    public String toString() {
        return ("Cell state: " + this.state + " Has mine: " + this.hasMine);
    }

    public Texture getImage() {
        Texture texture = coveredSquare;

        if (state == State.COVERED) {
            if (hasFlag) {
                texture = flaggedSquare;
            } else {
                texture = coveredSquare;
            }
        } else {
            if (hasMine) {
                texture = hitMineSquare;
            } else {
                int surroundingMines = game.getNumberOfSurroundingMines(row, col);
                switch (surroundingMines) {
                    case 0:
                        texture = emptySquare;
                        break;
                    case 1:
                        texture = has1Square;
                        break;
                    case 2:
                        texture = has2Square;
                        break;
                    case 3:
                        texture = has3Square;
                        break;
                    case 4:
                        texture = has4Square;
                        break;
                    case 5:
                        texture = has5Square;
                        break;
                }
            }
        }
        if (game.lost) {
            if (state == State.COVERED) {
                texture = coveredSquare;
                if (hasFlag && hasMine) {
                    texture = flaggedSquare;
                }
                if (hasFlag && !hasMine) {
                    texture = wrongFlaggedSquare;
                }
                if (!hasFlag && hasMine) {
                    texture = mineSquare;
                }
            } else {
                if (hasMine) {
                    texture = hitMineSquare;
                }
            }

        }
        return texture;
    }

    public enum State {

        COVERED,
        REVEALED
    }

}
