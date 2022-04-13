package pika.ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import pika.Pika;
import pika.exception.PikaException;

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

    private Pika pika;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ash.png"));
    private Image pikaImage = new Image(this.getClass().getResourceAsStream("/images/pika.png"));

    /**
     * Initializes the mainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setStyle("-fx-background-color: #ffa500");
        //dialogContainer.setStyle("-fx-background-color: #add8e6");
        dialogContainer.setStyle("-fx-background-image: url('/images/pokeball2.png');"
                + "-fx-background-size: 194 184;");
        dialogContainer.getChildren().addAll(
                DialogBox.getPikaDialog("Hello from\n"
                        + "Pika Pi!\n"
                        + "(o^-^o)\n"
                        + "Hello! I'm PikaBot!\n"
                        + "What can Pika do for you?\n", pikaImage)
        );
    }

    public void setPika(Pika p) {
        pika = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) { //Closes the application if the bye command is inputted
            Platform.exit();
        }
        try {
            String response = pika.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getPikaDialog(response, pikaImage)
            );
        } catch (PikaException | IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(e.getMessage(), pikaImage));
        }
        userInput.clear();
    }
}
