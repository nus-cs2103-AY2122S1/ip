package banana;

import javafx.application.Platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Handles user input and output to screen,
 * the scroller, the text field and the
 * send button.
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
    private Image user = new Image(this.getClass().getResourceAsStream(
            "/images/levi.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream(
            "/images/hange.png"));

    /**
     * Sets the color of the background
     * to be dark green, sets the scrollPane
     * features, initialises the Image
     * objects and prints the welcome label.
     */
    @FXML
    public void initialize() {
        dialogContainer.setBackground(new Background(new BackgroundFill(
                Color.DARKSEAGREEN, null, null)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        " Hello I'm Banana! \n "
                                + "How can I help you?", bot)
        );
        handleActions();
    }

    /**
     * Initialises the duke object.
     *
     * @param d the Duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input
     * after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
            return;
        }
        String response = duke.getResponse(input);
        Parser.setPrevInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + " ",
                        user),
                DialogBox.getDukeDialog(response, bot)
        );
        userInput.clear();
    }

    /**
     * Allows the send button and TextField
     * to handle user input.
     */
    public void handleActions() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}

