package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class UserPrompts {

    public void showExitMessage() {
        Stage exitWindow = new Stage();
        exitWindow.setTitle("See you next time!");
        exitWindow.setWidth(300);
        exitWindow.setHeight(100);
        exitWindow.setResizable(false);
        exitWindow.setAlwaysOnTop(true);
        Label exitLabel = new Label("Bye. Hope to see you again soon!");
        Button closeButton = new Button("Confirm");
        closeButton.setOnAction(event -> exitWindow.close());
        VBox otherBox = new VBox(10);
        otherBox.getChildren().addAll(exitLabel, closeButton);
        otherBox.setAlignment(Pos.CENTER);
        Scene exitScene = new Scene(otherBox);
        exitWindow.setScene(exitScene);
        exitWindow.showAndWait();
    }

    public void showDueTasks() {
        Stage exitWindow = new Stage();
        exitWindow.setTitle("There are task(s) due!");
        exitWindow.setWidth(300);
        exitWindow.setHeight(100);
        exitWindow.setResizable(false);
        exitWindow.setAlwaysOnTop(true);

        Label exitLabel = new Label("Bye. Hope to see you again soon!");

        Button snooze = new Button("Snooze");
        Button reSchedule = new Button("Re-Schedule");
        Button delete = new Button("Delete");
        snooze.setOnAction(event -> exitWindow.close());
        VBox otherBox = new VBox(10);
        otherBox.getChildren().addAll(exitLabel, snooze);
        otherBox.setAlignment(Pos.CENTER);
        Scene exitScene = new Scene(otherBox);
        exitWindow.setScene(exitScene);
        exitWindow.showAndWait();
    }

    public void showConfirmationMessage(String input) {

    }
}
