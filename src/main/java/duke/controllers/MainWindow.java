package duke.controllers;

import duke.main.Duke;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Encapsulates a MainWindow for Duke's GUI.
 * Provides the layout for the other controls.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class MainWindow extends AnchorPane {
    /** The scrollpane for the main window */
    @FXML
    private ScrollPane scrollPane;
    /** The container for the dialog */
    @FXML
    private VBox dialogContainer;
    /** The text field for user input */
    @FXML
    private TextField userInput;
    /** The button for user to send in input */
    @FXML
    private Button sendButton;
    /** The font to use for all texts in the GUI */
    private Font defaultFont;
    /** The instance of the Duke application */
    private Duke duke;
    /** The image of the user to be displayed in the GUI */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/loading_cat.png"));
    /** The image of Duke to be displayed in the GUI */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/thumbs_up_cat.png"));

    /**
     * Initialize the MainWindow.
     */
    @FXML
    public void initialize() {
        this.defaultFont = new Font("Arial", 15);
        sendButton.setFont(defaultFont);
        userInput.setFont(defaultFont);
        assert this.defaultFont.equals(new Font("Arial", 15))
                : "font must be set to prevent rubbish being printed";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.intro(), dukeImage)
        );
    }

    /**
     * Sets the instance of Duke for the main window to be tied to.
     *
     * @param d The instance of Duke to be tied to the main window.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.equals(null) : "user input should not be null";
        String response = duke.getResponse(input);
        assert !response.equals(null) : "system response should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
