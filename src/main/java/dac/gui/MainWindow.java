package dac.gui;

import dac.DaC;
import dac.DaCResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Adapted from JavaFX tutorial 4: https://se-education.org/guides/tutorials/javaFxPart4.html
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

    @FXML
    private Label feedback;

    private DaC daC;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dog.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(DaC d) {
        daC = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * Updates the feedback node with the appropriate list of tasks.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        DaCResponse response = daC.getResponse(input);

        DialogBox dukeDialog = response.isErrorMessage()
                ? DialogBox.getErrorDialog(response.getResponse(), dukeImage)
                : DialogBox.getDukeDialog(response.getResponse(), dukeImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                dukeDialog
        );

        if (response.getListToPrint() == null) {
            new Thread(Main::exit).start();
        } else {
            feedback.setText(response.getListToPrint());
        }

        userInput.clear();
    }
}

