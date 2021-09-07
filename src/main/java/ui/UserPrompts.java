package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * UserPrompts Class implements specific pop-up windows
 * with relevant display messages to interact with the user.
 */
class UserPrompts {

    /**
     * Shows the exit message with a pop-up window.
     */
    public void showExitMessage() {
        Stage exitWindow = new Stage();
        exitWindow.setTitle("See you next time!");
        exitWindow.setWidth(300);
        exitWindow.setHeight(100);
        exitWindow.setResizable(false);
        exitWindow.setAlwaysOnTop(true);
        Label exitLabel = new Label(Ui.getExitMessage());
        Button closeButton = new Button("Confirm");
        closeButton.setOnAction(event -> exitWindow.close());
        VBox otherBox = new VBox(10);
        otherBox.getChildren().addAll(exitLabel, closeButton);
        otherBox.setAlignment(Pos.CENTER);
        Scene exitScene = new Scene(otherBox);
        exitWindow.setScene(exitScene);
        exitWindow.showAndWait();
    }
}
