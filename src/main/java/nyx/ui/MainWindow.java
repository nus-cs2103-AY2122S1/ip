package nyx.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nyx.Nyx;
import nyx.NyxException;

/**
 * Controller for nyx.ui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String START_MESSAGE = "Hello, this is Nyx! What can I do for you?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML

    private Nyx nyx;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image nyxImage = new Image(this.getClass().getResourceAsStream("/images/hacker.png"));

    /**
     * Initializes Nyx chatbot object.
     * @param nyx Nyx chatbot object.
     */
    public void setNyx(Nyx nyx) {
        this.nyx = nyx;
    }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayStart();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nyx's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }

        DialogBox userBox = DialogBox.getUserDialog(input, userImage);

        try {
            String response = nyx.getResponse(input);

            dialogContainer.getChildren().addAll(
                    userBox,
                    DialogBox.getNyxDialog(response, nyxImage)
            );
        } catch (NyxException e) {
            dialogContainer.getChildren().addAll(
                    userBox,
                    DialogBox.getErrorDialog(e.getMessage(), nyxImage)
            );
        } finally {
            userInput.clear();
        }
    }

    @FXML
    private void displayStart() {
        dialogContainer.getChildren().add(DialogBox.getNyxDialog(START_MESSAGE, nyxImage));
    }
}
