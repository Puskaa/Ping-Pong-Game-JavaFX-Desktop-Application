package model;

public class Game {
    private int target;
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Ball ball = new Ball();

    public Game() {
        player1.setName("Player 1");
        player2.setName("Player 2");
        player1.getRacket().setWidth(30);
        player1.getRacket().setHeight(100);
        player2.getRacket().setWidth(30);
        player2.getRacket().setHeight(100);
        ball.setSpeed(5);
        ball.setAccelFreq(5);
    }

    // Target
    public int getTarget() { return target; }
    public void setTarget(int target) { this.target = target; }

    // Players
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }

    // Ball
    public Ball getBall() { return ball; }

    // Player names (delegates to Player)
    public String getPlayer1Name() { return player1.getName(); }
    public void setPlayer1Name(String name) { player1.setName(name); }
    public String getPlayer2Name() { return player2.getName(); }
    public void setPlayer2Name(String name) { player2.setName(name); }

    // Scores (delegates to Player)
    public int getPlayer1Score() { return player1.getScore(); }
    public int getPlayer2Score() { return player2.getScore(); }
    public void resetScores() {
        player1.setScore(0);
        player2.setScore(0);
    }

    // Ball speed (delegates to Ball)
    public int getBallSpeed() { return ball.getSpeed(); }
    public void setBallSpeed(double speed) { ball.setSpeed((int) speed); }

    // Speed increase interval (delegates to Ball)
    public int getSpeedIncreaseInterval() { return ball.getAccelFreq(); }
    public void setSpeedIncreaseInterval(int interval) { ball.setAccelFreq(interval); }

    // Racket dimensions (both rackets stay in sync)
    public int getRacketWidth() { return player1.getRacket().getWidth(); }
    public void setRacketWidth(int width) {
        player1.getRacket().setWidth(width);
        player2.getRacket().setWidth(width);
    }

    public int getRacketHeight() { return player1.getRacket().getHeight(); }
    public void setRacketHeight(int height) {
        player1.getRacket().setHeight(height);
        player2.getRacket().setHeight(height);
    }
}