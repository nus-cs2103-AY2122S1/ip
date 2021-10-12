package Du;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

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

    private Du du;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image emptyImage = new Image(this.getClass().getResourceAsStream("/images/Empty.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDu(Du d) {
        du = d;
    }

    public void start() throws IOException {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(du.getUi().greet(), dukeImage),
                DialogBox.getDukeDialog("Previous records (if there are any):\n" +
                                            du.getStorage().load(), dukeImage),
                DialogBox.getDukeDialog("Is there anything I can do for you?", dukeImage),
                DialogBox.getSeparation(emptyImage)
                );
    }

    /**
     * Creates two dialog boxes. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        Parser p = new Parser(du.getTasks());
        if (Objects.equals(input, "bye")) {
            du.end();
            Platform.exit();
        }
        String response = p.parse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSeparation(emptyImage),
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getSeparation(emptyImage)
        );
        userInput.clear();
    }
}