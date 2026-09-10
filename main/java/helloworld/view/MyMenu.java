package helloworld.view;

import controller.MenuListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenu {
    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuHelp;
    private Menu menuSettings;

    private MenuItem menuItemExit;
    private MenuItem menuItemAbout;
    private MenuItem menuItemPlayer1Name;
    private MenuItem menuItemPlayer2Name;
    private MenuItem menuItemBallSpeed;
    private MenuItem menuItemRacketSize;
    private MenuItem menuItemGameLimit;
    private MenuItem menuItemSpeedIncrease;

    private MenuListener menuListener;

    public MyMenu(MenuListener menuListener) {
        this.menuListener = menuListener;

        // Create MenuBar
        menuBar = new MenuBar();

        // Create Menus
        menuFile = new Menu("File");
        menuSettings = new Menu("Settings");
        menuHelp = new Menu("Help");

        // Create File Menu Items
        menuItemExit = new MenuItem("Exit");

        // Create Settings Menu Items
        menuItemPlayer1Name = new MenuItem("Set Player 1 Name");
        menuItemPlayer2Name = new MenuItem("Set Player 2 Name");
        menuItemBallSpeed = new MenuItem("Set Ball Speed");
        menuItemRacketSize = new MenuItem("Set Racket Size");
        menuItemGameLimit = new MenuItem("Set Game Limit");
        menuItemSpeedIncrease = new MenuItem("Set Speed Increase Interval");

        // Create Help Menu Items
        menuItemAbout = new MenuItem("About");

        // Add items to File menu
        menuFile.getItems().add(menuItemExit);

        // Add items to Settings menu
        menuSettings.getItems().addAll(
                menuItemPlayer1Name,
                menuItemPlayer2Name,
                menuItemBallSpeed,
                menuItemRacketSize,
                menuItemGameLimit,
                menuItemSpeedIncrease
        );

        // Add items to Help menu
        menuHelp.getItems().add(menuItemAbout);

        // Add menus to MenuBar
        menuBar.getMenus().addAll(menuFile, menuSettings, menuHelp);

        // Set up event handlers
        handleClicking();
    }

    private void handleClicking() {
        menuItemExit.setOnAction(e -> menuListener.setExit());
        menuItemAbout.setOnAction(e -> menuListener.setAbout());
        menuItemPlayer1Name.setOnAction(e -> menuListener.setPlayer1Name());
        menuItemPlayer2Name.setOnAction(e -> menuListener.setPlayer2Name());
        menuItemBallSpeed.setOnAction(e -> menuListener.setBallSpeed());
        menuItemRacketSize.setOnAction(e -> menuListener.setRacketSize());
        menuItemGameLimit.setOnAction(e -> menuListener.setGameLimit());
        menuItemSpeedIncrease.setOnAction(e -> menuListener.setSpeedIncrease());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
