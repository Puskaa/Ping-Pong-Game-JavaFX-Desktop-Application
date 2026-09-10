package helloworld.view;

import controller.BallThread;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Game;

public class GameCanvas extends Canvas {
    private Game game;
    private BallThread ballThread;

    public GameCanvas(Game game, double width, double height) {
        super(width, height);
        this.game = game;

        // Initialize positions
        initializePositions();

        draw();
        widthProperty().addListener(evt -> {
            initializePositions();
            draw();
        });
        heightProperty().addListener(evt -> {
            initializePositions();
            draw();
        });
    }

    public void setBallThread(BallThread ballThread) {
        this.ballThread = ballThread;
    }

    private void initializePositions() {
        double w = getWidth();
        double h = getHeight();
        double margin = 10;
        double rh = game.getRacketHeight();
        double rw = game.getRacketWidth();

        // Set racket positions
        game.getPlayer1().getRacket().setX(margin);
        game.getPlayer1().getRacket().setY((h / 2) - (rh / 2));

        game.getPlayer2().getRacket().setX(w - rw - margin);
        game.getPlayer2().getRacket().setY((h / 2) - (rh / 2));

        // Set ball position and initial velocity
        game.getBall().reset(w / 2, h / 2);
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        double w = getWidth();
        double h = getHeight();

        // Background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, w, h);

        // Center line
        gc.setStroke(Color.WHITE);
        gc.setLineDashes(10, 10);
        gc.strokeLine(w / 2, 0, w / 2, h);
        gc.setLineDashes(null);

        // Player 1 racket
        gc.setFill(Color.WHITE);
        gc.fillRect(
                game.getPlayer1().getRacket().getX(),
                game.getPlayer1().getRacket().getY(),
                game.getPlayer1().getRacket().getWidth(),
                game.getPlayer1().getRacket().getHeight()
        );

        // Player 2 racket
        gc.fillRect(
                game.getPlayer2().getRacket().getX(),
                game.getPlayer2().getRacket().getY(),
                game.getPlayer2().getRacket().getWidth(),
                game.getPlayer2().getRacket().getHeight()
        );

        // Ball
        gc.setFill(Color.WHITE);
        double ballR = game.getBall().getRadius();
        gc.fillOval(
                game.getBall().getX() - ballR,
                game.getBall().getY() - ballR,
                ballR * 2,
                ballR * 2
        );

        // Scores
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Arial", 40));
        gc.fillText(String.valueOf(game.getPlayer1Score()), w / 4, 50);
        gc.fillText(String.valueOf(game.getPlayer2Score()), 3 * w / 4, 50);

        // Names
        gc.setFont(Font.font("Arial", 20));
        gc.fillText(game.getPlayer1Name(), w / 4, 80);
        gc.fillText(game.getPlayer2Name(), 3 * w / 4, 80);

        // Goal message (if any)
        if (ballThread != null) {
            String message = ballThread.getGoalMessage();
            if (!message.isEmpty()) {
                gc.setFill(Color.rgb(255, 215, 0, 0.9));  // Gold with transparency
                gc.setFont(Font.font("Arial", 60));
                gc.fillText(message, w / 2, h / 2);
            }
        }
    }
}