package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Consumer<String> inputHandler ;

    private final Image USER_IMG = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image DUKE_IMG = new Image(this.getClass().getResourceAsStream("/images/lord-farquaad.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setInputHandler(Consumer<String> handler) {
        this.inputHandler = handler;
    }

    public void print(String text) {
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, DUKE_IMG)
        );
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, USER_IMG)
        );

        inputHandler.accept(userText);
        userInput.clear();
    }
}