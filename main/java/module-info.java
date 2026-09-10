module com.example.first {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens helloworld to javafx.fxml;
    opens helloworld.view to javafx.fxml;
    exports helloworld;
    exports model;
    exports controller;
    exports helloworld.view;
}