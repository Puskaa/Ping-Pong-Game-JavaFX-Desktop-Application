package helloworld;

import controller.BallThread;
import controller.Controller;
import controller.KeyboardListener;
import controller.MenuListener;
import helloworld.view.GameCanvas;
import helloworld.view.MyMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class pingpong extends Application {

    private Controller controller;
    private MenuListener menuListener;
    private KeyboardListener keyboardListener;
    private BallThread ballThread;
    private MyMenu gameMenu;
    private GameCanvas gameCanvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Super Ping Pong");

        // Create MVC components
        controller = new Controller();
        menuListener = new MenuListener(controller.getGame());
        keyboardListener = new KeyboardListener(controller.getGame());
        ballThread = new BallThread(controller.getGame());
        gameMenu = new MyMenu(menuListener);

        // Create canvas
        gameCanvas = new GameCanvas(controller.getGame(), 800, 600);

        // Connect canvas to ball thread for message display
        gameCanvas.setBallThread(ballThread);

        // Create layout
        BorderPane root = new BorderPane();
        root.setTop(gameMenu.getMenuBar());
        root.setCenter(gameCanvas);

        // Bind canvas size to scene size
        gameCanvas.widthProperty().bind(root.widthProperty());
        gameCanvas.heightProperty().bind(
                root.heightProperty().subtract(gameMenu.getMenuBar().getHeight())
        );

        // Update ball thread with canvas size
        gameCanvas.widthProperty().addListener((obs, old, newVal) ->
                ballThread.setCanvasSize(newVal.doubleValue(), gameCanvas.getHeight())
        );
        gameCanvas.heightProperty().addListener((obs, old, newVal) ->
                ballThread.setCanvasSize(gameCanvas.getWidth(), newVal.doubleValue())
        );

        // Set initial canvas size for ball thread
        ballThread.setCanvasSize(800, 600);

        // Create scene
        Scene scene = new Scene(root, 800, 600);

        // Add keyboard listener
        scene.setOnKeyPressed(event -> {
            // Spacebar starts the ball
            if (event.getCode() == KeyCode.SPACE) {
                if (!ballThread.isBallMoving()) {
                    ballThread.startBall();
                }
            } else {
                // Other keys control rackets
                keyboardListener.setCanvasHeight(gameCanvas.getHeight());
                keyboardListener.handleKeyPressed(event);
            }
        });

        // Animation loop to continuously redraw
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameCanvas.draw();
            }
        };
        timer.start();

        // Start ball thread
        ballThread.start();

        primaryStage.setScene(scene);
        primaryStage.show();

        // Request focus so keyboard works immediately
        scene.getRoot().requestFocus();

        // Stop ball thread when window closes
        primaryStage.setOnCloseRequest(e -> ballThread.stopRunning());
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    public void setGameCanvas(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }
}