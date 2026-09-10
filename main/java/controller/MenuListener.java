package controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Game;

import java.util.Optional;

public class MenuListener {
    private Game game;

    public MenuListener(Game game) {
        this.game = game;
    }

    public void setExit() {
        Platform.exit();
    }

    public void setAbout() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Super Ping Pong");
        alert.setHeaderText("Made in Cork");
        alert.setContentText("All rights reserved");
        alert.showAndWait();
    }

    public void setPlayer1Name() {
        TextInputDialog dialog = new TextInputDialog(game.getPlayer1Name());
        dialog.setTitle("Player 1 Name");
        dialog.setContentText("Enter name:");
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) game.setPlayer1Name(name);
        });
    }

    public void setPlayer2Name() {
        TextInputDialog dialog = new TextInputDialog(game.getPlayer2Name());
        dialog.setTitle("Player 2 Name");
        dialog.setContentText("Enter name:");
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) game.setPlayer2Name(name);
        });
    }

    public void setBallSpeed() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(game.getBallSpeed()));
        dialog.setTitle("Ball Speed");
        dialog.setContentText("Enter speed (1-20):");
        dialog.showAndWait().ifPresent(val -> {
            try {
                int speed = Integer.parseInt(val);
                if (speed > 0 && speed <= 20) game.setBallSpeed(speed);
                else showError("Speed must be between 1 and 20");
            } catch (NumberFormatException e) { showError("Enter a valid number"); }
        });
    }

    public void setRacketSize() {
        // Create sliders
        Slider widthSlider = new Slider(10, 100, game.getRacketWidth());
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSlider.setMajorTickUnit(10);
        widthSlider.setBlockIncrement(5);

        Slider heightSlider = new Slider(50, 300, game.getRacketHeight());
        heightSlider.setShowTickLabels(true);
        heightSlider.setShowTickMarks(true);
        heightSlider.setMajorTickUnit(50);
        heightSlider.setBlockIncrement(10);

        // Labels that update with slider values
        Label widthLabel = new Label();
        widthLabel.textProperty().bind(
                widthSlider.valueProperty().asString("Width: %.0f")
        );

        Label heightLabel = new Label();
        heightLabel.textProperty().bind(
                heightSlider.valueProperty().asString("Height: %.0f")
        );

        // Create layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(
                new Label("Adjust racket dimensions:"),
                widthLabel,
                widthSlider,
                heightLabel,
                heightSlider
        );

        // Create dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Racket Size");
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Show and apply if OK pressed
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            game.setRacketWidth((int) widthSlider.getValue());
            game.setRacketHeight((int) heightSlider.getValue());
        }
    }

    public void setGameLimit() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(game.getTarget()));
        dialog.setTitle("Game Limit");
        dialog.setContentText("Enter winning score:");
        dialog.showAndWait().ifPresent(val -> {
            try {
                int t = Integer.parseInt(val);
                if (t > 0) game.setTarget(t);
                else showError("Must be greater than 0");
            } catch (NumberFormatException e) { showError("Enter a valid number"); }
        });
    }

    public void setSpeedIncrease() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(game.getSpeedIncreaseInterval()));
        dialog.setTitle("Speed Increase Interval");
        dialog.setContentText("Increase speed every N goals:");
        dialog.showAndWait().ifPresent(val -> {
            try {
                int n = Integer.parseInt(val);
                if (n > 0) game.setSpeedIncreaseInterval(n);
                else showError("Must be greater than 0");
            } catch (NumberFormatException e) { showError("Enter a valid number"); }
        });
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}