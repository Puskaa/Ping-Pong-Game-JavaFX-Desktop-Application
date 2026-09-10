package controller;

import model.Game;

public class Controller {
    private Game game = new Game();

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}