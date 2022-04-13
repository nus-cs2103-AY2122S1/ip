package shybot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shybot.exception.ShyBotException;


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

    private ShyBot shybot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image shybotImage = new Image(this.getClass().getResourceAsStream("/images/DaShyBot.png"));
    private Image shybotErrorImage = new Image(this.getClass().getResourceAsStream("/images/DaShyBotError.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setShyBot(ShyBot d) {
        this.shybot = d;
        // get status response about data loading
        String response = shybot.getResponse();
        this.showShyBotDialog(response);
        this.showWelcomeDialog();
    }

    private void showShyBotDialog(String shybotResponse) {
        dialogContainer.getChildren().add(DialogBox.getShyBotDialog(shybotResponse, shybotImage));
    }

    private void showShyBotErrorDialog(String shybotResponse) {
        dialogContainer.getChildren().add(DialogBox.getShyBotDialog(shybotResponse, shybotErrorImage));
    }

    private void showUserDialog(String userInput) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userInput, userImage));
    }

    private void showWelcomeDialog() {
        String response = shybot.getWelcomeResponse();
        this.showShyBotDialog(response);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ShyBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = shybot.getResponse(input);
            this.showUserDialog(input);
            this.showShyBotDialog(response);
        } catch (ShyBotException e) {
            this.showUserDialog(input);
            this.showShyBotErrorDialog(e.getMessage());
        }
        userInput.clear();
    }
}
