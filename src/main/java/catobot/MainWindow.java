package catobot;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Catobot catobot;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/image/user.png")));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/image/bot.png")));

    /**
     * Initializes the displaying window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                BotDialogBox.getBotDialog(Catobot.WELCOME, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = catobot.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                BotDialogBox.getBotDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public void setCatobot(Catobot c) {
        catobot = c;
    }
}
