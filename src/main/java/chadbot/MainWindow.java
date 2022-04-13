package chadbot;

import chadbot.io.Ui;
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

    private Chad chad;
    private boolean isExit;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/chad.png"));

    /**
     * Initializes the scroll pane and adds a welcome message to the dialog container
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getChadDialog(Ui.showWelcome(), chadImage));
    }

    public void setChad(Chad d) {
        chad = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chad's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chad.getResponse(input);
        setChadOutput(input, response);
        userInput.clear();
    }

    private void setChadOutput(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChadDialog(response, chadImage)
        );
    }
}
