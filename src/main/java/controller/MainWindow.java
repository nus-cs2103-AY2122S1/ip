package controller;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Encapsulates a controller used to control the main window user interface.
 * Includes the dialog container, the user input, the send button and the scroll bar.
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private Image sendIconImage = new Image(this.getClass().getResourceAsStream("/images/send.png"));

    /**
     * Instantiates the controller to render the main chat window with scrollbar and user input.
     *
     * @param duke Duke to process inputs.
     */
    public MainWindow(Duke duke) {
        try {
            this.duke = duke;

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/MainWindow.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the main window after variables are set.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Write a message...");

        // Set icon for send button
        ImageView sendIcon = new ImageView(sendIconImage);
        sendIcon.setFitHeight(20);
        sendIcon.setFitWidth(20);
        sendButton.setGraphic(sendIcon);

        // Greet user
        String greetingText = this.duke.greet();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingText, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (!input.isEmpty()) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
