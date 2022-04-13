package ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.Duke;
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

    private int closeTransition = 1000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs the GUI's Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.startUpMessage();
    }

    /**
     * Shows the greeting message from the app.
     */
    public void startUpMessage() {
        String welcomeMessage = new Ui().welcomeMessageGui();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Sets the GUI's Duke object.
     *
     * @param d duke object to be passed into MainWindow.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes.
     * One echos user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (userInput.getText().equals("bye")) {
            CompletableFuture.delayedExecutor(closeTransition,
                    TimeUnit.MILLISECONDS).execute(() -> Platform.exit());
        }
        userInput.clear();
    }
}
