package duke.controller;

import duke.Duke;
import duke.main.Ui;
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

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser" +
            ".png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke" +
            ".jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        Map<String, Consumer<String>> uiCommands =
                Map.of("showDukeResponse", this::showDukeResponse);
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

    public void showDukeResponse(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage));
    }

}