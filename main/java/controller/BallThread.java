package controller;

import javafx.application.Platform;
import model.Ball;
import model.Game;

public class BallThread extends Thread {
    private Game game;
    private Ball ball;
    private CollisionDetector collisionDetector;
    private double canvasWidth;
    private double canvasHeight;
    private volatile boolean running = true;
    private volatile boolean ballMoving = false;
    private volatile String goalMessage = "";
    private volatile long goalMessageTime = 0;

    public BallThread(Game game) {
        this.game = game;
        this.ball = game.getBall();
        this.collisionDetector = new CollisionDetector();
        setDaemon(true);
    }

    public void setCanvasSize(double width, double height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

    public void stopRunning() {
        running = false;
    }

    public void startBall() {
        ballMoving = true;
    }

    public void pauseBall() {
        ballMoving = false;
    }

    public boolean isBallMoving() {
        return ballMoving;
    }

    public String getGoalMessage() {
        // Clear message after 2 seconds
        if (System.currentTimeMillis() - goalMessageTime > 2000) {
            goalMessage = "";
        }
        return goalMessage;
    }

    @Override
    public void run() {
        while (running) {
            if (ballMoving) {
                // Move ball
                ball.move();

                // Check wall collisions (top/bottom)
                collisionDetector.checkWallCollision(ball, canvasHeight);

                // Check goal scoring (left edge = player 2 scores)
                if (collisionDetector.checkLeftGoal(ball)) {
                    handleGoal(2);
                }

                // Check goal scoring (right edge = player 1 scores)
                if (collisionDetector.checkRightGoal(ball, canvasWidth)) {
                    handleGoal(1);
                }

                // Check racket collisions
                collisionDetector.checkRacketCollision(ball, game.getPlayer1().getRacket());
                collisionDetector.checkRacketCollision(ball, game.getPlayer2().getRacket());
            }

            // Sleep for 60 FPS
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void handleGoal(int scoringPlayer) {
        // Pause ball
        pauseBall();

        // Update score on JavaFX thread
        Platform.runLater(() -> {
            if (scoringPlayer == 1) {
                game.getPlayer1().setScore(game.getPlayer1().getScore() + 1);
                goalMessage = "GOAL " + game.getPlayer1Name().toUpperCase() + "!";
            } else {
                game.getPlayer2().setScore(game.getPlayer2().getScore() + 1);
                goalMessage = "GOAL " + game.getPlayer2Name().toUpperCase() + "!";
            }
            goalMessageTime = System.currentTimeMillis();
        });

        // Wait 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        // Reset ball to center
        ball.reset(canvasWidth / 2, canvasHeight / 2);

        // Resume game
        startBall();
    }
}
