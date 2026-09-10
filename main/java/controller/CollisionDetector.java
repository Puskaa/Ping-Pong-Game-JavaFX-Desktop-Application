package controller;

import model.Ball;
import model.Racket;

public class CollisionDetector {

    /**
     * Check if ball collides with racket and handle bounce
     * @return true if collision detected
     */
    public boolean checkRacketCollision(Ball ball, Racket racket) {
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballR = ball.getRadius();

        double racketX = racket.getX();
        double racketY = racket.getY();
        double racketW = racket.getWidth();
        double racketH = racket.getHeight();

        // Check if ball overlaps with racket
        if (ballX + ballR > racketX &&
                ballX - ballR < racketX + racketW &&
                ballY + ballR > racketY &&
                ballY - ballR < racketY + racketH) {

            // Bounce ball horizontally
            ball.reverseX();

            // Add vertical spin based on where ball hits racket
            // Hit top = ball goes up, hit bottom = ball goes down
            double hitPos = (ballY - racketY) / racketH;  // 0 to 1
            double spinFactor = (hitPos - 0.5) * 2;  // -1 to 1
            ball.setDy(ball.getSpeed() * spinFactor);

            return true;
        }

        return false;
    }

    /**
     * Check if ball hits top or bottom wall
     * @return true if collision detected
     */
    public boolean checkWallCollision(Ball ball, double canvasHeight) {
        double ballY = ball.getY();
        double ballR = ball.getRadius();

        if (ballY - ballR <= 0 || ballY + ballR >= canvasHeight) {
            ball.reverseY();
            return true;
        }

        return false;
    }

    /**
     * Check if ball reached left edge (goal for player 2)
     */
    public boolean checkLeftGoal(Ball ball) {
        return ball.getX() - ball.getRadius() <= 0;
    }

    /**
     * Check if ball reached right edge (goal for player 1)
     */
    public boolean checkRightGoal(Ball ball) {
        return ball.getX() + ball.getRadius() >= 0;  // Will update this with actual width
    }

    /**
     * Check if ball reached right edge with canvas width
     */
    public boolean checkRightGoal(Ball ball, double canvasWidth) {
        return ball.getX() + ball.getRadius() >= canvasWidth;
    }
}