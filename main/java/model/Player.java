package model;

public class Player {
    private String name;
    private int score;
    private Racket racket;

    public Player() {
        this.racket = new Racket();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Racket getRacket() { return racket; }
    public void setRacket(Racket racket) { this.racket = racket; }
}