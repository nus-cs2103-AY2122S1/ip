package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
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

    private Duke chatBot;

    // Image by André Santana AndreMS from Pixabay
    // Image from: https://pixabay.com/vectors/captain-america-marvel-hero-6192855/
    private final Image userIcon = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));

    // Image by Ega Maulana from Pixabay
    // Image from: https://pixabay.com/vectors/iron-man-chibi-cartoon-superhero-5783522/
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/BotIcon.png"));

    /**
     * Sets up the application UI when started.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        assert (botImage != null) : "Image resource cannot be null";
        assert (!botImage.isBackgroundLoading()) : "Chat Bot Icon not loaded in GUI";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.greetUser(), botImage));
    }

    public void setChatBot(Duke duke) {
        chatBot = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatBot.getResponse(input);

        assert (botImage != null) : "Image resource cannot be null";
        assert (userIcon != null) : "Image resource cannot be null";
        assert (!userIcon.isBackgroundLoading()) : "User Icon not loaded in GUI";
        assert (!botImage.isBackgroundLoading()) : "Chat Bot Icon not loaded in GUI";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + "    ", userIcon),
                DialogBox.getDukeDialog(response, botImage)
        );

        userInput.clear();
    }
}
