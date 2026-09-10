package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Game;

public class KeyboardListener {
    private Game game;
    private double canvasHeight;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    public void setCanvasHeight(double height) {
        this.canvasHeight = height;
    }

    public void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();

        // Player 1 controls: W (up), S (down)
        if (code == KeyCode.W) {
            game.getPlayer1().getRacket().moveUp();
            game.getPlayer1().getRacket().constrainToBounds(0, canvasHeight);
        } else if (code == KeyCode.S) {
            game.getPlayer1().getRacket().moveDown();
            game.getPlayer1().getRacket().constrainToBounds(0, canvasHeight);
        }

        // Player 2 controls: UP arrow (up), DOWN arrow (down)
        else if (code == KeyCode.UP) {
            game.getPlayer2().getRacket().moveUp();
            game.getPlayer2().getRacket().constrainToBounds(0, canvasHeight);
        } else if (code == KeyCode.DOWN) {
            game.getPlayer2().getRacket().moveDown();
            game.getPlayer2().getRacket().constrainToBounds(0, canvasHeight);
        }
    }
}