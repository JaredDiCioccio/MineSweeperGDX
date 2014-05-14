package com.jmd.minesweeper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class MineSweeperGDX extends ApplicationAdapter {

    SpriteBatch batch;

    private Texture coveredSquare;
    private Texture emptySquare;
    private int width;
    private int height;
    MineSweeper game;
    private boolean lost;

    @Override
    public void create() {
        batch = new SpriteBatch();
        game = new MineSweeper(Difficulty.EASY);
        width = Gdx.graphics.getWidth();

        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
        height = Gdx.graphics.getHeight();
        System.out.println("Width: " + width + " Height: " + height);

        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean keyDown(int i) {
                return true;
            }

            @Override
            public boolean keyUp(int i) {
                System.out.println("keyUp i: " + i);
                return true;
            }

            @Override
            public boolean keyTyped(char c) {
                return true;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int buttonPressed) {

                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int buttonPressed) {
                System.out.println("Touchup : [i1,i2,i3,i4] " + "[" + x + "," + y + "," + buttonPressed + "]");
                System.out.println(Buttons.LEFT + " " + Buttons.RIGHT);

                if (!game.lost) {
                    if (buttonPressed == Buttons.LEFT) {
                        Vector2 position = new Vector2(x, height - y);
                        System.out.println("Left Click: " + position.toString());
                        int row = (int) position.y / 32;
                        int col = (int) position.x / 32;
                        System.out.println("Row " + row + " Col " + col);
                        //constrains row/col to valid numbers
                        if (row >= 0 && row < game.difficulty.rows && col >= 0 && col < game.difficulty.columns) {
                            if (!game.board[row][col].hasFlag) {
                                game.lost = game.clickCell(row, col);
                            }
                        }
                    }
                    if (buttonPressed == Buttons.RIGHT) {
                        Vector2 position = new Vector2(x, height - y);
                        System.out.println("Right Click: " + position.toString());
                        int row = (int) position.y / 32;
                        int col = (int) position.x / 32;
                        System.out.println("Row " + row + " Col " + col);
                        //constrains row/col to valid numbers
                        long firstClick = TimeUtils.millis();
                        if (row >= 0 && row < game.difficulty.rows && col >= 0 && col < game.difficulty.columns) {
                            if (TimeUtils.timeSinceMillis(firstClick) < 1000) {
                                game.toggleFlag(row, col);
                            }

                        }
                    }
                }
                Gdx.graphics.requestRendering();
                return true;
            }

            @Override
            public boolean touchDragged(int i, int i1, int i2) {
                return true;
            }

            @Override
            public boolean mouseMoved(int i, int i1) {
                return true;
            }

            @Override
            public boolean scrolled(int i) {
                return true;
            }
        });

    }

    @Override

    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.render(batch);

    }

}
