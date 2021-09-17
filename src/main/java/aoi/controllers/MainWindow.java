package aoi.controllers;

import java.util.Timer;
import java.util.TimerTask;

import aoi.Aoi;
import aoi.ui.Ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Aoi aoi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/itadori.jpeg"));
    private final Image aoiImage = new Image(this.getClass().getResourceAsStream("/images/todo3.jpeg"));

    /** Initialise main components */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getAoiDialog(Ui.showGreeting(), aoiImage));
    }

    public void setAoi(Aoi a) {
        aoi = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing aoi.Aoi's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Does nothing if input is empty.
        if (input.trim().isEmpty()) {
            System.out.println("hi");
            return;
        }

        String response = aoi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAoiDialog(response, aoiImage)
        );
        userInput.clear();

        if (response.equals(Ui.showFarewellMsg())) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 1500);
        }
    }
}
