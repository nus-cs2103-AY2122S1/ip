package duke.controller;

import duke.Duke;
import duke.main.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

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

    private Duke duke;
    private Ui ui;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser"
            + ".png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke"
            + ".png")));

    /**
     * Forces ScrollPane to automatically scroll down.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke to the given Duke object.
     *
     * @param d Duke to be set.
     */
    public void setDuke(Duke d) {
        Map<String, Consumer<String>> uiCommands =
                Map.of("showDukeResponse", this::showDukeResponse,
                        "exit", this::exit);
        d.setUi(new Ui(uiCommands));
        duke = d;
        d.start();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        showDukeResponse(response);
        userInput.clear();
    }

    /**
     * Shows the response on the interface.
     *
     * @param response String to be displayed.
     */
    public void showDukeResponse(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Closes the UI.
     *
     * @param message String response before closing the UI.
     */
    public void exit(String message) {
        sleep(1500);
        Platform.exit();
    }

    /**
     * Pauses execution for the given time(in milliseconds).
     *
     * @param milliseconds int period of pause.
     */
    public void sleep(int milliseconds) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}