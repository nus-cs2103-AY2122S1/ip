package duke;

import java.io.InputStream;
import java.util.function.Consumer;

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

    private static final Image USER_IMG = getImage("/images/shrek.png");
    private static final Image DUKE_IMG = getImage("/images/lord-farquaad.png");

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Consumer<String> inputHandler;

    private static Image getImage(String fileName) {
        InputStream inputStream = MainWindow.class.getResourceAsStream(fileName);
        assert(inputStream != null);

        return new Image(inputStream);
    }

    /**
     * Initialise the JavaFX window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Type your message here...");
    }

    /**
     * Set the callback to handle input.
     *
     * @param handler input handler callback
     */
    public void setInputHandler(Consumer<String> handler) {
        this.inputHandler = handler;
    }

    /**
     * Get Duke to send a given text.
     * @param text Given text
     */
    public void print(String text) {
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, DUKE_IMG)
        );
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        if (userText.equals("")) {
            return;
        }

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, USER_IMG)
        );

        inputHandler.accept(userText);
        userInput.clear();
    }
}
